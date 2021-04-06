package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 검색 조건과 검색 키워드를 이용한 단순 검색에 사용.
 *
 */
@Data
@AllArgsConstructor
public class SearchVO {
	private String searchType;
	private String searchWord;
}
