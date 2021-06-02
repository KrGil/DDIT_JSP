package kr.or.ddit.board.controller;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.utils.RegexUtils;
import kr.or.ddit.validator.BoardInsertGroup;
import kr.or.ddit.validator.NoticeInsertGroup;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardInsertController {
	private static final Logger logger = 
			LoggerFactory.getLogger(BoardInsertController.class);
	private String[] filteringTokens = new String[] {"말미잘", "해삼"};
	
	@Inject
	private IBoardService service;
	
	@PostConstruct
	public void init() {
		logger.info("주입된 service : {}", service.getClass().getName());
		logger.info("프록시 여부 : {}", AopUtils.isAopProxy(service));
	}
		
	@RequestMapping("/board/noticeInsert.do")
	public String noticeForm(@ModelAttribute("board") BoardVO board) {
		board.setBo_type("NOTICE");
		return "board/boardForm";
	}
	
	@RequestMapping(value="/board/noticeInsert.do", method=RequestMethod.POST)
	public String noticeInsert(
			@Validated(NoticeInsertGroup.class) @ModelAttribute("board") BoardVO board
			, BindingResult errors
			, Model model
			) {
		return insert(board, errors, model);
	}
	
	@RequestMapping("/board/boardInsert.do")
	public String form(@ModelAttribute("board") BoardVO board
			, @RequestParam(value="parent", required=false, defaultValue="0") int parent
			) {
		board.setBo_type("BOARD");
		board.setBo_parent(parent);
		return "board/boardForm";
	}
	
	
	@RequestMapping(value="/board/boardInsert.do", method=RequestMethod.POST)
	public String insert(
			@Validated(BoardInsertGroup.class) @ModelAttribute("board") BoardVO board
			, Errors errors
			, Model model
			) {
		
		boolean valid = !errors.hasErrors();
		
		String view = null;
		String message = null;
		if(valid) {
			
			String replaceText = 
					RegexUtils.filteringTokens(board.getBo_content(), 'ㅁ', filteringTokens);
			board.setBo_content(replaceText);
			
			ServiceResult result = service.createBoard(board);
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/board/boardView.do?what="+board.getBo_no();
			}else {
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









