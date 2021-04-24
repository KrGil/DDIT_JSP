package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validator.BoardInsertGroup;
import kr.or.ddit.validator.DeleteGroup;
import kr.or.ddit.validator.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 공지글/게시글을 공통으로 관리할 domain layer
 *
 */
@Data
@EqualsAndHashCode(of="bo_no")
@ToString(exclude= {"attatchList", "replyList"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVO implements Serializable{
	
	private Integer bo_sort;
	@NotBlank
	private String bo_type;
	
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	@Min(value=1, groups= {UpdateGroup.class, DeleteGroup.class})
	private Integer bo_no;
	@NotBlank
	private String bo_title;
	@NotBlank(groups= {BoardInsertGroup.class, UpdateGroup.class})
	private String bo_writer;
	@NotBlank(groups= {
						BoardInsertGroup.class
					   , UpdateGroup.class
					   , DeleteGroup.class
					  })
	private String bo_pass;
	private String bo_content;
	private String bo_date;
	private Integer bo_hit;
	private Integer bo_rec;
	private Integer bo_rep;
	private String bo_sec;
	private Integer bo_parent;
	
	private int startAttNo;
	private List<AttatchVO> attatchList;
	private List<Reply2VO> replyList;
	
	private int[] delAttNos;
	private String thumbnail;
}












