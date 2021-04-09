package kr.or.krgil.enumpkg;

public enum BrowserType{ // 생성자를 따로 만들어 주는 순간 enum(상수)들 역시 뒤에 생성자의 파라미터를 같이 넣어줘야한다.
	EDG("엣지"), CHROME("크롬"), TRIDENT("익스프로러"), OTHER("기타등등");
	BrowserType(String browserName){ 
		this.browserName = browserName;
	}
	
	// GETTER
	private String browserName;
	
	public String getBrowserName(){
		return this.browserName;
	}
	
	// 오버로딩
	public static String getBrowserName(String agent) { // intance가 없어도 사용 가능 static ㅇ
		agent = agent.toUpperCase();
		BrowserType searched = OTHER;
		for(BrowserType tmp : values()){
			if(agent.contains(tmp.name())){
				searched = tmp;
				break;
			}
		}
		String name = searched.getBrowserName();
		return name;
	}
}
