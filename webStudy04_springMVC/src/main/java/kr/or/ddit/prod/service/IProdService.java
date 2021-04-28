package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리를 위한  Business Logic Layer
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
	 * 페이징 적용된 상품 목록 조회
	 * @param pagingVO
	 * @return
	 */
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVO);
	/**
	 * 페이징 적용된 상품 건수
	 * @param pagingVO TODO
	 * @return
	 */
	public int retrieveProdCount(PagingVO<ProdVO> pagingVO);
	/**
	 * 신규 상품 등록
	 * @param prod
	 * @return
	 */
	public ServiceResult createProd(ProdVO prod);
	/**
	 * 상품 수정
	 * @param prod
	 * @return 존재하지 않는 경우, CustomException 발생
	 */
	public ServiceResult modifyProd(ProdVO prod);
}












