package kr.or.ddit.container.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBInfoVO {
	private String driverClassName;
	private String url;
	private String user;
	private String password;
	private long maxWait;
}
