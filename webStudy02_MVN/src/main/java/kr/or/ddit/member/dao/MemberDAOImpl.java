package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {
	// 싱글톤 만들기
	private static MemberDAOImpl self;
	private MemberDAOImpl() {}
	public static MemberDAOImpl getInstance() {
		if(self==null) self = new MemberDAOImpl();
		return self;
	}
	
	private SqlSessionFactory sessionFactory = 
			CustomSqlSessionFactoryBuilder.getSessionFactory();
	

	@Override
	public MemberVO selectMemberForAuth(String mem_id) {
		try( SqlSession session = sessionFactory.openSession();
		){
//			return (MemberVO) session.selectOne("kr.or.ddit.member.dao.IMemberDAO.selectMemberForAuth", mem_id);
			// mapper proxy - 가짜 매퍼
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			return mapper.selectMemberForAuth(mem_id);
		}
	}

	@Override
	public MemberVO selectMemberDetail(String mem_id) {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			return mapper.selectMemberDetail(mem_id);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			int cnt = mapper.insertMember(member);
			session.commit();
			return cnt;
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
			SqlSession session = sessionFactory.openSession();
			// 문제가 있다. 문제가 있어도 commit을 해버림
		){
//			MemberDAOImpl.class.equals(this.getClass());
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			int cnt = mapper.updateMember(member);
			session.commit();
			return cnt;
		}
	}

	@Override
	public int deleteMember(String mem_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> selectMeberList() {
		// TODO Auto-generated method stub
		return null;
	}

}
