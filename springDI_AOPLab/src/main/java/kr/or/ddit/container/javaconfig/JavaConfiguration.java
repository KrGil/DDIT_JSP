package kr.or.ddit.container.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.container.various.VariousDIVO;

@Configuration
@Lazy
@ComponentScan(basePackages = "kr.or.ddit")
public class JavaConfiguration {
	@Bean("vo1")
	@Scope("prototype")
	public VariousDIVO getVariousDIVO() {
		return new VariousDIVO();
	}
}


