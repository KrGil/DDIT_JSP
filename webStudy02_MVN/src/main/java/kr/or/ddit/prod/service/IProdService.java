package kr.or.ddit.prod.service;

import kr.or.ddit.vo.ProdVO;

public interface IProdService {
	/**
	 * 상품 상세 조회
	 * @param prod_id
	 * @return 해당 상품이 존재하지 않는 경우, RuntimeException 발생
	 */
	public ProdVO retrieveProd(String prod_id);
}
