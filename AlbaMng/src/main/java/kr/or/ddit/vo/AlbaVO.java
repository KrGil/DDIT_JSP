package kr.or.ddit.vo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of= {"alId"})
public class AlbaVO {
	@NotBlank(groups=UpdateGroup.class)
	private String alId;
	@NotBlank
	private String alName; 
	private Integer alAge; 
	@NotBlank
	private String alZip; 
	@NotBlank
	private String alAdd1; 
	@NotBlank
	private String alAdd2; 
	@NotBlank
	private String alHp; 
	
	@NotBlank
	private String grCode;
	private String grName;
	
	@NotBlank
	private String alGen;
	@NotBlank
	private String alMail; 
	private String alSpec; 
	private String alDesc;
	
	
	private String alImg;
	private MultipartFile alImage;
	public void setAlImage(MultipartFile alImage) {
		if(alImage!=null && !alImage.isEmpty()) {
			this.alImage = alImage;
			this.alImg = alImage.getUniqueSaveName();
		}
	}
	
	private String alCareer; 
	
	private List<LicenseVO> licenseList;
	private String[] licCodes;
	private String[] licDates;
	private List<MultipartFile> licImages;
	
	public void setLicImages(List<MultipartFile> licImages) throws IOException {
		this.licImages = licImages;
		if(licCodes==null && licDates==null && licImages==null) return;
		else {
			int codeSize = licCodes==null?0:licCodes.length;
			int dateSize = licDates==null?0:licDates.length;
			int imageSize = licImages==null?0:licImages.size();
			if((codeSize!=dateSize)||(codeSize!=imageSize)) {
				throw new IllegalArgumentException("자격증 사본이나 취득일 정보 누락");
			}
			licenseList = new ArrayList<>();
			for(int i=0; i<codeSize; i++) {
				licenseList.add(new LicenseVO(licCodes[i], licDates[i], licImages.get(i)));
			}
		}
	}
	
	private String[] deleteLicCodes;
}
