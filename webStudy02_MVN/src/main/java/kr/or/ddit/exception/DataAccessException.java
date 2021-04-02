package kr.or.ddit.exception;
/**
 * Persistence Layer에서만 사용할 예외로 정의.
 *
 */
public class DataAccessException extends RuntimeException{
	// alt + shift + s 밑에서 두번째 
	public DataAccessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
