package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
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
		if(selectedProd == null) {
			throw new CustomException();
		}
		return selectedProd;
	}
	@Override
	public List<ProdVO> retrieveProdList() {
		List<ProdVO> prodList = dao.selectProdList();
		if(prodList==null) {
			throw  new CustomException();
		}
		return prodList;
	}
	@Override
	public ServiceResult createProd(ProdVO prod) {
		int rowcnt = dao.insertProd(prod);
		ServiceResult result = null;
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAIL;
		}
		return result;
	}
	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ProdVO> retrieveProdList(PagingVO pagingVO) {
		return dao.selectProdList(pagingVO);
	}
	@Override
	public int retrieveProdCount(PagingVO<ProdVO> pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}

}
