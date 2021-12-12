package cafe.pj.jvx330.user.service;

import java.util.List;

import cafe.pj.jvx330.domain.User;

public interface UserService {
	/**
	 * 유저를 회원가입할 때 사용
	 * @param user
	 * @return
	 */
	User addUser(User user);
	
	/**
	 * 회원을 휴대폰번호를 이용해 찾아준다
	 * @param phone
	 * @return
	 */
	List<User> findUsersByPhone(String phone);
	
	/**
	 * 회원 이름으로 유저를 찾아준다.
	 * @param name
	 * @return
	 */
	List<User> findUserByName(String name);
	
	/**
	 * 회원 생일로 유저를 찾아준다.
	 * @param birth
	 * @return
	 */
	List<User> findUserByBirth(String birth);
	
	/**
	 * 모든회원을 검색한다.
	 * @return
	 */
	List<User> findAllUsers();
	
	
	/**
	 * 회원을 삭제할 때 사용한다
	 * @param id
	 */
	void removeUserId(long id);
	
	
	/**
	 * 회원정보를 갱신
	 * @param user
	 * @return
	 */
	User updateUserById(User user);
	
	
	/**
	 * 회원 아이디로 회원을 찾아온다.
	 * @param id
	 * @return
	 */
	User findUserById(long id);
	
}
