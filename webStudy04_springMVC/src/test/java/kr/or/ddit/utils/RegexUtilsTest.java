package kr.or.ddit.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexUtilsTest {

	@Test
	public void testFiltringTokens() {
		String origin = "ㅁㅁㅁㅁ말미잘...해삼....말마잘";
		String newStr = RegexUtils.filteringTokens(origin, "***", "말미잘","해삼");
		String newStr1 = RegexUtils.filteringTokens(origin, '*', "말미잘","해삼");
		System.out.println(newStr1);
	}
}
