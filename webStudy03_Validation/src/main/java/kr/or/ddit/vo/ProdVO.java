package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.sun.istack.internal.NotNull;

import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.validator.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 회원 
 * 
 */
@Data
@EqualsAndHashCode(of="prod_id")
@ToString(of= {"prod_id", "prod_name", "prod_lgu"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdVO implements Serializable {
	private int rnum;
	// 쓰여야 하는 곳을 작성
	@NotBlank(groups=UpdateGroup.class)
	private String prod_id;
	@NotBlank
	private String prod_name;
	@NotBlank
	private String prod_lgu;
	private String lprod_nm;
	@NotBlank
	private String prod_buyer;
	// int 체크하기
	@NotNull
	@Min(0)
	private Integer prod_cost;
	@Min(0)
	private Integer prod_price;
	@Min(0)
	private Integer prod_sale;
	private String prod_outline;
	private String prod_detail;
	@NotBlank(groups=InsertGroup.class)
	private String prod_img;
	private Integer prod_totalstock;
	private String prod_insdate;
	private Integer prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
	private Integer prod_qtyin;
	private Integer prod_qtysale;
	private Integer prod_mileage;
	
	private BuyerVO buyer; // has a(1:1) 관계
	
	private Set<MemberVO> userList; // has many
	
	
}
