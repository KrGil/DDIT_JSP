package kr.or.krgil.buyer.service;

import java.util.List;

import kr.or.krgil.enumpkg.ServiceResult;
import kr.or.krgil.vo.BuyerVO;
import kr.or.krgil.vo.PagingVO;

/**
 * 
 * 회원 관리(CRUD)를 위한 Business Layer
 *
 */
public interface IBuyerService {
	/**
	 * 회원 정보 상세 조회
	 * @param mem_id
	 * @return 존재하지 않으면, custom exception  발생,
	 * 			INVALIDPASSWORD, OK, FAIL
	 */
	public BuyerVO retrieveBuyer(String buyer_id);
	// enum 을 가지고 식별성을 가진 녀석을 return으로ㅓ 내보낼 수 있다.
	/**
	 * 신규 등록
	 * @param Buyer
	 * @return PKDUPLICATED, OK, FAIL
	 */
	public ServiceResult createBuyer(BuyerVO buyer_id);
	
	/**
	 * 자기 정보 수정
	 * @param Buyer
	 * @return INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult modifyBuyer(BuyerVO buyer_id);
	
	/**
	 * 회원 탈퇴
	 * @param Buyer
	 * @return 존재하지 않으면, custom exception  발생,
	 * 			INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult removeBuyer(BuyerVO buyer_id);
	
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없으면, size()==0
	 */
	public List<BuyerVO> retrieveBuyerList(PagingVO pagingVO);
	
	/**
	 *  페이징 처리를 위한 회원수 조회
	 * @param pagingVO TODO
	 * @return
	 */
	public int retrieveBuyerCount(PagingVO<BuyerVO> pagingVO);
}






