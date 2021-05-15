package valion.test.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test01_예외처리3 {
	public static void main(String[] args) throws IOException  {
		
		/** 파일읽기 IO : 객체생성 -> 작업 -> 종료 */ 
		
		String path = "src/valion/test/java/Test01_예외처리.java";
//		BufferedReader br = new BufferedReader(new FileReader(path));
//		while(true) {
//			String readLine = null;
//			readLine = br.readLine();
//			if(readLine==null) { break; }
//			System.out.println(readLine);
//		}
//		br.close();
		
		StringBuffer read = FileIOUtil.read(path); // 객체명.함수 , 클래스명.함수
		System.out.println(read.toString());
	}
	
	
	
	
}
