package cafe.pj.jvx330.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cafe.pj.jvx330.user.domain.Customer;
import cafe.pj.jvx330.user.domain.Employee;
import cafe.pj.jvx330.user.domain.User;

@Component("userDao")
public class UserDaoImpl implements UserDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jt;
	
	
	/**
	 * 커스터머에 테이블에 고객 추가
	 * @author 정원식
	 *
	 */

	@Override
	public void addUser(User user) {
		
		if(user instanceof Customer) {
			String sql ="INSERT INTO Customer(name,phone,birth)"
					+ "VALUES(? , ? , ?)";
			Customer customer = (Customer)user;
			jt.update(sql,customer.getCustomerName(), customer.getPhone(),
					customer.getBirth());
				
		}
	}
	
	
	/**
	 * 고객 전체 회원목록 보기
	 */
	@Override
	public List<User> findAllUsers() {
		List<User> users = jt.query("SELECT id, name, phone, birth, point, regDate "
				+ "From Customer ORDER BY id ", new UserRowMapper());
		return users;
	}
	
	/**
	 * 고객 정보 변경하기
	 */

	@Override
	public User updateUserById(User user) {
		
		String sql = "UPDATE Customer SET name = ?,phone = ?,birth = ?,point = ? WHERE id = ?";
		
		jt.update(sql, ((Customer)user).getCustomerName(), ((Customer)user).getPhone(),
				((Customer)user).getBirth(), ((Customer)user).getPoint(), user.getId());
		
		return user;		
	}
	
	//안씀
	public void updateUser(User user) {
		String sql = "UPDATE Customer SET nema = ?, phone = ?, birth = ?, point = ? ";
		jt.query(sql, new UserRowMapper());
	}
	
	/**
	 * 고객 삭제하기
	 */

	@Override
	public void removeUserId(long id) {
		String sql = "DELETE FROM Customer WHERE id =?";
		
		jt.update(sql, id);
		
	}
	
	/**
	 * 폰번호로 고객정보 찾기
	 */

	@Override
	public List<User> findUsersByPhone(String phone) {
		String sql = "SELECT id, name, phone, birth, point, regDate"
				+ " FROM Customer WHERE phone = ? ORDER BY id ASC";
		
		List<User> users = jt.query(sql, new UserRowMapper(),phone);
		return users;
	}
	
	/**
	 * 고객 아이디로 검색하기
	 */
	@Override
	public User findUserById(long id) {
		String sql = "SELECT id, name, phone, birth, point, regDate From Customer WHERE id= ? ORDER BY id ASC";
		
		return jt.queryForObject(sql, new UserRowMapper(), id);
	}

	/**
	 * 고객 이름으로 검색히가
	 */
	@Override
	public List<User> findUserByName(String userName) {
		String sql = "SELECT id, name, phone, birth, point, regDate From Customer WHERE name = ? ORDER BY id ASC";
	
	return jt.query(sql, new UserRowMapper(), userName);
	}

	/**
	 * 고객 생일로 검색하기
	 */
	@Override
	public List<User> findUserByBirth(String birth) {
		String sql = "SELECT id, name, phone, birth, point, regDate From Customer WHERE birth = ? ORDER BY id ASC";
		return jt.query(sql, new UserRowMapper(), birth);
	}


	@Override
	public User findEmployeeByEid(String eid) {
		String sql = "SELECT id, eid, passwd, position, regDate From Employee WHERE eid=?";
		
		User user = null;
		
		try {
			user = jt.queryForObject(sql, new EmployeeRowMapper(), eid);
		} catch (EmptyResultDataAccessException e) {
			user = null;
		}
		
		return user;
	}


	@Override
	public List<User> findAllEmployee() {
		String sql = "SELECT id, eid, passwd, position, regDate From Employee";
		return jt.query(sql, new EmployeeRowMapper());
	}


	@Override
	public void updatePointById(User user) {
		String sql = "UPDATE Customer SET point=? WHERE id=?";
		jt.update(sql, ((Customer)user).getPoint(), user.getId());
	}
	
}
