package kr.or.ddit.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Reply2VO;

@Repository
public interface IReplyDAO {
	// 1. crud
	// 2. paging
	// uri restful
	// dao, service, xml은 동일하게 작성.
	// controller - 4가지
	// 1. get, post, put, delete.
	
	public int insertReply();
	public List<Reply2VO> selectReplyList();
	public Reply2VO selectReply(Reply2VO vo);
	public int updateReply();
	public int deleteReply();
}
