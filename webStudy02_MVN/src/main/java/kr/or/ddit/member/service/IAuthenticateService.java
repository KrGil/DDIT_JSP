package kr.or.ddit.member.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

public interface IAuthenticateService {
	public ServiceResult authenticate(MemberVO member);
}
