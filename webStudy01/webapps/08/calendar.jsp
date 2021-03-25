<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar. *"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%   
   // 파라미터 받기 => 있나 없나 확인 => 없으면 현재 달력
   // 있으면 그 달로 이동
   request.setCharacterEncoding("UTF-8");
   String yearParam = request.getParameter("year");
   String monthParam = request.getParameter("month");
   String loc = request.getParameter("loc");
   String zoneParam = request.getParameter("timeZone");
   
   Locale locale = request.getLocale();
//    locale = locale.ENGLISH;
   if(loc != null && !loc.isEmpty()){
      locale = Locale.forLanguageTag(loc);
   }
   
   TimeZone zone = TimeZone.getDefault();
   if(zoneParam != null && !zoneParam.isEmpty()){
      zone = TimeZone.getTimeZone(zoneParam);
   }
   // java 1.7부터는 static import가능
   // 그럼 계속해서 Calender. 쓸 필요 없음
   //   Calendar calender = Calender.getInstance();
   Calendar calendar =  Calendar.getInstance(locale);   // 얘는 값이 계속 바뀌는 애
   
   //그래서 Calender하나 만든다 
   final Calendar TODAY = getInstance(zone, locale);
   
   if(yearParam != null && yearParam.matches("\\d{4}")){
      calendar.set(YEAR, Integer.parseInt(yearParam));
   }
   // \\d => 0 - 9 (1월 ~ 10월) |(or) 11월, 12월 => 10, 11 (앞에 1이 오고 그 뒤가 [0-1])
   if(monthParam != null && monthParam.matches("\\d|1[0-1]")){
      calendar.set(MONTH, Integer.parseInt(monthParam));
   }
   calendar.set(DAY_OF_MONTH, 1);
   int dayOfWeek = calendar.get(DAY_OF_WEEK);
   int offset = dayOfWeek - 1;
   int maxDate = calendar.getActualMaximum(DAY_OF_MONTH);
   
   DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);
   calendar.add(MONTH, -1);   // 한 달 전으로 상태를 바꿈 - 2/1
   int beforeYear = calendar.get(YEAR);      // 그걸 저장
   int beforeMonth = calendar.get(MONTH);   // 그걸 저장
   
   calendar.add(MONTH, 2);   // 두 달 후로 상태를 바꿈 - 4/1
   int nextYear = calendar.get(YEAR);      // 그걸 저장
   int nextMonth = calendar.get(MONTH);      // 그걸 저장
   // 현재 달력의 상태 : 다음달까지 감
   calendar.add(MONTH, -1);   // 한 달 전으로 상태를 바꿈 - 3/1(이번달)
   
   int year = calendar.get(YEAR);
   int month = calendar.get(MONTH);
   
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  .sunday{
   background: red;
     color: white;
  }
  .saturday{
     background: blue;
     color: skyblue;
  }
  .current{
     background: skyblue;
     color: darkblue;
  }
</style>
</head>
<body>
<h3><%=String.format(locale, "%tc", TODAY) %> </h3>
<h4>
<!-- 지문, 발열체크, 키재기 >>> 정문, 후문 >> 중앙문으로
   a태그 못 쓰게 막고 form태그로만 하게..
   둘 다 a태그라서 eventhandler들어가는데 하는 일이 똑같음  -->
<!-- 그래서 밑에 script만들었음 => return값을 false로
   근데 이 false가 여기에서 일어나는 일이니까 onclick할 때 false값 받을 수 있게
   여기서 return을 해준다 -->
<!-- a태그가 전인지 후인지 모르겠으니까 dataproperty를 숨겨보자 -->
  <a onclick="return clickHandler(event);" href="#" data-year="<%=beforeYear %>" 
      data-month="<%=beforeMonth %>">◀◀◀</a>
  <%=String.format("%1$tY %1$tB", calendar) %>
  <a onclick="return clickHandler(event);"  href="#" data-year="<%=nextYear %>" 
      data-month="<%=nextMonth %>">▶▶▶</a>
