package kr.or.ddit.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
//	@Override
//	public ServiceResult createMember(MemberVO member) {
//		int cnt =  dao.insertMember(member);
//		MemberVO savedMember = dao.selectMemberDetail(member.getMem_id());
//		System.out.println("createMember_savedMember : "+savedMember);
//		ServiceResult result = null;
//		if(cnt==0){ // 실패
//			if(savedMember != null) { // 조회 후 값이 존재한다면
//				result = ServiceResult.PKDUPLICATED;
//			}else {
//				result = ServiceResult.FAIL;
//			}
//		}else { // 성공
//			result = ServiceResult.OK;
//		}
//		return result;
//	}
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(dao.selectMemberDetail(member.getMem_id())==null) { // 중복되지 않는다면
			int rowcnt = dao.insertMember(member);
			if(rowcnt>0) { // 성공
				result = ServiceResult.OK;
			}else { // 실패
				result = ServiceResult.FAIL;
			}
		}else { // 중복
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}
	
	@Override
	public ServiceResult modifyMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ServiceResult removeMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<MemberVO> retrieveMemberList() {
		// TODO Auto-generated method stub
		return null;
	}
}
