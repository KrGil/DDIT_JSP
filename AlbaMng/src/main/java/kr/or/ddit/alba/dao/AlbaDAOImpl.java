package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaDAOImpl implements IAlbaDAO {
	
	private static AlbaDAOImpl self;
	private AlbaDAOImpl() {
		super();
	}
	public static AlbaDAOImpl getInstance() {
		if(self==null) self = new AlbaDAOImpl();
		return self;
	}
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertAlba(AlbaVO alba, SqlSession session) {
		return session.insert("kr.or.ddit.alba.dao.IAlbaDAO.insertAlba", alba);
	}

	@Override
	public int selectAlbaCount(PagingVO<AlbaVO> pagingVO) {
		try(
			SqlSession session = sqlSessionFactory.openSession();	
		){
			IAlbaDAO mapper = session.getMapper(IAlbaDAO.class);
			return mapper.selectAlbaCount(pagingVO);
		}
	}

	@Override
	public List<AlbaVO> selectAlbaList(PagingVO<AlbaVO> pagingVO) {
		try(
			SqlSession session = sqlSessionFactory.openSession();	
		){
			IAlbaDAO mapper = session.getMapper(IAlbaDAO.class);
			return mapper.selectAlbaList(pagingVO);
		}
	}

	@Override
	public AlbaVO selectAlba(String al_id) {
		try(
			SqlSession session = sqlSessionFactory.openSession();	
		){
			IAlbaDAO mapper = session.getMapper(IAlbaDAO.class);
			return mapper.selectAlba(al_id);
		}
	}

	@Override
	public int updateAlba(AlbaVO alba, SqlSession session) {
		return session.insert("kr.or.ddit.alba.dao.IAlbaDAO.updateAlba", alba);
	}

	@Override
	public int deleteAlba(String al_id, SqlSession session) {
		return session.insert("kr.or.ddit.alba.dao.IAlbaDAO.deleteAlba", al_id);
	}

}
