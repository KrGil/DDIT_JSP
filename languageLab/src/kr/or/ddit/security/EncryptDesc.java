package kr.or.ddit.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 *	encoding(부호화/decoding) : 저장이나 전송을 위해 
 *			매체가 인지할 수 있는 방식으로 데이터의 표현방식을 바꾸는 작업.
 *			UrlEncoding(Percent encoing), Base64(파일 업로드)
 *	encrypting(암호화) : 허가되지 않은 (key) 사용자의 데이터 접근을 막기위해
 *					데이터를 변환하는 작업.
 *	단방향 암호화(해시 함수) : 복호화 불가능한 암호화(비밀번호)
 *			: 입력 데이터의 길이가 달라도, 출력 데이터의 길이는 동일한 구조.
 *			MessageD
 *			SHA-512(비트수 64바이트)
 *	양방향 암호화 : 키를 소유한 경우 복호화가 가능한 암호화(전송데이터)
 *			
 *			Cipher
 *			대칭키 암호화 : 하나의 동일키(비밀키)를 통해 암복호화 수행.
 *					AES-128(키의길이), AES-256
 *			비대칭키 암호화 : 한쌍의 키(개인키/공개키)를 통해 암복호화 수행
 *			
 */
public class EncryptDesc {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		String plain = "힘들다...";
		
		Cipher cipher = Cipher.getInstance("RSA");
		
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(2048);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.encodeBase64String(encrypted);
		System.out.println(encoded);
		
		//decode
		byte[] decoded = Base64.decodeBase64(encoded);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		
		byte[] decrypted = cipher.doFinal(decoded);
		System.out.println(new String(decrypted));
	}
	
	public static void aesEncryptetest(String plain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		// 양방향 대칭키 
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] iv = new byte[128/8];
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.nextBytes(iv);
		System.out.println(Arrays.toString(iv));
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		
		// 비밀키 생성
		SecretKey key = keyGen.generateKey();
		
		// encrypted
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.encodeBase64String(encrypted);
		System.out.println(encoded);
		
		// decrypted
		byte[] decoded = Base64.decodeBase64(encoded);
		cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
		byte[] decrypted = cipher.doFinal(decoded);
		System.out.println(new String(decrypted));
	}
			
	public static String encryptSha512(String plain) throws NoSuchAlgorithmException {
//		단방향 암호화
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] input = plain.getBytes();
		byte[] encrypted = md.digest();
		System.out.println(encrypted.length*8);
		String encoded = Base64.encodeBase64String(encrypted);
		System.out.println(encoded);
//				String savedPass = "z4PhNX7vuL3xVChQ1m2AB9Yg5AULVxXcg/SpIdNs6c5H0NE8XYXysP+DGNKHfuwvY7kxvUdBeoGlODJ6+SfaPg==";
//				System.out.println(savedPass.equals(encoded)?"인증성공":"인증실패");
		return encoded;
	}
	
	public static void encodeTest(String plain) throws IOException{
//		1. Url encoding
		String encoded = URLEncoder.encode(plain, "utf-8");
		System.out.println(encoded);
		
		String decoded = URLDecoder.decode(encoded,"utf-8");
		System.out.println(decoded);
		
//		2. Base 64
		byte[] binary = plain.getBytes();
		encoded = Base64.encodeBase64String(binary);
		System.out.println(encoded);
		
		Base64.decodeBase64(encoded);
		byte[] decodeBinary = Base64.decodeBase64(encoded);
		System.out.println(new String(decodeBinary));
	}
}






