package kr.or.ddit.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

/**
 * 첨부파일 관리를 위한 persistence layer
 *
 */
@Repository
public interface IAttatchDAO {
	public int insertAttatches(BoardVO board);
	public AttatchVO selectAttatch(int att_no);
	public List<String> selectSaveNamesForDelete(BoardVO board);
	public int deleteAttathes(BoardVO board);
}
