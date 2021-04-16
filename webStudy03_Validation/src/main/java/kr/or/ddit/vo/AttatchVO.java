package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="att_no")
@NoArgsConstructor
@ToString(exclude="file")
public class AttatchVO implements Serializable{
	
	private transient MultipartFile file;
	
	public AttatchVO(MultipartFile file) {
		super();
		this.file = file;
		this.att_filename = file.getOriginalFilename();
		this.att_savename = file.getUniqueSaveName();
		this.att_contenttype = file.getContentType();
		this.att_size = file.getFileSize();
	}
	
	private Integer bo_no;
	private Integer att_no;
	private String att_filename;
	private String att_savename;
	private Long att_size;
	private String att_contenttype;
	private Integer att_downcount;
	
	public void saveTo(File saveFolder) throws IOException {
		file.transferTo(new File(saveFolder, att_savename));
	}

}




