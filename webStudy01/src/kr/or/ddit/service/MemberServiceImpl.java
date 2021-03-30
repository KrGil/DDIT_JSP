package kr.or.ddit.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private IMemberDAO dao = new MemberDAOImpl();
	@Override
	public MemberVO retrieveMember(String mem_id) {
		MemberVO savedMember = dao.selectMemberDetail(mem_id);
		if(savedMember==null) {
			// custom exception 발생
		}
		return savedMember;
		// 특정 상황에 쓸 수 있는 커스텀 익셉션
	}
}
