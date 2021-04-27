package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.IAttatchDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.utils.CryptoUtil;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class BoardServiceImpl implements IBoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
    IBoardDAO boardDAO ;
    @Inject
	public void setBoardDAO(IBoardDAO boardDAO) {
		this.boardDAO = boardDAO;
		logger.info("주입된 boardDAO : {}\n\n\n\n", boardDAO.getClass().getName());
	}
	IAttatchDAO attatchDAO ;
	@Inject
	public void setAttatchDAO(IAttatchDAO attatchDAO) {
		this.attatchDAO = attatchDAO;
		logger.info("주입된 attatchDAO : {}\n\n\n\n", attatchDAO.getClass().getName());
	}
	
	@Value("#{appInfo.attatchPath}")
	private String attatchPath;
	
	//Spring에서는 File보다 resource를 쓴다
	private File saveFolder;
	@Resource(name="appInfo")
	Properties appInfo;
	
	@PostConstruct
	public void init() {
		saveFolder = new File(attatchPath);
		logger.info("{} 초기화, {} 주입됨."
					, getClass().getSimpleName(), saveFolder.getAbsolutePath());
	}
	
	private void encodePassword(BoardVO board) {
		String bo_pass = board.getBo_pass();
		if(StringUtils.isBlank(bo_pass)) return;
		try {
			String encodedPass = CryptoUtil.sha512(bo_pass);
			board.setBo_pass(encodedPass);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	private int processes(BoardVO board) {
		int cnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList!=null && attatchList.size()>0) {
			cnt += attatchDAO.insertAttatches(board);
			
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
	
	private int deleteFileProcesses(BoardVO board) {
		int[] delAttNos = board.getDelAttNos();
		int cnt = 0;
		if(delAttNos!=null && delAttNos.length > 0) {
			List<String> saveNames = 
					attatchDAO.selectSaveNamesForDelete(board);
			// 첨부파일의 메타 데이터 삭제
			attatchDAO.deleteAttatches(board);
			// 이진 데이터 삭제
			for(String saveName : saveNames) {
				File saveFile = new File(saveFolder, saveName);
				saveFile.delete();
			}
		}
		return cnt;
	}
	// 선언적 프로그래밍
	@Transactional
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
			if(cnt > 0) {
				result = ServiceResult.OK;
			}	
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
		boardDAO.incrementHit(board.getBo_no());
		return board;
	}

	@Transactional
	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		// 게시글 존재 여부 확인
		// 비번 인증
		// 인증 성공시
		// 게시글의 일반 데이터 수정
		ServiceResult result = ServiceResult.INVALIDPASSWORD;
		encodePassword(board);
		int cnt = boardDAO.updateBoard(board);
		if(cnt>0) {
			// 신규 파일에 대한 등록
		cnt += processes(board);
			// 삭제할 파일에 대한 처리
			cnt += deleteFileProcesses(board);
			if(cnt > 0) {
				result = ServiceResult.OK;
			}
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult removeBoard(BoardVO search) {
			ServiceResult result = ServiceResult.FAIL;
			BoardVO savedBoard = boardDAO.selectBoard(search);
			encodePassword(search);
			
			String savedPass = savedBoard.getBo_pass();
			String inputPass = search.getBo_pass();
			
			// 인증
			if(savedPass.equals(inputPass)) {
				// 첨부파일 삭제
				List<AttatchVO> attatchList 
					= savedBoard.getAttatchList();
				if(attatchList.size()>0) {
					int[] delAttNos = new int[attatchList.size()];
					search.setDelAttNos(delAttNos);
					for(int i = 0; i < delAttNos.length; i++) {
						delAttNos[i] = 
								attatchList.get(i).getAtt_no();
					}	
					deleteFileProcesses(search);
				}// if(attatchList.size) end
				
				// 게시글 삭제
				int cnt = boardDAO.deleteBoard(search);
				if(cnt>0) {
					result = ServiceResult.OK;
				}
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
			return result;
	}

	@Override
	public AttatchVO download(int att_no) {
		AttatchVO attatch = attatchDAO.selectAttatch(att_no);
		if(attatch==null)
			throw new CustomException( att_no+" 에 해당하는 파일이 없음.");
		return attatch;
	}

	
	@Override
	public boolean boardAuthenticate(BoardVO search) {
		BoardVO saved = boardDAO.selectBoard(search);
		encodePassword(search);
		String savedPass = saved.getBo_pass();
		String inputPass = search.getBo_pass();
		return savedPass.equals(inputPass);
	}
	@Override
	public ServiceResult recommend(int bo_no) {
		ServiceResult result = ServiceResult.FAIL;
		int cnt = boardDAO.incrementRcmd(bo_no);
		if(cnt > 0)
			result = ServiceResult.OK;
		return result;
	}

	@Override
	public ServiceResult report(int bo_no) {
		ServiceResult result = ServiceResult.FAIL;
		int cnt = boardDAO.incrementRpt(bo_no);
		if(cnt > 0)
			result = ServiceResult.OK;
		return result;
	}
}


