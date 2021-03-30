package kr.or.ddit.member.dao;

import kr.or.ddit.vo.MemberVO;

/**
 *  회원 관리 및 인증을 위한 Persistence Layer
 *	
 */
public interface IMemberDAO {
	/**
	 * PK를 기준으로 한명의 회원 조회(인증용)
	 * @param mem_id
	 * @return 존재하지 않는 경우, null 반환.
	 */
	public MemberVO selectMemberForAuth(String mem_id);
	/**
	 * 회원 정보 상세 조회
	 * @param mem_id
	 * @return 존재하지 않는 경우, null 반환.
	 */
	public MemberVO selectMemberDetail(String mem_id);
	
}
