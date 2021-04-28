package kr.or.ddit.member.service;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.utils.CryptoUtil;
import kr.or.ddit.vo.MemberVO;

@Service
public class AuthenticateServiceImpl implements IAuthenticateService {
	private static final Logger logger =
			LoggerFactory.getLogger(AuthenticateServiceImpl.class);
	@Inject
	private IMemberDAO dao;
	
	@Override
	public ServiceResult authenticate(MemberVO member) {
		MemberVO savedMember = dao.selectMemberForAuth(member.getMem_id());
		ServiceResult result = null;
		if(savedMember!=null) {
			String inputPass = member.getMem_pass();
			try {
				String encodedPass = CryptoUtil.sha512(inputPass);
				String savedPass = savedMember.getMem_pass();
				if(savedPass.equals(encodedPass)) {
					try {
						BeanUtils.copyProperties(member, savedMember);
					} catch (IllegalAccessException | InvocationTargetException e) {
						throw new RuntimeException(e);
					}
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.INVALIDPASSWORD;
				}
			}catch (Exception e) {
				logger.error("", e);
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

}










