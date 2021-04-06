package kr.or.ddit.mybatis;

import java.util.List;

import kr.or.ddit.vo.DBPropertyVO;

public interface IDBPropertyDAO {
	public List<DBPropertyVO> selectDBPropertyList();
}
