package kr.or.ddit.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexUtilsTest {

	@Test
	public void testFilteringTokens() {
		String origin = "힘들어.. 언제 끝나 말미잘!해삼!!";
		String newStr = RegexUtils.filteringTokens(origin, 'ㅁ', "말미잘","해삼");
		System.out.println(newStr);
	}

}
