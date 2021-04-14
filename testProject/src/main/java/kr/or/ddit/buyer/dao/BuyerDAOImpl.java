package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImpl implements IBuyerDAO {
	// 싱글톤 만들기
	private static BuyerDAOImpl self;
	private BuyerDAOImpl() {}
	public static BuyerDAOImpl getInstance() {
		if(self==null) self = new BuyerDAOImpl();
		return self;
	}
	
	private SqlSessionFactory sessionFactory = 
			CustomSqlSessionFactoryBuilder.getSessionFactory();
	

	@Override
	public BuyerVO selectBuyerForAuth(String buyer_id) {
		try( SqlSession session = sessionFactory.openSession();
		){
//			return (MemberVO) session.selectOne("kr.or.ddit.member.dao.IMemberDAO.selectMemberForAuth", mem_id);
			// mapper proxy - 가짜 매퍼
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyerForAuth(buyer_id);
		}
	}

	@Override
	public BuyerVO selectBuyerDetail(String buyer_id) {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyerDetail(buyer_id);
		}
	}

	@Override
	public int insertBuyer(BuyerVO buyer) {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			int cnt = mapper.insertBuyer(buyer);
			session.commit();
			return cnt;
		}
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		try(
			SqlSession session = sessionFactory.openSession();
			// 문제가 있다. 문제가 있어도 commit을 해버림
		){
//			MemberDAOImpl.class.equals(this.getClass());
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			int cnt = mapper.updateBuyer(buyer);
			session.commit();
			return cnt;
		}
	}

	@Override
	public int deleteBuyer(String mem_id) {
		try(
				SqlSession session = sessionFactory.openSession();	
			){
				IBuyerDAO mapper =
						session.getMapper(IBuyerDAO.class);
				int cnt = mapper.deleteBuyer(mem_id);
				session.commit();
				return cnt;
			}
	}

	@Override
	public List<BuyerVO> selectBuyerList(PagingVO pagingVO) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
				return mapper.selectBuyerList(pagingVO);
			}
	}
	@Override
	public int selectTotalRecord(PagingVO pagingVO) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
				return mapper.selectTotalRecord(pagingVO);
			}
	}

}
