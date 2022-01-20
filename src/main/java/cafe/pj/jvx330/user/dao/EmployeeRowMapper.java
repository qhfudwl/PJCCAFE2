package cafe.pj.jvx330.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cafe.pj.jvx330.user.domain.Employee;
import cafe.pj.jvx330.user.domain.User;

public class EmployeeRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new Employee(rs.getLong("id"),
				rs.getString("eid"), rs.getString("passwd"),
				rs.getString("position"), rs.getDate("regDate")); // 현재 position이 string 으로 선언되어있다.  char 으로 변경할 것
				
		return user;
	}

}
