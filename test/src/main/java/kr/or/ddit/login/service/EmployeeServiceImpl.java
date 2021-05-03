package kr.or.ddit.login.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.login.dao.IEmployeeDAO;
import kr.or.ddit.vo.EmployeeVO;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Inject
	IEmployeeDAO dao;
	
	@Override
	public EmployeeVO retrieveEmployee(EmployeeVO empVO) {
		
		return null;
	}

	@Override
	public ServiceResult createEmployee(EmployeeVO empVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyEmployee(EmployeeVO empVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeEmployee(EmployeeVO empVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeVO> retrieveEmployeeList() {
		// TODO Auto-generated method stub
		return null;
	}

}
