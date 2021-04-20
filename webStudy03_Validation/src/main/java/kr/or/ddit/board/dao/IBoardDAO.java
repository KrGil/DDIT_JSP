package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

/**
 *	게시글 관리를 위한 persistence layer
 */
public interface IBoardDAO {
	public int insertBoard(BoardVO board, SqlSession session);
	public int selectBoardCount(PagingVO<BoardVO> pagingVO);
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	/**
	 * 
	 * @param search
	 * @return 없으면 null, 존재한다면  BoardVO
	 */
	public BoardVO selectBoard(BoardVO search);
	/**
	 * 
	 * @param board
	 * @param session TODO
	 * @return int
	 */
	public int updateBoard(BoardVO board, SqlSession session);
	/**
	 * 
	 * @param search( BoardVO의 속성 중 하나 )
	 * @return int
	 */
	public int deleteBoard(BoardVO search);
	
}
