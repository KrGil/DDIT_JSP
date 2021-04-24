package kr.or.ddit.commons.service;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZipVO;

public interface ISearchZipService {
	public int retrieveZipCount(PagingVO<ZipVO> pagingVO);
	public List<ZipVO> retrieveZipList(PagingVO<ZipVO> pagingVO);
}
