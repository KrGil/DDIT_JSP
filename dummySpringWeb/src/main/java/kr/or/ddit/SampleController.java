package kr.or.ddit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// 1. 빈에 등록시키겠다.
// 2. 핸들러 매핑에 의해서 수집되겠다.
@Controller
@RequestMapping("/test")
public class SampleController {
	
	@RequestMapping(value="sample1.do", method = RequestMethod.GET)
	public String sample1(Model model) {
		// requset의 setAttribute한 것과 같다(adaptor가 알아서 한다.).
		model.addAttribute("data", "전달한 모델");
		return "sample/view";
	}
	
	@RequestMapping("sample2.do")
	public ModelAndView sample2() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", "전달한 모델");
		mav.setViewName("sample/view");
		return mav;
	}
}
