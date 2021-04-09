package kr.or.ddit.servlet03.view;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public abstract class AbstractView {
	public abstract void mergeModelAndView(Object target, HttpServletResponse resp) 
			throws IOException;
}
