package kr.or.ddit.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원 관리(CRUD) 및 인증을 위한 Persistence Layer
 *
 */
@Repository
public interface IMemberDAO {
	/**
	 * PK 를 기준으로 한명의 회원 조회(인증용)
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
	
	/**
	 * 신규 등록
	 * @param member
	 * @return 등록된 row count > 0 성공
	 */
	public int insertMember(MemberVO member);
	
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 수정된 row count > 0 성공
	 */
	public int updateMember(MemberVO member);
	
	/**
	 * 회원 정보 삭제(???)
	 * @param mem_id
	 * @return 삭제된 row count > 0 성공
	 */
	public int deleteMember(String mem_id);
	
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없다면, size()==0
	 */
	public List<MemberVO> selectMemberList(PagingVO pagingVO);
	
	public int selectTotalRecord(PagingVO<MemberVO> pagingVO);
}













