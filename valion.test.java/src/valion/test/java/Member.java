package valion.test.java;

public class Member {
	
	private String memNo; 
	private String memName;
	
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	@Override
	public String toString() {
		return "Member [memNo=" + memNo + ", memName=" + memName + "]";
	}
	
	
}
