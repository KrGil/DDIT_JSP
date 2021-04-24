package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.UserNotFoundException;
import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerServiceImpl implements IBuyerService {
	private IBuyerDAO dao = BuyerDAOImpl.getInstance();
	private IAuthenticateService authService = new AuthenticateServiceImpl();

	@Override
	public BuyerVO retrieveBuyer(String buyer_id) {
		BuyerVO savedMember = dao.selectBuyerDetail(buyer_id);
		if (savedMember == null) {
			// custom exception 발생
			// compile error가 발생하면 checked exception이다. ex)Exceiption()
			throw new UserNotFoundException("아이디에 해당하는 회원이 존재하지 않음.");
		}
		return savedMember;
		// 특정 상황에 쓸 수 있는 커스텀 익셉션
	}

	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		ServiceResult result = null;
		if (dao.selectBuyerDetail(buyer.getBuyer_id()) == null) { // 중복되지 않는다면
			int rowcnt = dao.insertBuyer(buyer);
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
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		retrieveBuyer(buyer.getBuyer_id());

		ServiceResult result =null;

		if (ServiceResult.OK.equals(result)) {
			int rowcnt = dao.updateBuyer(buyer);
			if (rowcnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAIL;
			}
		}
		return result;
	}

	@Override
	public ServiceResult removeBuyer(BuyerVO buyer) {
		retrieveBuyer(buyer.getBuyer_id());

		ServiceResult result =null;
		if (ServiceResult.OK.equals(result)) {
			int rowcnt = dao.deleteBuyer(buyer.getBuyer_id());
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
