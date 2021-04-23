package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="zipcode")
public class ZipVO {
	private String zipcode;
	private String sido;
	private String gugun;
	private String dong;
	private String ri;
	private String bldg;
	private String bunji;
	private Integer seq;
	
	public String getAddress() {
		StringBuffer address = new StringBuffer();
		address.append(sido+" ");
		address.append(gugun+" ");
		if(dong!=null)
			address.append(dong+" ");
		if(ri!=null)
			address.append(ri+" ");
		if(bldg!=null)
			address.append(bldg+" ");
		return address.toString();
	}
}
