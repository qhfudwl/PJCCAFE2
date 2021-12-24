package cafe.pj.jvx330.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cafe.pj.jvx330.domain.Customer;
import cafe.pj.jvx330.domain.User;
//인터페이스를 상속 받을 때 implements
//클래스 extends
public class UserRowMapper implements RowMapper<User>{
	

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new Customer(rs.getLong("id"),
				rs.getString("name"), rs.getString("phone"),
				rs.getString("birth"), rs.getDouble("point"), rs.getDate("regDate"));
				
		return user;
	}
	
}
