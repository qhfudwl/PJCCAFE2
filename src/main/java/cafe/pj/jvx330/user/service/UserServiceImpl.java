package cafe.pj.jvx330.user.service;

import java.util.List;

import cafe.pj.jvx330.domain.User;
import cafe.pj.jvx330.user.dao.UserDao;

public class UserServiceImpl implements UserService{
	private UserDao dao;
	private List<User> users;
	private User user;
	public UserServiceImpl() {
		
		
	}
	

	@Override
	public User addUser(User user) {
		dao.addUser(user);
		return user;
	}

	@Override
	public List<User> findUsersByPhone(String phone) {
		
		return null;
	}

	@Override
	public List<User> findAllUsers() {
		
		return null;
	}

	@Override
	public User UpdateUserById(long id) {
		long id2 = user.getId();
		dao.updateUserById(id2);
		
		
		return user;
	}

	@Override
	public void RemoveUserId(long id) {
		
		
	}

}
