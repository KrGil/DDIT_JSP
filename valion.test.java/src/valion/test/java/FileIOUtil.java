package valion.test.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIOUtil {
	
	/** 파일경로 -> 파일의 내용을 전달하기 위한 모듈 */
	public static StringBuffer read(String path) throws IOException {
		
		/** 결과객체 */
		StringBuffer sb = new StringBuffer();
		
		/** 파일읽기 IO : 객체생성 -> 작업 -> 종료 */ 
		BufferedReader br = new BufferedReader(new FileReader(path));
		boolean isFirst = true; 
		while(true) {
			String readLine = null;
			readLine = br.readLine();
			if(readLine==null) { break; }
			if(isFirst) {
				sb.append(readLine);
				isFirst = false; 
			}else {
				sb.append("\r\n" + readLine);
			}
		}
		br.close();
		return sb;
	}
	
}
