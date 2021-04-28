package kr.or.ddit.board.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IReplyDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.utils.CryptoUtil;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Reply2VO;

@Service
public class ReplyServiceImpl implements IReplyService {
	@Inject
	private IReplyDAO replyDAO;

	private void encryptPassword(Reply2VO reply) {
	 	String inputPass = reply.getRep_pass();
	 	if(StringUtils.isNotBlank(inputPass)) {
	 		try {
				reply.setRep_pass(CryptoUtil.sha512(inputPass));
			} catch (NoSuchAlgorithmException e) {}
	 	}
	}
	
	@Override
	public ServiceResult createReply(Reply2VO reply) {
		encryptPassword(reply);
		int rowcnt = replyDAO.insertReply(reply);
		ServiceResult result = ServiceResult.FAIL;
		if(rowcnt>0) result = ServiceResult.OK;
		return result;
	}

	@Override
	public int readReplyCount(PagingVO<Reply2VO> pagingVO) {
		return replyDAO.selectReplyCount(pagingVO);
	}

	@Override
	public List<Reply2VO> readReplyList(PagingVO<Reply2VO> pagingVO) {
		return replyDAO.selectReplyList(pagingVO);
	}

	@Override
	public ServiceResult modifyReply(Reply2VO reply) {
		encryptPassword(reply);
		int rowcnt = replyDAO.updateReply(reply);
		ServiceResult result = ServiceResult.INVALIDPASSWORD;
		if(rowcnt>0) result = ServiceResult.OK;
		return result;
	}

	@Override
	public ServiceResult removeReply(Reply2VO reply) {
		encryptPassword(reply);
		int rowcnt = replyDAO.deleteReply(reply);
		ServiceResult result = ServiceResult.INVALIDPASSWORD;
		if(rowcnt>0) result = ServiceResult.OK;
		return result;
	}
	
}
