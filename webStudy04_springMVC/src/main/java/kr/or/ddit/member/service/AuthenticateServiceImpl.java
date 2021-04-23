package kr.or.ddit.member.service;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.core.net.Severity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.utils.CryptoUtil;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements IAuthenticateService{
	private static final Logger logger = LoggerFactory.getLogger(AuthenticateServiceImpl.class);
	private IMemberDAO dao = MemberDAOImpl.getInstance();
	
	// 한번 검증 거치기.
	@Override
	public ServiceResult authenticate(MemberVO member) {
		MemberVO savedMember =  dao.selectMemberForAuth(member.getMem_id());
		ServiceResult result = null;
		if(savedMember!=null){ 
			String inputPass = member.getMem_pass();
			try {
				String encodedPass= CryptoUtil.sha512(inputPass);
				String savedPass = savedMember.getMem_pass();
				if(savedPass.equals(encodedPass)) { // 검증
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
			} catch (NoSuchAlgorithmException e) {
				logger.error("", e);
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}
}
