package cafe.pj.jvx330.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cafe.pj.jvx330.domain.Customer;
import cafe.pj.jvx330.domain.User;
import cafe.pj.jvx330.user.dao.UserDao;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao dao;
	public UserServiceImpl() {
		
	}

	@Override 
	public User addUser(User user) {
		dao.addUser(user);
		return user;
	}
	

	@Override
	public List<User> findUsersByPhone(String phone) {
		List<User> users = dao.findUsersByPhone(phone);
		return users;
	}
	@Override
	public List<User> findAllUsers() {
		List<User> users;
		users = dao.findAllUsers();
		return users;
	}

	@Override
	public User updateUserById(User user) {
		return dao.updateUserById(user);
		
	}

	@Override
	public void removeUserId(long id) {
		dao.removeUserId(id);
	}


	@Override
	public User findUserById(long id) {
		
		return dao.findUserById(id);
	}

	@Override
	public List<User> findUserByName(String name) {
		
		return dao.findUserByName(name);
	}

	@Override
	public List<User> findUserByBirth(String birth) {
		
		return dao.findUserByBirth(birth);
	}

}
