package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public class BoardDAOImpl implements IBoardDAO {
	private SqlSessionFactory sessionFactory =
			CustomSqlSessionFactoryBuilder.getSessionFactory();

	@Override
	public int insertBoard(BoardVO board, SqlSession session) {
		return session.insert("kr.or.ddit.board.dao.IBoardDAO.insertBoard", board);
	}

	@Override
	public int selectBoardCount(PagingVO<BoardVO> pagingVO) {
		try(
			SqlSession session = sessionFactory.openSession();	
		){
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.selectBoardCount(pagingVO);
		}
	}

	@Override
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO) {
		try(
			SqlSession session = sessionFactory.openSession();	
		){
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.selectBoardList(pagingVO);
		}
	}

	@Override
	public BoardVO selectBoard(BoardVO search) {
		try(
			SqlSession session = sessionFactory.openSession();	
		){
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.selectBoard(search);
		}
	}

	@Override
	public int updateBoard(BoardVO board, SqlSession session) {
		return session.update("kr.or.ddit.board.dao.IBoardDAO.updateBoard", board);
	}

	@Override
	public int deleteBoard(BoardVO search, SqlSession session) {
		return session.delete("kr.or.ddit.board.dao.IBoardDAO.deleteBoard", search);
	}

	@Override
	public int incrementHit(int bo_no) {
		try(
			SqlSession session = sessionFactory.openSession(true);	
		){
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.incrementHit(bo_no); 
		}
	}

	@Override
	public int incrementRcmd(int bo_no) {
		try(
			SqlSession session = sessionFactory.openSession(true);	
		){
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.incrementRcmd(bo_no); 
		}
	}

	@Override
	public int incrementRpt(int bo_no) {
		try(
			SqlSession session = sessionFactory.openSession(true);	
		){
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.incrementRpt(bo_no); 
		}
	}

}
