package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="rep_no")
public class Reply2VO implements Serializable{
	private Integer bo_no;
	private Integer rep_no;
	private String rep_writer;
	private String rep_pass;
	private String rep_content;
	private String rep_date;
}
