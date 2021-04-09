package kr.or.krgil.buyer.service;

import java.util.List;

import kr.or.krgil.buyer.UserNotFoundException;
import kr.or.krgil.buyer.dao.BuyerDAOImpl;
import kr.or.krgil.buyer.dao.IBuyerDAO;
import kr.or.krgil.enumpkg.ServiceResult;
import kr.or.krgil.vo.BuyerVO;
import kr.or.krgil.vo.PagingVO;

public class BuyerServiceImpl implements IBuyerService {
	private IBuyerDAO dao = BuyerDAOImpl.getInstance();

	@Override
	public BuyerVO retrieveBuyer(String Buyer_id) {
		BuyerVO savedBuyer = dao.selectBuyerDetail(Buyer_id);
		if (savedBuyer == null) {
			// custom exception 발생
			// compile error가 발생하면 checked exception이다. ex)Exceiption()
			throw new UserNotFoundException("아이디에 해당하는 회원이 존재하지 않음.");
		}
		return savedBuyer;
		// 특정 상황에 쓸 수 있는 커스텀 익셉션
	}

	@Override
	public ServiceResult createBuyer(BuyerVO Buyer) {
		ServiceResult result = null;
		if (dao.selectBuyerDetail(Buyer.getBuyer_id()) == null) { // 중복되지 않는다면
			int rowcnt = dao.insertBuyer(Buyer);
			if (rowcnt > 0) { // 성공
				result = ServiceResult.OK;
			} else { // 실패
				result = ServiceResult.FAIL;
			}
		} else { // 중복
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO Buyer) {
		retrieveBuyer(Buyer.getBuyer_id());

		ServiceResult result = null;
		if (ServiceResult.OK.equals(result)) {
			int rowcnt = dao.updateBuyer(Buyer);
			if (rowcnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAIL;
			}
		}
		return result;
	}

	@Override
	public ServiceResult removeBuyer(BuyerVO Buyer) {
		retrieveBuyer(Buyer.getBuyer_id());
		ServiceResult result = null;
		if (ServiceResult.OK.equals(result)) {
			int rowcnt = dao.deleteBuyer(Buyer.getBuyer_id());
			if (rowcnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAIL;
			}
		}
		return result;
	}

	@Override
	public List<BuyerVO> retrieveBuyerList(PagingVO pagingVO) {
		return dao.selectBuyerList(pagingVO);
	}

	@Override
	public int retrieveBuyerCount(PagingVO<BuyerVO> pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}
}
