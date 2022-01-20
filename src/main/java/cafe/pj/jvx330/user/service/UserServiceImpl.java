package cafe.pj.jvx330.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cafe.pj.jvx330.user.dao.UserDao;
import cafe.pj.jvx330.user.domain.Employee;
import cafe.pj.jvx330.user.domain.User;
import cafe.pj.jvx330.web.util.Validator;
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao ud;

	@Autowired
	private Validator validator;

	public UserServiceImpl() {
		
	}

	@Override 
	public User addUser(User user) {
		ud.addUser(user);
		return user;
	}
	

	@Override
	public List<User> findUsersByPhone(String phone) {
		List<User> users = ud.findUsersByPhone(phone);
		return users;
	}
	@Override
	public List<User> findAllUsers() {
		List<User> users;
		users = ud.findAllUsers();
		return users;
	}

	@Override
	public User updateUserById(User user) {
		return ud.updateUserById(user);
		
	}

	@Override
	public void removeUserId(long id) {
		ud.removeUserId(id);
	}


	@Override
	public User findUserById(long id) {
		
		return ud.findUserById(id);
	}

	@Override
	public List<User> findUserByName(String name) {
		
		return ud.findUserByName(name);
	}

	@Override
	public List<User> findUserByBirth(String birth) {
		
		return ud.findUserByBirth(birth);
	}

	@Override
	public User findEmployeeByEid(String eid) {
		return ud.findEmployeeByEid(eid);
	}

	@Override
	public List<User> findAllEmployee() {
		return ud.findAllEmployee();
	}

	@Override
	public void updatePointById(User user) {
		ud.updatePointById(user);
	}

	@Override
	public boolean isEmployee(String eid) {
		User user = ud.findEmployeeByEid(eid);
		
		if (validator.isEmpty(user)) {
			return false;
		}
		
		return true;
	}

}
