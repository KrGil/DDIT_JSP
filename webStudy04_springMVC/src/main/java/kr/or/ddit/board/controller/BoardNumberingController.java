package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardNumberingController {
	@Inject
	private IBoardService service;
	
	
	@RequestMapping(value="{numeringType}.do"
					, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> recommend(
		@RequestParam(required=true) int what	
		, @PathVariable String numeringType
	) {
		ServiceResult result = null;
		if("recommend".equals(numeringType)) {
			result = service.recommend(what);
		}else if("report".equals(numeringType)) {
			result = service.report(what);
		}
		Map<String, Object> resultMap = new HashMap<>();
		boolean success = ServiceResult.OK.equals(result);
		resultMap.put("success", success);
		if(success) {
			BoardVO board = service.retrieveBoard(BoardVO.builder().bo_no(what).build());
			if("recommend".equals(numeringType)) {
				resultMap.put("recommend", board.getBo_rec());
			}else if("report".equals(numeringType)) {
				resultMap.put("report", board.getBo_rep());
			}
		}else {
			resultMap.put("message", "서버오류로 추천 실패");
		}
		return resultMap;
	}
}










