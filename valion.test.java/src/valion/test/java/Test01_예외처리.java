package valion.test.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test01_예외처리 {
	public static void main(String[] args)  {
		
		/** 파일읽기 IO : 객체생성 -> 작업 -> 종료 */ 
		
		String path = "src/valion/test/java/Test01_예외처리.java";
		BufferedReader br = null;
		
		/** try-catch [1] */ 
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			String readLine = null;
			try {
				readLine = br.readLine();
				if(readLine==null) { break; }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(readLine);
		}
		
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
