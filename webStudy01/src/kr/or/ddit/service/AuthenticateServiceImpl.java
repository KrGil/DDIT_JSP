package kr.or.ddit.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements IAuthenticateService{
	private IMemberDAO dao = new MemberDAOImpl();
	
	@Override
	public ServiceResult authenticate(MemberVO member) {
		MemberVO savedMember =  dao.selectMemberForAuth(member.getMem_id());
		ServiceResult result = null;
		if(savedMember!=null){
			String inputPass = member.getMem_pass();
			String savedPass = savedMember.getMem_pass();
			if(savedPass.equals(inputPass)) {
				try {
					BeanUtils.copyProperties(member, savedMember);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// throws 가 없으면 uncheced exception으로 해야 server까지 간다.
					throw new RuntimeException(e);
					// e를 잊지말자 이 exception에 대한 정보이다.
				}
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}
}
