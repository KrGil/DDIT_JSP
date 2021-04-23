/**
 * 
 */
	let licCodeTag = $("[name='licCodes']");
	let grCodeTag = $("[name='grCode']");
	$.ajax({
		url:CONTEXTPATH+"/alba/getLicenseList.do",
		dataType:"json",
		success:function(resp){
			if(!resp) return;
			let options = [];
			$(resp).each(function(idx, lic){
				options.push($("<option>").attr("value", lic.licCode).text(lic.licName));
			});
			licCodeTag.append(options);
		}
	});
	$.ajax({
		url:CONTEXTPATH+"/alba/getGradeList.do",
		dataType:"json",
		success:function(resp){
			if(!resp) return;
			let options = [];
			$(resp).each(function(idx, grade){
				options.push($("<option>").attr("value", grade.grCode).text(grade.grName));
			});
			grCodeTag.append(options);	
		}
	});