</h4>
<form name="calForm">
<!-- 왜 여기 input의 name을 year로 해야할까? 위에 파라미터를 year로 받아서 -->
<!-- 넘어가는 식별자를 공통으로 하기 위해서 -->
  <input type="number" name="year" placeholder="년도" value="<%=year %>" 
        onchange="this.form.submit();"/>
  <select name="month" onchange="this.form.submit();">
    <%
      String[] months = dfs.getMonths();
       for(int i = 0; i <= months.length - 1; i++){
    %>
       <option <%=i==month?"selected":"" %> value="<%=i %>"><%=months[i] %></option>
    <%
       }
    %>
  </select>
  <select name="loc" onchange="this.form.submit();">
  <% // 서버 쪽으로는 language tag? = localecode
    Locale[] locales = Locale.getAvailableLocales();
    for(Locale tmp : locales){
       String dL = tmp.getDisplayLanguage(tmp);
       String dc = tmp.getDisplayCountry(tmp);
       if(dL.isEmpty() && dc.isEmpty()) continue;
       String selected = "";
       selected = locale.equals(tmp)? "selected" : "";
  %>
     <option <%=selected %> value="<%= tmp.toLanguageTag()%>"><%=String.format("%s[%s]", dL, dc) %></option>
  <%
    }
  %>
  </select>
  <select name="timeZone" onchange="this.form.submit();"> 
     <%
        for(String tmpId : TimeZone.getAvailableIDs()){
           TimeZone tmpZone = TimeZone.getTimeZone(tmpId);
        %>
        <option <%=tmpZone.equals(zone)?"selected":"" %> value="<%=tmpId %>"><%=tmpZone.getDisplayName() %></option>
        <%
     }
     %>
  </select>
</form>
<table>
  <thead>
  <tr>
  <%
     String[] weekDays = dfs.getShortWeekdays();
    // 아까 calender에서 sunday가 1이라고 했으니까 0은 없ㅇ므
    for(int i = SUNDAY; i <= SATURDAY; i++){
  %>
     <th><%=weekDays[i] %></th>
  <%
    }
  %>
  </tr>
  </thead>
  <tbody>
     <%
        calendar.add(DATE, -offset);   // DAY_OF_MONTH = DATE
        for(int row = 1; row <= 6; row++){
     %>
     <tr>
     <%
        for(int col = 1; col <= 7; col++){
           int date = calendar.get(DATE);
           // calendar 밑에서 증가시키니까 지금 값 가져올 수 있다
           // 그럼 삼항연산자 쓸 필요 없이 그냥 date쓰면 됨
           String clz = col==SUNDAY? "sunday" :
                          col==SATURDAY? "saturday" :
                             (year==TODAY.get(YEAR) && month==TODAY.get(MONTH) 
                             && calendar.get(DATE)==TODAY.get(DATE))?"current":"normal";
     %>
<%--         <td><%=date > 0 && date <= maxDate ? date : "" %></td> --%>
        <td class="<%=clz %>"><%=date %></td>
     <%
           calendar.add(DATE, 1);
           // 반복시킬 때마다 1씩증가시키기 => 하루하루 증가함
        }
     %>
     </tr>
     <%
        }
     %>
  </tbody>
</table>
<script type="text/javascript">
  let calForm = document.calForm;
  // Form태그는 document의 property명으로 적용이 된다
  // 브라우저에 따라서는 property명으로 작동하는 경우도 있고 아닌 경우도 있다
  if(!calForm) calForm = document.querySelector("[name='calForm']");
  // 만약 calForm이 없으면 => 브라우저가 작동하지 않으면 
  // querySelector이용해서 name이 calForm인 거 갖고 오기
  
  function clickHandler(event){
   event.preventDefault();
   let dataset = event.target.dataset;
   // 모든 event는 자신을 발생시킨 요소에 대한 정보를 항상 target으로 갖고 있다
   calForm.year.value= dataset.year;
   calForm.month.value= dataset.month;
   // form안의 모든 요소들의 name form태그의 property로 사용할 수 있다 
   calForm.submit();
   return false;
  }
</script>
</body>
</html>