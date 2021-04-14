package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor // 기본생성자
public class PagingVO<T> implements Serializable{
	
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int totalRecord;
	private int screenSize = 10;
	private int blockSize = 5;
	private int currentPage;
	
	private int totalPage;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	private List<T> dataList;
	
	private SearchVO simpleSearch;

	private T detailSearch;
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = totalRecord % screenSize == 0?
				totalRecord/screenSize :
				totalRecord/screenSize +1;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		startRow = (currentPage - 1)*screenSize + 1;
		endRow = currentPage * screenSize;
		endPage = (currentPage + (blockSize-1))/blockSize * blockSize;
		startPage = endPage - (blockSize  - 1);
	}
	
	private static String aPattern = "<a href='#' data-page = '%d'>[%s]</a>";
	private static String currentPagePtrn = "<a href='#'>[%s]</a>";
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		if(startPage > 1) {
			html.append(
				String.format(aPattern, (startPage-1), "이전")
			);
		}
		endPage = endPage < totalPage ? endPage : totalPage;
		for(int page=startPage; page<=endPage; page++) {
			if(page == currentPage) {
				html.append(
					String.format(currentPagePtrn, page+"")
				);
			}else {
				html.append(
					String.format(aPattern, page, page+"")
				);
			}
		}
		if(endPage < totalPage) {
			html.append(
				String.format(aPattern, (endPage +1), "다음")
			);
		}
		return html.toString();
	}
	
}
