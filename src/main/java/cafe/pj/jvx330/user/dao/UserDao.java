package cafe.pj.jvx330.user.dao;

import java.util.List;

import cafe.pj.jvx330.domain.User;

public interface UserDao {
	void addUser(User user);
	List<User> findUsersByPhone(String phone);
	List<User> findAllUsers();
	void updateUserById(long id);
	void removeUserId(long id);
	
}
