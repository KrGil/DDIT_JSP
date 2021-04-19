package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import kr.or.ddit.board.dao.AttatchDAOImpl;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IAttatchDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.utils.CryptoUtil;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements IBoardService {
	
    IBoardDAO boardDAO = BoardDAOImpl.getInstance();
	IAttatchDAO attatchDAO = AttatchDAOImpl.getInstance();
	
	//singleton
	private static BoardServiceImpl self;
	private BoardServiceImpl() {}
	public static BoardServiceImpl getInstance() {
		if(self==null) self = new BoardServiceImpl();
		return self;
	}
	
	private void encodePassword(BoardVO board) {
		String bo_pass = board.getBo_pass();
		try {
			String encodedPass = CryptoUtil.sha512(bo_pass);
			board.setBo_pass(encodedPass);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	private int processes(BoardVO board) {
		File saveFolder = new File("D:/attatches");
		int cnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList!=null && attatchList.size()>0) {
			cnt += attatchDAO.insertAttatches(board);
			
			try {
				for(AttatchVO attatch : attatchList) {
					attatch.saveTo(saveFolder);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return cnt;
	}
	
	@Override
	public ServiceResult createBoard(BoardVO board) {
		ServiceResult result = ServiceResult.FAIL;
		//==========비밀번호 암호화==========
		encodePassword(board);
		//===============================
		
		int cnt = boardDAO.insertBoard(board);
		if(cnt > 0) {
			//==========첨부파일 처리==========
			cnt += processes(board);
			//==============================
			if(cnt > 0)
				result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public int retrieveBoardCount(PagingVO<BoardVO> pagingVO) {
		return boardDAO.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO) {
		return boardDAO.selectBoardList(pagingVO);
	}

	@Override
	public BoardVO retrieveBoard(BoardVO search) {
		BoardVO board = boardDAO.selectBoard(search);
		if(board==null)
			throw new CustomException(search.getBo_no()+"에 해당하는 게시글이 없음");
		return board;
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

	
	@Override
	public boolean boardAuthenticate(BoardVO search) {
		BoardVO saved = boardDAO.selectBoard(search);
		encodePassword(search);
		String savedPass = saved.getBo_pass();
		String inputPass = search.getBo_pass();
		return savedPass.equals(inputPass);
	}
}
























