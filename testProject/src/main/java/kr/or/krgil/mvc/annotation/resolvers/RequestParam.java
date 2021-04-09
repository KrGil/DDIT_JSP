package kr.or.krgil.mvc.annotation.resolvers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
	String value(); // 어떤 이름의 파라미터를 꺼내줄지 결정해주는 역할
	boolean required() default true; // 
	String defaultValue() default ""; // false 시 넣어줄 기본값.
	// 요청 파라미터가 비어있을 경우를 상정해서 만들어준 녀석
}
