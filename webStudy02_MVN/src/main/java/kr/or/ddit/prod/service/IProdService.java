package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 
 * 상품관리를 위한 Business Logic Layer
 *
 */
public interface IProdService {
	/**
	 * 상품 상세 조회
	 * @param prod_id
	 * @return 해당 상품이 존재하지 않는 경우, CustomException 발생
	 */
	public ProdVO retrieveProd(String prod_id);
	/**
	 * 상품 리스트 조회
	 * @param 
	 * @return 해당 상품이 존재하지 않는 경우, CustomException 발생
	 */
	public List<ProdVO> retrieveProdList();
	/**
	 * 상품 만들기
	 * @param ProdVO
	 * @return 성공, 실패, 해당 상품이 존재하지 않는 경우, CustomException 발생
	 */
	public ServiceResult createProd(ProdVO prod);
	/**
	 * 상품 업데이트
	 * @param ProdVO
	 * @return 성공, 실패, 해당 상품이 존재하지 않는 경우, CustomException 발생
	 */
	public ServiceResult modifyProd(ProdVO prod);
	
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없으면, size()==0
	 */
	public List<ProdVO> retrieveProdList(PagingVO pagingVO);
	
	/**
	 *  페이징 처리를 위한 회원수 조회
	 * @param pagingVO TODO
	 * @return
	 */
	public int retrieveProdCount(PagingVO<ProdVO> pagingVO);
}
