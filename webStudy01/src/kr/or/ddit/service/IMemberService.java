package kr.or.ddit.service;

import java.sql.SQLException;

import kr.or.ddit.vo.MemberVO;

public interface IMemberService {
	/**
	 * @param mem_id
	 * @return
	 * @throws SQLException
	 */
//	public MemberVO retrieveMember(String mem_id) throws SQLException;
	public MemberVO retrieveMember(String mem_id);
}
