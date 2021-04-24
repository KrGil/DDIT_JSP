package kr.or.ddit.buyer.service;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;

public class AuthenticateServiceImpl implements IAuthenticateService{
	private static final Logger logger = LoggerFactory.getLogger(AuthenticateServiceImpl.class);
	private IBuyerDAO dao = BuyerDAOImpl.getInstance();
	
	// 한번 검증 거치기.
	@Override
	public ServiceResult authenticate(BuyerVO buyer) {
		BuyerVO savedMember =  dao.selectBuyerForAuth(buyer.getBuyer_id());
		ServiceResult result = null;
		if(savedMember!=null){ 
//				String encodedPass= CryptoUtil.sha512(inputPass);
			String encodedPass= null;
//				String savedPass = savedMember.getMem_pass();
			String savedPass = null;
			if(savedPass.equals(encodedPass)) { // 검증
				try {
					BeanUtils.copyProperties(buyer, savedMember);
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
