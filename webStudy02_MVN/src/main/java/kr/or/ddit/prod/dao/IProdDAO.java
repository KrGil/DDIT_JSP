package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
/**
 * 
 * 상품관리를 위한 Persistence Layer
 *
 */
public interface IProdDAO {
	public ProdVO selectProd(String prod_id);
	public List<ProdVO> selectProdList();
	public int insertProd(ProdVO prod); // row cnt가 return
	public int updateProd(ProdVO prod);
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return 조건에 맞는 회원이 없다면, size() == 0
	 */
	public List<ProdVO> selectProdList(PagingVO pagingVO);
	
	public int selectTotalRecord(PagingVO<ProdVO> pagingVO);
}
