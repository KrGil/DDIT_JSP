package kr.or.ddit.enumpkg;

public enum OSType {
	MAC("충성충성!"), ANDROID("쯧"), WINDOWS("음그냥컴터"), OTHER("정체불명");
	OSType(String OSName){
		this.OSName = OSName;
	}
	
	private String OSName;
	public String getOSName() {
		return this.OSName;
	}
	//오버로딩
	public static String getOSName(String agent) {
		agent = agent.toUpperCase();
		OSType searched = OTHER;
		for(OSType tmp: OSType.values()) {
			if(agent.contains(tmp.name())) {
				searched = tmp;
				System.out.println(searched);
				break;
			}
		}
		String name = searched.getOSName();
		return name;
	}
	
	
}
