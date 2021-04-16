package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.AttatchDAOImpl;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IAttatchDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements IBoardService{
	IBoardDAO boardDao = BoardDAOImpl.getInstance();
	IAttatchDAO attatchDao = AttatchDAOImpl.getInstance();
	
	//singleton
	private static BoardServiceImpl self;
	private BoardServiceImpl() {}
	public static BoardServiceImpl getInstance() {
		if(self==null) self = new BoardServiceImpl();
		return self;
	}
	
	@Override
	public ServiceResult createBoard(BoardVO baord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int retrieveBoardCount(PagingVO<BoardVO> pagingVO) {
		return boardDao.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO) {
		return	boardDao.selectBoardList(pagingVO);
	}

	@Override
	public BoardVO retrieveBoard(BoardVO search) {
		return boardDao.selectBoard(search);
	}

	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeBoard(BoardVO search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttatchVO download(int att_no) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
