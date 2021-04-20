package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board.dao.AttatchDAOImpl;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IAttatchDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.utils.CryptoUtil;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements IBoardService {
	
    IBoardDAO boardDAO = BoardDAOImpl.getInstance();
	IAttatchDAO attatchDAO = AttatchDAOImpl.getInstance();
	private File saveFolder = new File("D:/attatches");
	private SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();
	
	//singleton
	private static BoardServiceImpl self;
	private BoardServiceImpl() {}
	public static BoardServiceImpl getInstance() {
		if(self==null) self = new BoardServiceImpl();
		return self;
	}
	
	private void encodePassword(BoardVO board) {
		String bo_pass = board.getBo_pass();
		// 공지글 작성 시 비번 암호화는 필요가 없다.
		if(StringUtils.isBlank(bo_pass)) return;
		
		try {
			String encodedPass = CryptoUtil.sha512(bo_pass);
			board.setBo_pass(encodedPass);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	private int processes(BoardVO board, SqlSession session) {
		int cnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList!=null && attatchList.size()>0) {
			cnt += attatchDAO.insertAttatches(board, session);
			try {
				for(AttatchVO attatch : attatchList) {
//					if(1==1)
//						throw new RuntimeException("강제 발생 예외");
					attatch.saveTo(saveFolder);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return cnt;
	}
	private int deleteFileProcesses(BoardVO board, SqlSession session) {
		int[] delAttNos = board.getDelAttNos();
		int cnt = 0;
		if(delAttNos!=null && delAttNos.length > 0) {
			List<String> saveNames = 
					attatchDAO.selectSaveNamesForDelete(board);
			// 첨부파일의 메타 데이터 삭제
			attatchDAO.deleteAttatches(board, session);
			// 이진 데이터 삭제
			for(String saveName : saveNames) {
				File saveFile = new File(saveFolder, saveName);
				saveFile.delete();
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
		try(
			SqlSession session = sessionFactory.openSession(false);
		){
			int cnt = boardDAO.insertBoard(board, session);
			if(cnt > 0) {
				//==========첨부파일 처리==========
				cnt += processes(board, session);
				//==============================
				if(cnt > 0) {
					result = ServiceResult.OK;
					session.commit();
				}
			}
		}// try end 
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
		try(
			SqlSession session = sessionFactory.openSession(false);
			){
			// 게시글 존재 여부 확인
			// 비번 인증
			// 인증 성공시
			// 게시글의 일반 데이터 수정
			ServiceResult result = ServiceResult.INVALIDPASSWORD;
			encodePassword(board);
			int cnt = boardDAO.updateBoard(board, session);
			if(cnt>0) {
				// 신규 파일에 대한 등록
			cnt += processes(board, session);
				// 삭제할 파일에 대한 처리
				cnt += deleteFileProcesses(board, session);
				if(cnt > 0) {
					result = ServiceResult.OK;
					session.commit();
				}
			}
			return result;
		}// try end
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
























