package kr.or.ddit.login.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.EmployeeVO;

@Repository
public interface IEmployeeDAO {
	/**
	 * 검증용 확인(pk)
	 * @param empVO
	 * @return 존재하지 않는 경우 null
	 */
	public EmployeeVO selectEmployeeForAuth(EmployeeVO empVO);
	
	public EmployeeVO selectEmployeeDetail(EmployeeVO empVO);
	
//	public List<EmployeeVO> selectEmployeeList(PagingVO pagingVO);
}
