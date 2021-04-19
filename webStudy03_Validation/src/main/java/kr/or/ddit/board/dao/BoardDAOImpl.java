package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardDAOImpl implements IBoardDAO {
	// 싱글톤
	private static BoardDAOImpl self;
	BoardDAOImpl() {}
	public static BoardDAOImpl getInstance() {
		if(self==null) self = new BoardDAOImpl();
		return self;
	}
	
	private SqlSessionFactory sessionFactory = 
				CustomSqlSessionFactoryBuilder.getSessionFactory();
	

	@Override
	public int insertBoard(BoardVO board) {
		try(
			SqlSession session =sessionFactory.openSession();
		){
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			int cnt =mapper.insertBoard(board);
			session.commit();
			return cnt;
		}
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
			SqlSession session =  sessionFactory.openSession();
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
	public int updateBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(BoardVO search) {
		// TODO Auto-generated method stub
		return 0;
	}

}
