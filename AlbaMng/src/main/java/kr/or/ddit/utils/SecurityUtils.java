package kr.or.ddit.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecurityUtils {
	public static String encryptSha512(String plain){
//		1. 단방향 (해시 함수) : 다양한 입력데이터를 일정 길이의 해시 코드로 생성할 때 사용
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] encrypted = md.digest(plain.getBytes());
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			return encoded;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
}
