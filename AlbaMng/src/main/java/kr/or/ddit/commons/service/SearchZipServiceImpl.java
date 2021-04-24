package kr.or.ddit.commons.service;

import java.util.List;

import kr.or.ddit.commons.dao.ISearchZipDAO;
import kr.or.ddit.commons.dao.SearchZipDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZipVO;

public class SearchZipServiceImpl implements ISearchZipService {
	private SearchZipServiceImpl() { }
	private static SearchZipServiceImpl self;
	public static SearchZipServiceImpl getInstance() {
		if(self==null) self = new SearchZipServiceImpl();
		return self;
	}
	private ISearchZipDAO dao = SearchZipDAOImpl.getInstance();
	
	@Override
	public int retrieveZipCount(PagingVO<ZipVO> pagingVO) {
		return dao.selectZipCount(pagingVO);
	}
	
	@Override
	public List<ZipVO> retrieveZipList(PagingVO<ZipVO> pagingVO) {
		return dao.selectZipList(pagingVO);
	}

}
