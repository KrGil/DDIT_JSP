package kr.or.ddit.commons.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZipVO;

public class SearchZipDAOImpl implements ISearchZipDAO {
	private SearchZipDAOImpl() { }
	private static SearchZipDAOImpl self;
	public static SearchZipDAOImpl getInstance() {
		if(self==null) self = new SearchZipDAOImpl();
		return self;
	}

	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int selectZipCount(PagingVO<ZipVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){		
			ISearchZipDAO mapper = sqlSession.getMapper(ISearchZipDAO.class);
			return mapper.selectZipCount(pagingVO);
		}
	}
	
	@Override
	public List<ZipVO> selectZipList(PagingVO<ZipVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){		
			ISearchZipDAO mapper = sqlSession.getMapper(ISearchZipDAO.class);
			return mapper.selectZipList(pagingVO);
		}
	}

}
