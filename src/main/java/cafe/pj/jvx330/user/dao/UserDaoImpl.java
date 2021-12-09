package cafe.pj.jvx330.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cafe.pj.jvx330.domain.Customer;
import cafe.pj.jvx330.domain.Employee;
import cafe.pj.jvx330.domain.User;


public class UserDaoImpl implements UserDao {
	private JdbcTemplate jdbcTemplate;
	
	public UserDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 커스터머에 테이블에 고객 추가
	 * @author 정원식
	 *
	 */

	@Override
	public void addUser(User user) {
		
		if(user instanceof Customer) {
			Customer customer = (Customer)user;
			jdbcTemplate.update("INSERT INTO Customer(name, phone, birth) "
					+ "VALUES( ?, ?, ?)",customer.getCustomerName(), customer.getPhone(),
					customer.getBirth());
				
		}
	}
	
	/**
	 * 폰번호로 고객정보 찾기
	 */

	@Override
	public List<User> findUsersByPhone(String phone) {
		String sql = "SELECT id, name, phone, birth, point ,regDate"
				+ "FROM Customer WHERE phone = ?";
		
		List<User> users = jdbcTemplate.query(sql, new UserRowMapper());
		return users;
	}
	
	/**
	 * 고객 전체 회원목록 보기
	 */
	@Override
	public List<User> findAllUsers() {
		
		List<User> users = jdbcTemplate.query("SELECT id, name, phone, birth, point From Customer", new UserRowMapper());
		return users;
	}
	
	/**
	 * 고객 정보 변경하기
	 */

	@Override
	public void updateUserById(long id) {
		
		String sql = "UPDATE Customer SET name = ?,phone = ?,birth = ?,point = ? WHERE id = ?"
				+ "VALUES(?, ?, ?, ? ,?)";
		User user;
		Customer customer = (Customer)user;
		jdbcTemplate.update(sql,customer.getCustomerName(), customer.getPhone(),
				customer.getBirth(),customer.getPoint(),customer.getId());
		
		
		
	}
	
	
	public void updateUser(User user) {
		String sql = "UPDATE Customer SET nema = ?, phone = ?, birth = ?, point = ? "
				+ "VALUES (?, ?, ?, ?)";
		jdbcTemplate.query(sql, new UserRowMapper());
	}
	
	/**
	 * 고객 삭제하기
	 */

	@Override
	public void removeUserId(long id) {
		String sql = "DELETE FROM Customer WHERE id =?";
		
		jdbcTemplate.update(sql, id);
		
	}


	
}
