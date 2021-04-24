package kr.or.ddit.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	public static String filteringTokens(String origin, String replace, String... tokens){
//		"말미잘", "해삼", "멍게"
		String regex = String.format("(%s)", String.join("|", tokens));
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(origin);
		StringBuffer result = new StringBuffer();
		while(matcher.find()) {
			matcher.appendReplacement(result, replace);
		}
		matcher.appendTail(result);
		return result.toString();
	}

	public static String filteringTokens(String origin, char maskingCh, String... tokens){
//		"말미잘", "해삼", "멍게"
		String regex = String.format("(%s)", String.join("|", tokens));
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(origin);
		StringBuffer result = new StringBuffer();
		while(matcher.find()) {
			// 그룹안에 있는 글자 가지고오기.
//			matcher.group(1).replaceAll(".", maskingCh+"");
			String replace = 
					matcher.group(1).replaceAll(".", new Character(maskingCh).toString());
			matcher.appendReplacement(result, replace);
		}
		matcher.appendTail(result);
		return result.toString();
	}
}
