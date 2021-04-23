package kr.or.ddit.vo;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of= {"alId", "licCode"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LicenseVO {
	public LicenseVO(String licCode, String licDate, MultipartFile licImage) throws IOException {
		super();
		this.licCode = licCode;
		this.licDate = licDate;
		this.licImage = licImage;
		this.licImg = IOUtils.toByteArray(licImage.getInputStream());
	}
	private String alId;
	private String licCode;
	private String licName;
	private String licDate;
	private byte[] licImg;
	private MultipartFile licImage;
}
