package kr.or.ddit.prod.service;

import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService{
	private IProdDAO dao = ProdDAOImpl.getInstance();
	
	// singletone
	private static ProdServiceImpl self;
	private ProdServiceImpl() {};
	public static ProdServiceImpl getInstance() {
		if(self == null) self = new ProdServiceImpl();
		return self;
	}
	
	@Override
	public ProdVO retrieveProd(String prod_id) {
		ProdVO selectedProd = dao.selectProd(prod_id);
//		if(selectedProd == null) {
//			throw new UserNotFoundException("아이디에 해당하는 상품이 존재하지 않음");
//		}
		return selectedProd;
	}

}
