package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
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
	
	private Map<String, Object> searchMap;
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = totalRecord % screenSize == 0 ?
							totalRecord/screenSize :
							totalRecord/screenSize + 1;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		startRow = (currentPage - 1)*screenSize + 1;
		endRow = currentPage * screenSize;
		endPage = (currentPage+(blockSize-1))/blockSize * blockSize;
		startPage = endPage - (blockSize - 1);
	}
	
	private static String aPattern = "<a href='#' data-page='%d'>[%s]</a>";
	private static String currentPagePtrn= "<a href='#'>[%s]</a>";
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		if(startPage > 1) {
			html.append(
				String.format(aPattern, (startPage-1), "이전")	
			);
		}
		endPage = endPage < totalPage ? endPage : totalPage;
		for(int page=startPage; page<=endPage; page++) {
			if(page==currentPage) {
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
				String.format(aPattern, (endPage + 1), "다음")	
			);
		}
		return html.toString();
	}
	private static String pageItem = "<li class='page-item %s' %s>"
			+"<a class='page-link' href='#' data-page='%d'>%s</a>"
			+ "</li>";

	public String getPagingHTMLBS() {
		StringBuffer html = new StringBuffer();
		html.append("<nav aria-label='...' class='mt-3'>");
		html.append("<ul class='pagination'>");
		String first = null;
		String second = null;
		int third = -1;
		String fourth = "이전";
		if(startPage > 1) {
			first = "";
			second = "";
			third = startPage - 1;
		}else {
			first ="disabled";
			second = "tabindex='-1' aria-disabled='true'";
			third = -1;
		}
		html.append(
			String.format(pageItem, first, second, third, fourth)	
		);
		endPage = endPage < totalPage ? endPage : totalPage;
		for(int page=startPage; page<=endPage; page++) {
			second = "";
			third = page;
			fourth = page + "";
			if(page==currentPage) {
				first = "active";
			}else {
				first = "";
			}
			html.append(
				String.format(pageItem, first, second, third, fourth)	
			);
		}
		fourth = "다음";
		if(endPage < totalPage) {
			first = "";
			second = "";
			third = endPage + 1;
		}else {
			first ="disabled";
			second = "tabindex='-1' aria-disabled='true'";
			third = -1;
		}
		html.append(
			String.format(pageItem, first, second, third, fourth)	
		);
		html.append("</ul>");
		html.append("</nav>");
		return html.toString();
	}
}









