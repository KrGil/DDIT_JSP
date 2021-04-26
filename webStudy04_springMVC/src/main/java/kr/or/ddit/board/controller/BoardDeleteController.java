package kr.or.ddit.board.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.DeleteGroup;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardDeleteController {
	private static final Logger logger = LoggerFactory.getLogger(BoardDeleteController.class);
	@Inject
	private IBoardService service ;
	
	@RequestMapping(value="/board/boardDelete.do", method=RequestMethod.POST)
	public String delete(
		@ModelAttribute("board")BoardVO board	
		, Model model
		, RedirectAttributes redirectAttributes
	) {
		logger.info(board.getBo_pass());
		Map<String, List<String>> errors = new LinkedHashMap<>();
		model.addAttribute("errors", errors);
		
		// delete group hint 를 적용한 검증
		boolean valid = new CommonValidator<BoardVO>()
						.validate(board, errors, DeleteGroup.class);
		String view = null;
		if(valid) {
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
