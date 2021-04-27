package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.Reply2VO;

public interface IReplyService {
	public ServiceResult createReply(Reply2VO reply);
	public ServiceResult modifyReply(Reply2VO reply);
	public List<Reply2VO> retrieveListReply();
	public ServiceResult removeReply(Reply2VO reply);
}
