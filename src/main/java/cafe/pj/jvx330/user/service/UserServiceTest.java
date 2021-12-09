package cafe.pj.jvx330.user.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import cafe.pj.jvx330.domain.Customer;
import cafe.pj.jvx330.domain.User;
import cafe.pj.jvx330.user.dao.UserDao;
import cafe.pj.jvx330.web.config.DataSourceConfig;

@Component("userServiceTest")
public class UserServiceTest {
	private static UserService us;
	private static UserDao dao;
	
	@Autowired
	public UserServiceTest(UserService userService) {
		this.us = userService;
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DataSourceConfig.class);
		UserServiceTest test = context.getBean("userServiceTest",UserServiceTest.class);
		
		//test.serviceTestAdd();
		//test.serviceTestFindAll();
		//test.serviceTestFindPhone("12312341231");
		//test.userByIdTest(101);
		//test.userUpdate(new Customer(112,"정김식","01012341234","1234567",0.0));
		//test.userRemove(111);
	}
	public void serviceTestAdd() {
		us.addUser(new Customer("정원식","12312341231","19910630"));
		us.addUser(new Customer("정투식","12312341232","19920630"));
		us.addUser(new Customer("정삼식","12312341233","19930630"));
		us.addUser(new Customer("정사식","12312341234","19940630"));
		us.addUser(new Customer("정오식","12312341235","19950630"));	
	}
	public List<User> serviceTestFindAll() {
		System.out.println(us.findAllUsers());
		return us.findAllUsers();
		
	}
	public void serviceTestFindPhone(String phone) {
		List<User> users =  us.findUsersByPhone(phone);
		System.out.println(users);
		
	}
	public void userByIdTest(long id) {
		User user = us.findUserById(id);
		System.out.println(user);
	}
	public void userUpdate(User user) {
		long id = user.getId();
		us.findUserById(id);
		user = us.updateUserById(user);
		
		System.out.println(user);
	}
	public void userRemove(long id) {
		us.removeUserId(id);
	}
}
