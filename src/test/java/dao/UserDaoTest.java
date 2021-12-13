package dao;

import java.util.List;

import cafe.pj.jvx330.domain.User;

public interface UserDaoTest {
	
	/**
	 * 유저를 추가 한다.
	 * @param user
	 */
	void addUser(User user);
	
	
	/**
	 * 휴대폰번호를 이용한 고객찾기
	 * 포인트 적립이나 사용이 잘못 되었을 떄 조회하기 편하다
	 * @param phone
	 * @return
	 */
	List<User> findUsersByPhone(String phone);
	
	
	/**
	 * 모든 회원을 조회한다.
	 * @return
	 */
	List<User> findAllUsers();
	
	
	/**
	 * 회원을 중복값이 없는 아이디로 조회를 해
	 * 유저정보를 갱신 해준다.
	 * @param user
	 * @return
	 */
	User updateUserById(User user);
	
	
	/**
	 * 유저의 정보를 삭제 해준다.
	 * @param id
	 */
	void removeUserId(long id);
	
	
	/**
	 * 아이디값을 이용해 유저를 찾아준다.
	 * 회원 정보를 업데이트 할 때 사용.
	 * @param id
	 * @return
	 */
	User findUserById(long id);
	
}
