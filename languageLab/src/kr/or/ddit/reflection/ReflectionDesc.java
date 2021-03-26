package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import kr.or.ddit.reflect.ReflectionTest;

public class ReflectionDesc {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Object obj = ReflectionTest.getObject();
		System.out.println(obj);
		
		Map<Object, Object> map = new HashMap();
		dePopulate(obj, map);
		System.out.println(map);
	}
	
	public static void dePopulate(Object bean, Map<Object,Object> map) {
		Class clz = bean.getClass();
		Field[] fields = clz.getDeclaredFields();
		for(Field tmp : fields) {
			String varName = tmp.getName();
			try {
				PropertyDescriptor pd = new PropertyDescriptor(varName, clz);
				Class varType =  pd.getPropertyType();
				Method getter =  pd.getReadMethod();
				Object value = getter.invoke(bean);
				
				Method setter = pd.getWriteMethod();
				
				setter.invoke(bean, "");
				
				System.out.printf("%s %s = %s;\n",
								varType.getSimpleName(), varName, value);
				map.put(varName, value); // depopulation
				
				// java규약에 맞지 않는 것을 사용했을 때 발생하는 오류
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private static void reflect2(Object obj){
	
		Class clz = obj.getClass();
		Field[] fields = clz.getDeclaredFields();
		for(Field tmp : fields) {
			String varName = tmp.getName();
			try {
				PropertyDescriptor pd = new PropertyDescriptor(varName, clz);
				Class varType =  pd.getPropertyType();
				Method getter =  pd.getReadMethod();
				Object value = getter.invoke(obj);
				System.out.printf("%s %s = %s;\n",
								varType.getSimpleName(), varName, value);
				// java규약에 맞지 않는 것을 사용했을 때 발생하는 오류
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void reflect1(Object obj) {
		// 모든 클래스들의 최상위가 Class라는 녀석이다.
				Class clz = obj.getClass();
				System.out.println(clz);
//				Field[] fields =  clz.getFields(); // public 녀석들만 가져올 수 있음.
				Field[] fields =  clz.getDeclaredFields(); // 다 가져올 수 있음.
				for(Field tmp: fields) {
					String varName = tmp.getName();
					Class varType = tmp.getType();
					try {
//						tmp.setAccessible(true); // private 전역변수를 public으로 바꿈
//						Object value = tmp.get(obj);
						String readMethodName = "get"
									+varName.substring(0,1).toUpperCase()
									+varName.substring(1);
						
						Method readMethod = clz.getDeclaredMethod(readMethodName);
//						member.getMem_name(); 
						Object value = readMethod.invoke(obj);
						System.out.printf("%s %s=%s;\n",varType.getSimpleName(), varName, value);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	}
}
