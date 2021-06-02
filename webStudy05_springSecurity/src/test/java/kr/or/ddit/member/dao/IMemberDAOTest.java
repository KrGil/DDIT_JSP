package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.TestWebAppConfiguration;

@RunWith(SpringRunner.class)
@TestWebAppConfiguration
public class IMemberDAOTest {
	@Inject
	private IMemberDAO dao;
	private Map<String, Object> pMap;
	
	@Before
	public void setUp() throws Exception {
		pMap = new HashMap<>();
	}

	@Test
	public void testRealDeleteMembers() {
		dao.realDeleteMembers(pMap);
		System.out.println(pMap);
	}

}









