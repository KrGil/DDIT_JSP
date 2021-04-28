package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원 관리(CRUD)를 위한 Business Logic Layer
 *
 */
public interface IMemberService {
	/**
	 * 회원 정보 상세 조회
	 * @param mem_id
	 * @return 존재하지 않으면, custom exception 발생
	 */
	public MemberVO retrieveMember(String mem_id);
	
	/**
	 * 신규 등록
	 * @param member
	 * @return PKDUPLICATED, OK, FAIL
	 */
	public ServiceResult createMember(MemberVO member);
	
	/**
	 * 자기 정보 수정
	 * @param member
	 * @return  존재하지 않으면, custom exception 발생, 
	 * 			INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult modifyMember(MemberVO member);
	
	/**
	 * 회원 탈퇴
	 * @param member
	 * @return 존재하지 않으면, custom exception 발생, 
	 * 		   INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult removeMember(MemberVO member);
	
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없으면, size()==0
	 */
	public List<MemberVO> retrieveMemberList(PagingVO pagingVO);
	/**
	 * 페이징 처리를 위한 회원수 조회
	 * @param paingVO TODO
	 * @return
	 */
	public int retrieveMemberCount(PagingVO<MemberVO> paingVO);
}













