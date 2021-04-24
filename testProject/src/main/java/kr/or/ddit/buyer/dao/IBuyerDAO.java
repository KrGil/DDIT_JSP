package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

/**
 *  회원 관리(CRUD) 및 인증을 위한 Persistence Layer
 *	
 */
public interface IBuyerDAO {
	/**
	 * PK를 기준으로 한명의 회원 조회(인증용)
	 * @param mem_id
	 * @return 존재하지 않는 경우, null 반환.
	 */
	public BuyerVO selectBuyerForAuth(String buyer_id);
	
	/**
	 * 회원 정보 상세 조회
	 * @param mem_id
	 * @return 존재하지 않는 경우, null 반환.
	 */
	public BuyerVO selectBuyerDetail(String buyer_id);
	
	/**
	 * 신규 등록
	 * @param member
	 * @return 등록된 row count > 0 성공
	 */
	public int insertBuyer(BuyerVO buyer);
	
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 수정된 row count > 0 성공
	 */
	public int updateBuyer(BuyerVO buyer);
	
	/**
	 * 회원 정보 삭제(???)
	 * @param mem_id
	 * @return 삭제된 row count > 0 성공
	 */
	public int deleteBuyer(String buyer_id);
	
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없다면, size() == 0
	 */
	public List<BuyerVO> selectBuyerList(PagingVO pagingVO);
	
	public int selectTotalRecord(PagingVO pagingVO);
}


















