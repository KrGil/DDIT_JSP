package kr.or.krgil.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.krgil.mvc.annotation.HandlerAdapter;
import kr.or.krgil.mvc.annotation.HandlerMapping;
import kr.or.krgil.mvc.annotation.IHandlerAdapter;
import kr.or.krgil.mvc.annotation.IHandlerMapping;
import kr.or.krgil.mvc.annotation.RequestMapping;
import kr.or.krgil.mvc.annotation.RequestMappingInfo;

public class FrontController extends HttpServlet {
	private IHandlerMapping handlerMapping;
	private IHandlerAdapter handlerAdapter;
	private IViewResolver viewResolver;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String packageParam = config.getInitParameter("basePackes");
		String[] basePackages =  packageParam.split("\\s+");
		
		handlerMapping = new HandlerMapping(basePackages);
		handlerAdapter = new HandlerAdapter();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix(config.getInitParameter("prefix"));
		viewResolver.setSuffix(config.getInitParameter("suffix"));
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		RequestMappingInfo mappingInfo = handlerMapping.findCommandHandler(req);

		// 요청했는데 우리가 가지고 있지 않다.
		if (mappingInfo == null) {
			resp.sendError(404, "현재 요청을 처리할 수 있는 핸들러가 없음.");
			return;
		}

		String viewName = handlerAdapter.invokHandler(mappingInfo, req, resp);

		// 일단 우리에게 잘못이 있다.
		if (viewName == null) {
			if (!resp.isCommitted()) {
				resp.sendError(500, "논리적인 뷰네임은 널일수 없음.");
			}

		} else {
			viewResolver.viewResolve(viewName, req, resp);
		}
	}
}
