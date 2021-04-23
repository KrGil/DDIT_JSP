package kr.or.ddit.mvc;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver implements IViewResolver {
	private String prefix;
	private String suffix;
	
	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public void viewResolve(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean redirect = viewName.startsWith("redirect:");
		if(redirect) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			req.getRequestDispatcher(
					Objects.toString(prefix, "")
					+viewName
					+Objects.toString(suffix, "")).forward(req, resp);
		}
	}

}







