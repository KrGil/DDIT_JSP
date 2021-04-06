package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *	회원 관리를 위한 Domain Layer(Java Bean 규약)
 *	Data Mapper(SQL Mapper)를 이용한 다중 테이블 조인 방법
 *	ex) 한명의 회원과 그동안 구매한 상품 목록을 함께 조회.
 *	1. 메인 데이터를 가진 메인 테이블을 식별(Member)
 *	2. 조인의 대상이 되는 테이블로부터 조회된 데이터를 바인딩할 VO 설계.
 *		(MemberVO, ProdVO)
 *	3. 테이블 사이의 관계를 VO 사이의 관계로 구조화.
 *		1:N	- has many (MemberVO has many ProdVO)
 *		1:1 - has a (ProdVO has a BuyerVO)
 *	4. resultType을 대신하여 resultMap 으로 수동 바인딩 설정
 *		1:N - collection 엘리먼트
 *		1:1 - association 엘리먼트
 *		(Member.xml -> memberMap)
 *
 */
// 데이터를 받는것 뿐만 아니라 vo를 들고 보내는것까지 해야하기 때문에
// 직렬화 시켜주어야한다.
// lombok을 받기
//@Getter
//@Setter
//@AllArgsConstructor
@EqualsAndHashCode(of= {"mem_id", "mem_regno1", "mem_regno2"})
@ToString(exclude= {"mem_pass", "mem_regno1", "mem_regno2"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO implements Serializable{
	//  기본 생성자 만들어 주기
	
	// 이녀석 만들면 기본생성자 필수
	public MemberVO(String mem_id, String mem_pass) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
	}
	
	private int rnum;
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_regno1;
	private String mem_regno2;
	private String mem_bir;
	private String mem_zip;
	private String mem_add1;
	private String mem_add2;
	private String mem_hometel;
	private String mem_comtel;
	private String mem_hp;
	private String mem_mail;
	private String mem_job;
	private String mem_like;
	private String mem_memorial;
	private String mem_memorialday;
	private Integer mem_mileage;
	private String mem_delete;
	
	private Set<ProdVO> prodList; // has many(1:N) 관계
	
	
}
