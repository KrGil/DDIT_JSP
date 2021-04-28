package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.validator.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="prod_id")
@ToString(of= {"prod_id", "prod_name", "prod_lgu"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdVO implements Serializable {
	private int rnum;
	@NotBlank(groups=UpdateGroup.class)
	private String prod_id;
	@NotBlank
	private String prod_name;
	@NotBlank
	private String prod_lgu;
	private String lprod_nm;
	@NotBlank
	private String prod_buyer;
	@NotNull
	@Min(0)
	private Integer prod_cost;
	@Min(0)
	private Integer prod_price;
	@Min(0)
	private Integer prod_sale;
	private String prod_outline;
	private String prod_detail;
	//========상품이미지 처리====================================
	@NotBlank(groups=InsertGroup.class)
	private String prod_img;
	private MultipartFile prod_image;
	public void setProd_image(MultipartFile prod_image) {
		if(prod_image!=null && !prod_image.isEmpty()) {
			this.prod_image = prod_image;
			this.prod_img = UUID.randomUUID().toString();
		}
	}
	
	public void saveTo(File saveFolder) throws IOException {
		if(StringUtils.isBlank(prod_img)) return;
		prod_image.transferTo(new File(saveFolder, prod_img));
	}
	//===========================================================
	
	@Min(0)
	private Integer prod_totalstock;
	private String prod_insdate;
	@Min(0)
	private Integer prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
	@Min(0)
	private Integer prod_qtyin;
	@Min(0)
	private Integer prod_qtysale;
	@Min(0)
	private Integer prod_mileage;
	
	private BuyerVO buyer; // has a(1:1) 관계	
	
	private Set<MemberVO> userList; // has many
}







