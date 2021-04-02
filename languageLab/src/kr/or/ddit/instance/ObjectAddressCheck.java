package kr.or.ddit.instance;

import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;

public class ObjectAddressCheck {
	public static void main(String[] args) {
		String s1 = "test";
		String s2 = "test";
		
		VirtualMachine vm = VM.current();
		System.out.printf("s1's address : %s\n", vm.addressOf(s1));
		System.out.printf("s2's address : %s\n", vm.addressOf(s2));
		System.out.println(s1==s2);
		
		String s3 = new String("test");
		String s4 = new String("test");
		System.out.printf("s3's address : %s\n", vm.addressOf(s3));
		System.out.printf("s4's address : %s\n", vm.addressOf(s4));
		System.out.println(s3==s4);
		
		// constant(상수)메모리
		String str = "constant";
		// heap메모리
		System.out.printf("str's address : %s\n", vm.addressOf(str));
		str += "modify";
		System.out.printf("str's address : %s\n", vm.addressOf(str));
		// 새로 만들어 졌다.
		
		// 상수풀에 있는 데이터가 지워지지 않고 남아있다.
		String str2 = "constant";
		System.out.printf("str2's address : %s\n", vm.addressOf(str2));
		// 첫번째 str과 같다.
		
		// heap에 저장되어 잇기 때문에 초기 객체의 상태가 계속 변한다.
		StringBuffer sb = new StringBuffer("bufffer");
		System.out.printf("sb's address : %s\n", vm.addressOf(sb));
		sb.append("modify");
		System.out.printf("sb's address : %s\n", vm.addressOf(sb));
		
		// heap에 저장되면 상태가 변경될 수 있다.
		// 상수풀에 저장되면 상태가 변경될 수 없다.
		// 가비지컬렉터 - heap메모리
		
		
	}
}
