package kr.or.ddit.buyer.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;

public interface IAuthenticateService {
	public ServiceResult authenticate(BuyerVO buyer);
}
