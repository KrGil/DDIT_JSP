package kr.or.ddit.prod.dao;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;

public interface IProdDAO {
	public ProdVO selectProd(String prod_id);
}
