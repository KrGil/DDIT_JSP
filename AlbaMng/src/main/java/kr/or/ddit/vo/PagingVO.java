package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagingVO<T> {
	public PagingVO(int screenSize, int blockSize) {
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
	
	private SearchVO searchVO;
	private T searchDetail;
	
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (totalRecord + (screenSize - 1)) / screenSize;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		this.endRow = currentPage * screenSize;
		this.startRow = endRow - (screenSize - 1);
		this.startPage = blockSize * ( (currentPage-1) / blockSize ) + 1;
		this.endPage = startPage + (blockSize - 1);
	}
//	<nav aria-label='...'>
//	  <ul class='pagination'>
//	    <li class='page-item disabled'>
//	      <span class='page-link'>Previous</span>
//	    </li>
//	    <li class='page-item'><a class='page-link' href='#'>1</a></li>
//	    <li class='page-item active' aria-current='page'>
//	      <span class='page-link'>2</span>
//	    </li>
//	    <li class='page-item'><a class='page-link' href='#'>3</a></li>
//	    <li class='page-item'>
//	      <a class='page-link' href='#'>Next</a>
//	    </li>
//	  </ul>
//	</nav>
	
	private static final String LIPTRN = "<li class='page-item %s' %s>";
	private static final String APTRN = "<a class='page-link' href='#' data-page='%s'>%s</a>";
	private static final String SPANPTRN = "<span class='page-link'>%s</span>";
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		html.append("<nav aria-label='...'>");
		html.append("<ul class='pagination justify-content-center'>");
		// previous
		html.append(String.format(LIPTRN, startPage < blockSize?"disabled":"", ""));
		if(startPage < blockSize) {
			html.append(String.format(SPANPTRN, "Previous"));
		}else {
			html.append(String.format(APTRN, (startPage - blockSize), "Previous"));
		}
		html.append("</li>");
		
		// page number
		if(endPage>totalPage) endPage = totalPage;
		for(int page=startPage; page<=endPage; page++) {
			html.append(String.format(LIPTRN, page==currentPage?"active":"", 
											page==currentPage?"aria-current='page'":""));
			if(page==currentPage) {
				html.append(String.format(SPANPTRN, page));
			}else {
				html.append(String.format(APTRN, page, page));
			}
			html.append("</li>");
		}
		
		// Next
		html.append(String.format(LIPTRN, endPage >= totalPage?"disabled":"", ""));
		if(endPage >= totalPage) {
			html.append(String.format(SPANPTRN, "Next"));
		}else {
			html.append(String.format(APTRN, (endPage + 1) ,"Next"));
		}
		html.append("</li>");
		html.append("</ul>");
		html.append("</nav>");
		return html.toString();
	}
}


















