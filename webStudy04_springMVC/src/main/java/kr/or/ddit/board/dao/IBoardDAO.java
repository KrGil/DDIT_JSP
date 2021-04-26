package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 게시글 관리를 위한 persistence layer
 *
 */
public interface IBoardDAO {
	public int insertBoard(BoardVO board, SqlSession session);
	public int selectBoardCount(PagingVO<BoardVO> pagingVO);
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	public BoardVO selectBoard(BoardVO search);
	public int updateBoard(BoardVO board, SqlSession session);
	public int deleteBoard(BoardVO search, SqlSession session);
	
	/**
	 * 조회수 증가
	 * @param bo_no
	 * @return
	 */
	public int incrementHit(int bo_no);
	/**
	 * 추천수 증가
	 * @param bo_no
	 * @return
	 */
	public int incrementRcmd(int bo_no);
	/**
	 * 신고수 증가
	 * @param bo_no
	 * @return
	 */
	public int incrementRpt(int bo_no);
}










