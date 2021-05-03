package kr.or.ddit.login.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;

public interface IEmployeeService {
	public EmployeeVO retrieveEmployee(EmployeeVO empVO);
	public ServiceResult createEmployee(EmployeeVO empVO);
	public ServiceResult modifyEmployee(EmployeeVO empVO);
	public ServiceResult removeEmployee(EmployeeVO empVO);
	public List<EmployeeVO> retrieveEmployeeList();
}
