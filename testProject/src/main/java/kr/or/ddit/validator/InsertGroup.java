package kr.or.ddit.validator;

import javax.validation.groups.Default;

// 신규로 등록할 때만 사용된다. 기본그룹임과 동시에 insert그룹
public interface InsertGroup extends Default{
	
}
