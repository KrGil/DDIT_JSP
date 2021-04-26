package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

@Repository
public class AttatchDAOImpl implements IAttatchDAO{
	
	private SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();
	
	@Override
	public int insertAttatches(BoardVO board, SqlSession session) {
		return session.insert("kr.or.ddit.board.dao.IAttatchDAO.insertAttatches", board);
	}

	@Override
	public AttatchVO selectAttatch(int att_no) {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			IAttatchDAO mapper = session.getMapper(IAttatchDAO.class);
			AttatchVO attatchVO = null;
			attatchVO = mapper.selectAttatch(att_no);
			return attatchVO;
		}
	}

	@Override
	public List<String> selectSaveNamesForDelete(BoardVO board) {
		try(
			SqlSession session = sessionFactory.openSession();
			){
			IAttatchDAO mapper = session.getMapper(IAttatchDAO.class);
			return mapper.selectSaveNamesForDelete(board);
		}
	}
	@Override
	public int deleteAttatches(BoardVO board, SqlSession session) {
		return session.delete("kr.or.ddit.board.dao.IAttatchDAO.deleteAttatches", board);
	}
}
