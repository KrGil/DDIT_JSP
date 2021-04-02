package kr.or.ddit.servlet03.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonView extends AbstractView{
	
	// mvc - model->vo, view -> JsonView
	@Override
	public void mergeModelAndView(Object target, HttpServletResponse resp) 
	throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(target);
		
		try( // 직열화.
			PrintWriter out = resp.getWriter();
		){
//			out.print(json);
			mapper.writeValue(out, target); //marshalling과 serialliziton을 함께 해주기
		}
		
	}
}
