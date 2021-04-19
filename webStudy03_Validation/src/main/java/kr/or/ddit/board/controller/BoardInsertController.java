package kr.or.ddit.board.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.utils.RegexUtils;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardInsertController {
	private static final Logger logger = LoggerFactory.getLogger(BoardInsertController.class);
	private String[] filteringTokens = new String[] {"말미잘", "해삼"};
	
	private IBoardService service = BoardServiceImpl.getInstance();
			
	@RequestMapping("/board/boardInsert.do")
	public String form(@ModelAttribute("board") BoardVO board) {
        // model - 받아오는 param이 없으면 비어있는 녀석을 새로 생성해서 가져온다.
		board.setBo_type("BOARD");
		return "board/boardForm";
	}
	
	@RequestMapping(value="/board/boardInsert.do", method=RequestMethod.POST)
	public String insert(
			@ModelAttribute("board") BoardVO board
			, @RequestPart(value="bo_files", required=false) MultipartFile[] bo_files
			, HttpServletRequest req
			) {
		// file.isEmpty()로 검증 파일이 없더라도 파트는 생성되기에
		List<AttatchVO> attatchList = new ArrayList<>();
		if(bo_files!=null) {
			for(MultipartFile file : bo_files) {
				if(file.isEmpty()) continue;
				attatchList.add(new AttatchVO(file));
			}
			if(attatchList.size()>0)
				board.setAttatchList(attatchList);
		}
		logger.info("{} attatchList", attatchList);
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = new CommonValidator<BoardVO>()
						.validate(board, errors, InsertGroup.class);
		
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
		
		req.setAttribute("message", message);
		
		return view;
	}
}









