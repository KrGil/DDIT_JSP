package kr.or.krgil.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Factory Object[Method] Pattern
 * 	: 객체 생성을 전담하는 객체를 운영하는 구조.
 * 
 */
public class ConnectionFactory {
	private static String driverClassName;
	private static String url;
	private static String user;
	private static String password;
	private static DataSource ds;
	private static String connectionMessage;
	
	static {
//		Properties properties = new Properties();
//		try(
//			InputStream is =ConnectionFactory.class.getResourceAsStream("dbInfo.properties");
//		) {
//			properties.load(is);
		// classpath 이후의 경로를 읽는것을 baseName이라고 한다.
//			driverClassName = properties.getProperty("driverClassName");
//			url = properties.getProperty("url");
//			user = properties.getProperty("user");
//			password = properties.getProperty("password");
//			int initialSize = 
//					Integer.parseInt(properties.getProperty("initialSize"));
//			int maxTotal = 
//					Integer.parseInt(properties.getProperty("maxTotal"));
//			long maxWait = 
//					Long.parseLong(properties.getProperty("maxWait"));
			// dbinfo_en, dbinfo 뒤에 en등이 안붙으면 그게 기본 언어로 설정.
			ResourceBundle bundle = ResourceBundle.getBundle(
					"kr.or.ddit.db.dbInfo", Locale.ENGLISH);
			driverClassName =  bundle.getString("driverClassName");
			url = bundle.getString("url");
			user = bundle.getString("user");
			password = bundle.getString("password");
			connectionMessage = bundle.getString("connectionMessage");
			
			int initialSize = 
					Integer.parseInt(bundle.getString("initialSize"));
			int maxTotal = 
					Integer.parseInt(bundle.getString("maxTotal"));
			long maxWait = 
					Long.parseLong(bundle.getString("maxWait"));
			
			// DBCP 쓰기.
			ds = new BasicDataSource();
			((BasicDataSource)ds).setDriverClassName(driverClassName);
			((BasicDataSource)ds).setUrl(url);
			((BasicDataSource)ds).setUsername(user);
			((BasicDataSource)ds).setPassword(password);
			// 최대 connection수 설정
			((BasicDataSource)ds).setMaxTotal(maxTotal); 
			// 초기 connection 생성 수
			((BasicDataSource)ds).setInitialSize(initialSize);
			// 최대 기다리는 millis
			((BasicDataSource)ds).setMaxWaitMillis(maxWait);
			
//			ds = new OracleDataSource();
//			((OracleDataSource)ds).setURL(url);
//			((OracleDataSource)ds).setUser(user);
//			((OracleDataSource)ds).setPassword(password);
			
//			Class.forName(driverClassName);
//		} catch (IOException e) {
//			throw new RuntimeException(e); // uncheckedException으로 변경됨.
//		}
	}
	
	public static Connection getConnection() throws SQLException{
//		return DriverManager.getConnection(url, user, password);
		System.out.println(connectionMessage);
		return ds.getConnection();
	}
}
