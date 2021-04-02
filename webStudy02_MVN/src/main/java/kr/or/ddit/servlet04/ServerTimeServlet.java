package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.MimeType;
@WebServlet("/04/serverTime")
public class ServerTimeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 가져오기
		String loc = req.getParameter("loc");
		String zone = req.getParameter("zone");
		TimeZone timeZone = TimeZone.getDefault();
		Locale locale = req.getLocale();
		if(loc!=null && !loc.isEmpty()) {
			locale = Locale.forLanguageTag(loc);
		}
		if(zone!=null && !zone.isEmpty()) {
			timeZone = TimeZone.getTimeZone(zone);
		}
		
		
		resp.setIntHeader("Refresh", 1);
		
		resp.setContentType(MimeType.PLAIN.getMime());
		try(
			PrintWriter out = resp.getWriter();
		){
			out.print(
				String.format(locale, "%tc", Calendar.getInstance(timeZone))
			);
		}
		
	}
}
