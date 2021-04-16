package kr.or.ddit.board.dao;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

/**
 * 	첨부파일 관리를 위한 persistence layer
 *
 */
public interface IAttatchDAO {
	public int insertAttatches(BoardVO board);
	/**
	 * 첨부파일을 올릴 때 다운받을 시 조회
	 */
	public AttatchVO selectAttatch(int att_no);
	
	// 일부만 변경되는경우가 없기 때문에 update가 없다.
	// 그러니 삭제 후 새로 넣는것만 하자.
	// BoardVO 에 cascade를 넣지 않는 이유 
	// -> middleTier에 저장된 파일을 찾을 수가 없기 때문.
	public int deleteAttatches(BoardVO board);
}
