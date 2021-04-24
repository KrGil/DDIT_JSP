package kr.or.ddit.container.various;


import org.springframework.core.io.Resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class VariousDIVO {
	private String str;
	private int number;
	private double dblNumber;
	private char ch;
	private boolean boolData;
	
	private Resource cpr;
	private Resource fsr;
	private Resource urlr;
}
