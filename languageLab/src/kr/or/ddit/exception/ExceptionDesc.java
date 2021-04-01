package kr.or.ddit.exception;

import java.io.IOException;
import java.sql.SQLException;


/**
 * 예외(Throwable) : 실행과정에서 발생하는 모든 비정상 상황
 *	- Error : 개발자가 처리하지 않고, VM이 제어권을 넘겨받는 예외의 한 종류(fatal error).
 *	- Exception : 개발자가 처리할 수 있는 예외
 *			- checked exception  : Exception 의 직계
 *				: 예외 발생 가능한 코드가 있으면, 반드시 처리를 해야하는 예외
 *					SQLException, IOException, SocketException
 *			- unchecked exception : RuntimeException 직계
 *				: 명시적으로 처리하지 않더라도 호출자 혹은 VM에게 제어권이 전달되는 예외
 *				NullpointerException, IllegalArgumentException
 *
 *
 *
 * ** 예외 처리 방법
 * 1. 직접 처리
 * 	try(closable 객체 생성){
 * 		예의 발생 가능 코드
 * 	} ~ catch(captuable exception){
 * 		예외 처리 코드
 *  }~ finally{
 *  	예외 발생 여부와 무관하게 처리할 코드
 *  }
 *  2. 호출자에게 위임하는 처리 : throws
 *  
 *  ** 예외 발생 방법
 *  	throw new 예외 객체 생성
 *  	(throw가 없다면 그냥 일반 객체.)
 * 
 *  
 *  ** custom 예외 정의 방법
 *  	: 상위(예외의 특성에 따라 Exception/RuntimeException)의 예외 클래스를 상속
 *  	처리하고 싶다면 exception 처리하지 못한다고 판단하면 runtime
 *  un or checked부터 정하자.
 *  
 */
public class ExceptionDesc {
	public static void main(String[] args){
//		try {
//			String retValue = method1();
//			System.out.println(retValue);
//		} catch (IOException e) {
//			e.printStackTrace();
////			throw e; catch 밖에 녀석이 실행되지 않는다. 그리고 throws를 해줘얗마.
//		}
		
		try {
			System.out.println(method2()); 
		}catch(RuntimeException e) {
			System.err.println(e.getMessage());
			throw e;
		}
	}
	
	private static String method2(){
		try {
			if(1==1)// uncheced
//			throw new IllegalArgumentException("강제발생예외");
				throw new SQLException("강제발생예외");
			return "method2RetValue";
		}catch(SQLException e) {
			// customexception으로 해서 db쪽에서만 예외가 발생햇다는 것을
			// 쉽게 알아챌 수 있다.
			throw new DataAccessException(e);
		}
	}
	
	private static String method1() throws IOException {
		try {
			if(1==1) {
	//			new IOException("강제 발생 예외");
				throw new IOException("강제 발생 예외");
			}
			return "method1RetValue";
		}catch(IOException e) {
			System.err.println("예외 발생했음."+e.getMessage());
			throw e;
		}
		
	}
}







