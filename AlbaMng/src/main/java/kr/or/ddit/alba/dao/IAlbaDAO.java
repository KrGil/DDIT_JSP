package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public interface IAlbaDAO {
	public int insertAlba(AlbaVO alba, SqlSession session);
	public int selectAlbaCount(PagingVO<AlbaVO> pagingVO);
	public List<AlbaVO> selectAlbaList(PagingVO<AlbaVO> pagingVO);
	public AlbaVO selectAlba(String al_id);
	public int updateAlba(AlbaVO alba, SqlSession session);
	public int deleteAlba(String al_id, SqlSession session);
}
