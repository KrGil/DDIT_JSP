package kr.or.ddit.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Reply2VO;

@Repository
public interface IReplyDAO {
	public int insertReply(Reply2VO reply);
	public int selectReplyCount(PagingVO<Reply2VO> pagingVO);
	public List<Reply2VO> selectReplyList(PagingVO<Reply2VO> pagingVO);
	public int updateReply(Reply2VO reply);
	public int deleteReply(Reply2VO reply);
}
