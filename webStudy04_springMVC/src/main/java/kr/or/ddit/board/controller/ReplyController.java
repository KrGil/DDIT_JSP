package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Reply2VO;

//@Controller
//@ResponseBody
@RestController
@RequestMapping(
		value="/reply/{bo_no}"
		, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
public class ReplyController {
	@Inject
	private IReplyService service;
	
	// 목록 조회
//	@RequestMapping(method= RequestMethod.GET)
	@GetMapping
	public PagingVO<Reply2VO> list(
			@RequestParam(value="what", required=true) int bo_no,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, HttpServletRequest resp){
		// ======검색, 특정글의 댓글만 조회
		Reply2VO detailSearch = new Reply2VO();
		detailSearch.setBo_no(bo_no);
		
		PagingVO<Reply2VO> pagingVO = new PagingVO<>(5,5);
		pagingVO.setDetailSearch(detailSearch);
		// ==================================
		
		int totalRecord = service.readReplyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord); // totalPage
		pagingVO.setCurrentPage(currentPage); // startRow, endRow, startPage, endPage
		
		List<Reply2VO> ReplyList = service.readReplyList(pagingVO);
		pagingVO.setDataList(ReplyList);
		return pagingVO;
	}
	
//	@RequestMapping(method=RequestMethod.POST)
	@PostMapping
	public Map<String, Object> insert(@ModelAttribute("reply") Reply2VO reply,
		HttpServletResponse resp) throws ServletException, IOException{
		ServiceResult result = service.createReply(reply);
		Map<String, Object> resultMap = Collections.singletonMap("result", result);
		return resultMap;
	}
	
//	@RequestMapping(value="{rep_no}", method=RequestMethod.PUT)
	@PutMapping("{rep_no}")
	public Map<String, Object> update(@ModelAttribute("reply") Reply2VO reply,
			HttpServletResponse resp) throws ServletException, IOException {
		ServiceResult result = service.modifyReply(reply);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		if(ServiceResult.INVALIDPASSWORD.equals(result)) {
			resultMap.put("message", "비밀번호 오류");
		}
		return resultMap;
	}
//	@RequestMapping(value="{rep_no}", method=RequestMethod.DELETE)
	@DeleteMapping("{rep_no}")
	public Map<String, Object> delete(@ModelAttribute("reply") Reply2VO reply,
			HttpServletResponse resp) throws ServletException, IOException {
		ServiceResult result = service.removeReply(reply);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		if(ServiceResult.INVALIDPASSWORD.equals(result)) {
			resultMap.put("message", "비밀번호 오류");
		}
		return resultMap;
	}
}
