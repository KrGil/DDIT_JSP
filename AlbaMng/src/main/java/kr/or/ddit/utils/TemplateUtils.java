package kr.or.ddit.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class TemplateUtils {
	public static String readTemplate(HttpServletRequest request) throws IOException {
		String requestURI = request.getRequestURI();
		String cp = request.getContextPath(); // /webStudy01
		
		String tmplUrl = requestURI.substring(cp.length());
		URL url = request.getServletContext().getResource(tmplUrl);
		File tmplFile = new File(url.getFile());
		StringBuffer template = new StringBuffer();
		try(
			FileInputStream fis = new FileInputStream(tmplFile);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader reader = new BufferedReader(isr);
		){
			String tmp = null;
			while((tmp = reader.readLine()) != null) {
				template.append(tmp + "\n" );
			}
		}
		return template.toString();
	}
	
	static Pattern pattern = Pattern.compile("%([a-zA-Z0-9_]+)%");
	
	public static String reaplaceTemplateToData(HttpServletRequest request, Map<String, Object> dataMap) throws IOException{
		String template = readTemplate(request);
		Matcher matcher = pattern.matcher(template);
		StringBuffer html = new StringBuffer();
		while(matcher.find()) {
			String dataName = matcher.group(1);
			Object data =dataMap.get(dataName);
			String value = Objects.toString(data, "");
			matcher.appendReplacement(html, value);
		}
		matcher.appendTail(html);
//		String html = template.replace("%title%", (String)dataMap.get("title"));
//		html = html.replace("%optionData%", dataMap.get("optionData").toString());
//		String html = null;
		
		return html.toString();
	}
}

















