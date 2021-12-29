package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cafe.pj.jvx330.user.domain.Customer;
import cafe.pj.jvx330.user.domain.User;

public class UserRowMapperTest implements RowMapper<User>{
	

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new Customer(rs.getLong("id"),
				rs.getString("name"), rs.getString("phone"),
				rs.getString("birth"), rs.getDouble("point"), rs.getDate("regDate"));
				
		return user;
	}
	
}
