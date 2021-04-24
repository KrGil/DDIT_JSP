package kr.or.ddit.alba.controller;

import javax.servlet.http.HttpSession;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;

@Controller
public class AlbaDeleteController{
	IAlbaService service = AlbaServiceImpl.getInstance();
	@RequestMapping("/alba/albaDelete.do")
	public String doGet(@RequestParam("who") String who, HttpSession session){
		String goPage = null;
		ServiceResult result = service.removeAlba(who);
		if(ServiceResult.OK.equals(result)){
			goPage = "redirect:/alba/albaList.do";
		}else{
			session.setAttribute("message", "서버 오류로 인해 삭제 실패");
			goPage = "redirect:/alba/albaView.do?who="+who;
		}
		
		return goPage;
	}
}
