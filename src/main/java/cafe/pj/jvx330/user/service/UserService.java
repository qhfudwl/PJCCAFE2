package cafe.pj.jvx330.user.service;

import java.util.List;

import cafe.pj.jvx330.domain.User;

public interface UserService {
	User addUser(User user);
	List<User> findUsersByPhone(String phone);
	List<User> findAllUsers();
	User UpdateUserById(long id);
	void RemoveUserId(long id);
}
