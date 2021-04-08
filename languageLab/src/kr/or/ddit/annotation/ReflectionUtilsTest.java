package kr.or.ddit.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kr.or.ddit.utils.ReflectionUtils;

public class ReflectionUtilsTest {
	public static void main(String[] args) {
		String basePackage = "kr.or.ddit";
		// basePackage 경로 상의 모든 class파일들을 읽어들인다.
		List<Class<?>> clzList = ReflectionUtils.getAllClassesAtBasePackages(basePackage);
		for(Class<?> tmp : clzList) {
			System.out.println(tmp.getName());
		}
		
		Map<Class<?>, Annotation> withGroup =
				ReflectionUtils.getClassesWithAnnotationAtBasePackages(FirstAnnotation.class, basePackage);
		for(Entry<Class<?>, Annotation> entry : withGroup.entrySet()){
			String keyName = entry.getKey().getName();
			FirstAnnotation value =  (FirstAnnotation) entry.getValue();
			System.err.printf("%s : %s \n", keyName, value);
			Class<?> targetClz = entry.getKey();
			Class<? extends Annotation> annotationType = SecondAnnotation.class;
			Map<Method, Annotation> methos = 
					ReflectionUtils.getMethodsWithAnnotationAtClass(targetClz, annotationType, void.class);
			System.out.println(methos);
		}
		
	}
}
