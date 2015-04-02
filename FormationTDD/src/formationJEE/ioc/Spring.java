package formationJEE.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring {
	private static ApplicationContext factory;
	
	public static ApplicationContext getFactory() {
		if(factory==null) {
			factory = new ClassPathXmlApplicationContext("spring.xml");
		}
		return factory;
	}
	
	public static Object getBean(String name) {
		return getFactory().getBean(name);
	}
	
	public static <T> T getBean(String name, Class<T> type) {
		return type.cast(getBean(name));
	}
	
	public static <T> T getBean(Class<T> type) {
		return type.cast(getBean(type.getSimpleName()));
	}

}
