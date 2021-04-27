package kr.or.ddit.board.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.utils.RegexUtils;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardUpdateController {
	IBoardService service = new BoardServiceImpl();
	private String[] filteringTokens = new String[] {"말미잘", "해삼"};
	private static final Logger logger = LoggerFactory.getLogger(BoardUpdateController.class);
	
	@RequestMapping("/board/boardUpdate.do")
	public String form(
			@RequestParam("what") int bo_no
			, Model model
			) {
		BoardVO board = service.retrieveBoard(BoardVO.builder().bo_no(bo_no).build());
		model.addAttribute("board", board);
		// 수정폼으로 전달
		return "board/boardForm";
	}
	@RequestMapping(value="/board/boardUpdate.do", method=RequestMethod.POST)
	public String update(
			@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board
			, Errors errors
			, Model model
			) {
		
		// 검증 시 groupType 넘기기. groupHint를 적용한 검증.
//		Class<?> groupHint = (Class<?>) model.getAttribute("groupHint");
//		if(groupHint==null)groupHint=BoardInsertGroup.class;
//		boolean valid = new CommonValidator<BoardVO>().validate(board, errors, groupHint);
//		boolean valid = new CommonValidator<BoardVO>()
//				.validate(board, errors, UpdateGroup.class);
		
		boolean valid = !errors.hasErrors();
		String view = null;
		String message = null;
		if(valid) {
			String replaceText = 
					RegexUtils.filteringTokens(board.getBo_content(), 'ㅁ', filteringTokens);
			board.setBo_content(replaceText);
			// 검증 통과시 modify 로직 사용
			logger.info("{ } int[]-----\n\n\n", board.getDelAttNos() );
			ServiceResult result = service.modifyBoard(board);
			if(ServiceResult.OK.equals(result)) {
				// 로직 실행 성공
				// 성공 결과를 확인할 수 있는 view 로 redirect
				view = "redirect:/board/boardView.do?what="+board.getBo_no();
			}else {
				// 로직 실행 실패	
				// 다시 명령이 발생할 수 있는 곳으로 이동
				message = "서버 오류";
				view = "board/boardForm";
			}
		}else {
			view = "board/boardForm";
		}
		
		model.addAttribute("message", message);
		return view;
	}
}
