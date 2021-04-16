package kr.or.ddit.board.dao;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

public class AttatchDAOImpl implements IAttatchDAO{
	private static AttatchDAOImpl self;
	private AttatchDAOImpl() {}
	public static AttatchDAOImpl getInstance() {
		if(self==null) self = new AttatchDAOImpl();
		return self;
	}
	
	
	@Override
	public int insertAttatches(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AttatchVO selectAttatch(int att_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAttatches(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

}
