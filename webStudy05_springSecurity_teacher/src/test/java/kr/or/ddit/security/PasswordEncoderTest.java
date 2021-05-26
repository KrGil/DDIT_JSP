package kr.or.ddit.security;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.TestWebAppConfiguration;

@RunWith(SpringRunner.class)
@TestWebAppConfiguration
public class PasswordEncoderTest {
	@Inject
	private PasswordEncoder encoder;
	
	@Test
	public void testEncoder() {
		String plain = "java";
		
		String encoded = encoder.encodePassword(plain, null);
		System.out.println(encoded);
	}
}
