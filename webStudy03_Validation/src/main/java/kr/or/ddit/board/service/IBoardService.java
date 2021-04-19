package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardService {
	public ServiceResult createBoard(BoardVO board);
	public int retrieveBoardCount(PagingVO<BoardVO> pagingVO);
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO);
	/**
	 * 
	 * @param search
	 * @return 존재하지 않는 경우 custom exception
	 */
	public BoardVO retrieveBoard(BoardVO search);
	/**
	 * 
	 * @param board
	 * @return 있느냐 없느냐, 검증(암호화)
	 */
	public ServiceResult modifyBoard(BoardVO board);
	/**
	 * 
	 * @param search
	 * @return 존재하면 
	 */
	public ServiceResult removeBoard(BoardVO search);
	/**
	 * 
	 * @param att_no
	 * @return AttatchVO
	 */
	public AttatchVO download(int att_no);
	/**
	 *  인증 여부 확인
	 * @param BoardVO
	 * @return boolean
	 */
	public boolean boardAuthenticate(BoardVO search);
}
