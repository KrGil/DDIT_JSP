package kr.or.ddit.board.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IReplyDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.utils.CryptoUtil;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Reply2VO;

@Service
public class ReplyServiceImpl implements IReplyService{
	
	IReplyDAO replyDAO;
	@Inject
	public void setReplyDAO(IReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	private void encodePassword(BoardVO board) {
		String bo_pass = board.getBo_pass();
		if(StringUtils.isBlank(bo_pass)) return;
		try {
			String encodedPass = CryptoUtil.sha512(bo_pass);
			board.setBo_pass(encodedPass);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ServiceResult createReply(Reply2VO reply) {
		ServiceResult result = null;
		return result;
	}
	
	public ServiceResult modifyReply(Reply2VO reply) {
		ServiceResult result = null;
		return result;
	}
	public List<Reply2VO> retrieveListReply(){
		return replyDAO.selectReplyList();
	}
	public ServiceResult removeReply(Reply2VO reply) {
		ServiceResult result = null;
		return result;
	}
}
