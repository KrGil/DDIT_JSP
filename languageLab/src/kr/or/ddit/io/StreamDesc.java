package kr.or.ddit.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *	스트림 : 연속성을 가진 일련의 데이터의 흐름이면서 데이터 전송 (단방향)통로 
 *	
 *	스트림의 종류
 *	1. 전송 데이터 크기
 *		1) byte stream(1 byte) : XXXInputStream / XXXOutputStream
 *			FileInputStrea / FileOutputStream
 *			SocketInputStream / SocketOutputStream 
 *			ByteInputStream / ByteOutputStream			
 *
 *		2) character stream(1 char) : XXXReader / XXXWriter
 *			FileReader / FileWriter
 *			StringReader / StringWriter //inmemory방식.
 *
 *	2. stream의 생성 방법
 *		1) 1차 stream(단일형 스트림) : 매체를 대상으로 직접 생성(개방)하는 스트림
 *		2) 2차 stream(연결형 스트림) : 1차 스트림을 대상으로 연결하는 보조형 스트림
 *			- buffered stream : BufferedReader
 *			- filtered stream : DataInputStream - 데이터 타입
 *			- object stream : ObjectInputStream
 *			  (only serializable 객체를 대상으로 직렬화/역직렬화)
 *		직렬화(Serialization) : 객체를 전송하거나 기록하기 위한 형태의 바이트 배열의 형태로 변환하는 작업
 *		역직렬화(Deserialization) : 기록되어있거나 전송된 바이트 배열로부터 원래 객체의 상태를 복원하느 작업
 *
 *	** 매체로부터 데이터를 읽어들이는 단계
 *	1. 매체를 어플리케이션내에서 핸들링할수있는 객체로 생성
 *		new File(file system path), new ServerSocket(port)
 *	2. 읽어들이기 위한 스트림 생성
 *		new FileInputStream(file), socket.getInputStream()
 *		new InputStreamReader()
 *		new bufferedReader()
 *	3. stream end까지 읽기 반복(EOF, -1, nul)
 *	4. 자원 release : close
 *
 */
public class StreamDesc {
	public static void main(String[] args)throws IOException{
		String path = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
		File folder = new File("d:/contents");
		File writeFile = new File(folder, "googlelogo_color_272x92dp.png");
		URL url = new URL(path);
		System.out.println(url);
		URLConnection conn = url.openConnection();
		try(
			InputStream is = conn.getInputStream();
			FileOutputStream fos = new FileOutputStream(writeFile);
		){
			byte[] buffer = new byte[1024];
			int cnt = -1;
			while((cnt = is.read(buffer)) != -1 ) {
				fos.write(buffer, 0, cnt); // 맨 마지막이 중요. buffer의 처음(0)부터 얼마까지(cnt) 가지고있나
			}
		}
	}
	private static void readClassPathResource() {

//		another day.txt
		String qualifiedName = "another day.txt";
//		ClassLoader loader = Thread.currentThread().getContextClassLoader();
//		URL url = loader.getResource(qualifiedName); // 스키마에 따라 다른결과값이 나올 수 있다.(http:/ file:/)
		URL url = StreamDesc.class.getResource(qualifiedName); //클래스로더나 자기 자신의 위치에서 찾기
		System.out.println(url);
		String temp  = null;
		try( 
			InputStream in = StreamDesc.class.getResourceAsStream(qualifiedName);
	
			InputStreamReader reader = new InputStreamReader(in, "utf-8");
			BufferedReader br = new BufferedReader(reader);
		){	
			while((temp = br.readLine()) != null) {
				System.out.println(temp);
			}
		}catch(IOException e){ 
			System.err.println(e.getMessage());
		}
	}
	private static void readFileSystemResource() {
		File folder = new File("d:/contents");
		File readFile = new File(folder, "오래된 노래_utf8.txt");
		
		String temp = null;
//		try( Closable 객체 생성 ){	}catch{ } java 1.5 이후나온 withable? 방법 1.7이후에 쓰는 방법
		try( 
	//		FileReader reader = new FileReader(readFile); // character 설정을 이녀석이 해야함
			FileInputStream fis = new FileInputStream(readFile); // character 설정을 이녀석이 해야함
	
			InputStreamReader reader = new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(reader);
		){	
			while((temp = br.readLine()) != null) {
				System.out.println(temp);
			}
		}catch(IOException e){ 
			System.err.println(e.getMessage());
		}
	}
}








