package cafe.pj.jvx330.user.service;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cafe.pj.jvx330.user.dao.UserDao;
import cafe.pj.jvx330.web.config.DataSourceConfig;


public class UserServiceTest {
	private static UserService us;
	private static UserDao dao;
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DataSourceConfig.class);
		
	}
}
