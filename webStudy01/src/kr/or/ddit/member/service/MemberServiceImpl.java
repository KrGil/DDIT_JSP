package kr.or.ddit.member.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private IAuthenticateService authService = new AuthenticateServiceImpl();
	private IMemberDAO dao = new MemberDAOImpl();
	@Override
	public MemberVO retrieveMember(String mem_id) {
		MemberVO savedMember = dao.selectMemberDetail(mem_id);
		if(savedMember==null) {
			// custom exception 발생
			// compile error가 발생하면 checked exception이다. ex)Exceiption()
			throw new UserNotFoundException("아이디에 해당하는 회원이 존재하지 않음.");
		}
		return savedMember;
		// 특정 상황에 쓸 수 있는 커스텀 익셉션
	}
 
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
		retrieveMember(member.getMem_id());
		
		ServiceResult result = authService.authenticate(
				new MemberVO(member.getMem_id(), member.getMem_pass()));
		
		if(ServiceResult.OK.equals(result)) {
			int rowcnt = dao.updateMember(member);
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}
		return result;
	}
	
	@Override
	public ServiceResult removeMember(MemberVO member) {
		retrieveMember(member.getMem_id());
		
		ServiceResult result = authService.authenticate(
				new MemberVO(member.getMem_id(), member.getMem_pass()));
		if(ServiceResult.OK.equals(result)) {
			int rowcnt = dao.deleteMember(member.getMem_id());
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}
		return result;
	}
	@Override
	public List<MemberVO> retrieveMemberList() {
		return dao.selectMeberList();
	}
}
