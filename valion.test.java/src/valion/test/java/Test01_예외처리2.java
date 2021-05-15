package valion.test.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test01_예외처리2 {
	public static void main(String[] args)  {
		
		/** 파일읽기 IO : 객체생성 -> 작업 -> 종료 */ 
		
		String path = "src/valion/test/java/Test01_예외처리.java";
		BufferedReader br = null;
		
		/** try-catch [1] */ 
		try {
			br = new BufferedReader(new FileReader(path));
			while(true) {
				String readLine = null;
				readLine = br.readLine();
				if(readLine==null) { break; }
				System.out.println(readLine);
			}
			
			if(1==1) {
				return ;
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		{
			System.out.println("종료");
			try {
				if(br!=null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
