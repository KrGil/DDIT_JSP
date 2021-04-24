package kr.or.ddit.mvc.annotation.resolvers;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Target;

@Target(PARAMETER)
@Retention(RUNTIME)
public @interface ModelAttribute {
	String value();
}
