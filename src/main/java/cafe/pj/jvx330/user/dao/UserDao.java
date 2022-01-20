package cafe.pj.jvx330.user.dao;

import java.util.List;

import cafe.pj.jvx330.user.domain.User;

public interface UserDao {
	
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
	 * 이름으로 유저정보를 검색한다 
	 * @param userName
	 * @return
	 */
	List<User> findUserByName(String userName);
	
	/**
	 * 생일로 유저정보를 검색한다
	 * @param birth
	 * @return
	 */
	List<User> findUserByBirth(String birth);
	
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
	 * id로 해당 회원의 포인트 업데이트
	 * @param user
	 * @return
	 */
	void updatePointById(User user);
	
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
	
	/**
	 * 테이블 내 임플로이 찾아오기
	 * @param eid
	 * @return
	 * @author 김보령
	 */
	User findEmployeeByEid(String eid);

	/**
	 * 모든 직원 정보 가져오기
	 * @return
	 * @author 김보령
	 */
	List<User> findAllEmployee();
}
