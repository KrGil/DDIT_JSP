package kr.or.ddit.login.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.login.dao.IEmployeeDAO;
import kr.or.ddit.vo.EmployeeVO;

@Service
public class AuthenticateServiceImpl implements IAuthenticateService{

	@Inject
	private IEmployeeDAO dao;
	
	@Override
	public ServiceResult authenticate(EmployeeVO empVO) {
		EmployeeVO saveEmpl = dao.selectEmployeeForAuth(empVO);
		ServiceResult result = null;
		if(saveEmpl!=null) {
			String inputPass = empVO.getEmployee_pwd();
			
		}
		return null;
	}
	
}
