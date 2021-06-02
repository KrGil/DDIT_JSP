package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validator.DeleteGroup;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardDeleteController {
	@Inject
	private IBoardService service;
	@RequestMapping(value="/board/boardDelete.do", method=RequestMethod.POST)
	public String delete(
		@Validated(DeleteGroup.class) @ModelAttribute("board")BoardVO board
		, BindingResult errors
		, Model model
		, RedirectAttributes redirectAttributes
	) {
		String view = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.removeBoard(board);
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/board/boardList.do";
			}else {
				redirectAttributes.addFlashAttribute("message", "비밀번호 오류");
				view= "redirect:/board/boardView.do?what="+board.getBo_no(); 
			}
		}else {
			redirectAttributes.addFlashAttribute("message", "필수 데이터 누락");
			view= "redirect:/board/boardView.do?what="+board.getBo_no();
		}
		return view;
	}
}
