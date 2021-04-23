package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 
 * 자바빈 규약(VO, DTO, Model, Bean) - 다 동일한 의미이다.
 * 1. 값을 가질 수 있는 속성(property) 존재
 * 2. property 캡슐화
 * 3. property 에 접근할 수 있는 interface제공
 * 		Getter/Setter : get[set]property_name(carmel 표기법)
 * 4. 객체 상태 비교 메소드 제공
 * 	==(주소), equals(상태비교) 의 차이 
 * 5. 객체 상태 확인 메소드 제공 : toString
 * 6. 직렬화 지원 - Serializable(단순히 직렬화 식별 가능한지 판별)
 * 
 */
public class ParameterVO implements Serializable {
	private String[] param1;
	private String[] param2;
	private String[] param3;
	private int[] param4;
	private String param5;
	public String[] getParam1() {
		return param1;
	}
	public void setParam1(String[] param1) {
		this.param1 = param1;
	}
	public String[] getParam2() {
		return param2;
	}
	public void setParam2(String[] param2) {
		this.param2 = param2;
	}
	public String[] getParam3() {
		return param3;
	}
	public void setParam3(String[] param3) {
		this.param3 = param3;
	}
	public int[] getParam4() {
		return param4;
	}
	public void setParam4(int[] param4) {
		this.param4 = param4;
	}
	public String getParam5() {
		return param5;
	}
	public void setParam5(String param5) {
		this.param5 = param5;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(param1);
		result = prime * result + Arrays.hashCode(param2);
		result = prime * result + Arrays.hashCode(param3);
		result = prime * result + Arrays.hashCode(param4);
		result = prime * result + ((param5 == null) ? 0 : param5.hashCode());
		return result;
	}
	
	// 상태를 비교할 수 있게 되었다.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParameterVO other = (ParameterVO) obj;
		if (!Arrays.equals(param1, other.param1))
			return false;
		if (!Arrays.equals(param2, other.param2))
			return false;
		if (!Arrays.equals(param3, other.param3))
			return false;
		if (!Arrays.equals(param4, other.param4))
			return false;
		if (param5 == null) {
			if (other.param5 != null)
				return false;
		} else if (!param5.equals(other.param5))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ParameterVO [param1=" + Arrays.toString(param1) + ", param2=" + Arrays.toString(param2) + ", param3="
				+ Arrays.toString(param3) + ", param4=" + Arrays.toString(param4) + ", param5=" + param5 + "]";
	}
	
	
	
}
