package kr.or.ddit.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
	
	/**
	 * 문자열을 MD5 방식으로 암호화(해시)한다.
	 * @param str 암호화할 문자열
	 * @return 암호화된 문자열 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String md5(String str) throws NoSuchAlgorithmException {
		// sha1 이전에 나온방식.
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		
		// Base64 > 이상한 글자 들어갈 수도 있음 > Base64라고 하는 방식으로 인코딩, 디코딩해주는 객체?
		return Base64.getEncoder().encodeToString(md.digest());	
	}
	
	// 문자열을 SHA-256방식으로 암호화하는 메서드
	public static String sha256(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(str.getBytes());
		
		return Base64.getEncoder().encodeToString(md.digest());	
	}
	
	// 문자열을 SHA-512방식으로 암호화하는 메서드
	public static String sha512(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(str.getBytes());
		// 64바이트를 base64를 이용해서 encoding. -> 1.3배
		return Base64.getEncoder().encodeToString(md.digest());	
	}
	
	/**
	 * AES 256 방식으로 암호화하는 메서드
	 * @param str 암호화할 문자열
	 * @param key 암호키값 문자열 (16자 이상)
	 * @return 암호화된 문자열
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String encryptAES256(String str, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String iv = key.substring(0, 16);
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = keyBytes.length;	// 16으로 정해져 있어서 16으로 써도 되지만 나중에 확장성을 위해서
		
		System.arraycopy(b, 0, keyBytes, 0, len);
		// (배열1, int1, 배열2, int2, len);
		// 배열1이 가지고 있는 값을 int1부터 배열2의 int2번째 인덱스에 len길이만큼 집어넣어라
		
		// 비밀키 생성 (암호화할 byte배열, 알고리즘 이름)
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		
		// Ciper객체 생성 및 초기화
		// 알고리즘 / 모드 / 패딩
		// CBC ==> Cipher Block Chaining Mode
		//		  동일한  평문 블록과 암호문의 블록의 쌍이 발생하지 않도록
		// 		  이전 단계의 암복호화한 결과가 현 단계에 영향을 주는 운영 모드
		
		// Padding ==> 마지막 블록이 블록의 길이와 항상 딱 맞아 떨어지지 않기 때문에 부족한만큼을
		//				'0'으로 채우거나 임의의 비트로 채워 넣는 것을 말한다. - PKCS5Padding도 한 개의 패딩 ㅇㅂ션
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		byte[] ivBytes = new byte[16];
		System.arraycopy(iv.getBytes(), 0, ivBytes, 0, ivBytes.length);
		
		// 옵션 종류에 맞게 초기화한다.
		// 옵션 종류 : ENCRYPT_MODE(암호화모드), DECRYPT_MODE(복호화모드)... 
		
		// 초기화 벡터(Initial Vector, IV)
		//  ==> 암호문이 패턴화되지 않도록 사용하는 데이터
		//		첫 블록을 암호화할 때 사용하는 값
		
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
		
		// 암호문 생성 - doFinal
		byte[] encryped = c.doFinal(str.getBytes("UTF-8"));
		
		String enStr = Base64.getEncoder().encodeToString(encryped);
		
		return enStr;
	}
	
	/**
	 * encryptAES256()메서드로 암호화한 내용을 복호화하는 메서드
	 * @param str 복호화할 문자열(암호화된 문자열)
	 * @param key 암호키 문자열
	 * @return 복호화된 문자열(원래의 문자열)
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String decryptAES256(String str, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String iv = key.substring(0, 16);
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = keyBytes.length;	
		
		System.arraycopy(b, 0, keyBytes, 0, len);
		
		// 비밀키 생성 (암호화할 byte배열, 알고리즘 이름)
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		
		// Ciper객체 생성 및 초기화
		// 알고리즘 / 모드 / 패딩
		// CBC ==> Cipher Block Chaining Mode
		//		  동일한  평문 블록과 암호문의 블록의 쌍이 발생하지 않도록
		// 		  이전 단계의 암복호화한 결과가 현 단계에 영향을 주는 운영 모드
		
		// Padding ==> 마지막 블록이 블록의 길이와 항상 딱 맞아 떨어지지 않기 때문에 부족한만큼을
		//				'0'으로 채우거나 임의의 비트로 채워 넣는 것을 말한다. - PKCS5Padding도 한 개의 패딩 ㅇㅂ션
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		byte[] ivBytes = new byte[16];
		System.arraycopy(iv.getBytes(), 0, ivBytes, 0, ivBytes.length);
		
		// 옵션 종류에 맞게 초기화한다.
		// 옵션 종류 : ENCRYPT_MODE(암호화모드), DECRYPT_MODE(복호화모드)... 
		
		// 초기화 벡터(Initial Vector, IV)
		//  ==> 암호문이 패턴화되지 않도록 사용하는 데이터
		//		첫 블록을 암호화할 때 사용하는 값
		
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
		
		// 복호화할 문자열을 decoding해서 암호화될 byte배열을 구한다.
		byte[] byteStr = Base64.getDecoder().decode(str);
		
		// 복호화된 데이터를 문자열로 변환하여 반환한다.
		return new String(c.doFinal(byteStr), "UTF-8");
		
	}
	
}	

