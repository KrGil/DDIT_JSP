package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeVO {
	private String employee_id;
	private String employee_pwd;
	private String employee_name;
	private String employee_phone;
	private String employee_email;
	private String employee_authority;
	private String employee_picture;
}
