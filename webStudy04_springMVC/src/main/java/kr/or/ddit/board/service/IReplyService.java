package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Reply2VO;

public interface IReplyService {
	public ServiceResult createReply(Reply2VO reply);
	public int readReplyCount(PagingVO<Reply2VO> pagingVO);
	public List<Reply2VO> readReplyList(PagingVO<Reply2VO> pagingVO);
	public ServiceResult modifyReply(Reply2VO reply);
	public ServiceResult removeReply(Reply2VO reply);
}
