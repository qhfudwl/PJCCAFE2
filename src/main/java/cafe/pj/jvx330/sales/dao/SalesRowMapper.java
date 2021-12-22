package cafe.pj.jvx330.sales.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import cafe.pj.jvx330.sales.domain.Product;
import cafe.pj.jvx330.sales.domain.Sales;
import cafe.pj.jvx330.user.domain.Customer;
import cafe.pj.jvx330.user.domain.User;

public class SalesRowMapper implements RowMapper<Sales>{
	

	@Override
	public Sales mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new Customer();
		user.setId(rs.getLong("customerId"));
		
		Sales sales = new Sales();
		sales.setId(rs.getLong("id"));
		sales.setUser(user);
		sales.setOrderNumber(rs.getString("orderNumber"));
		sales.setAmount(rs.getDouble("amount"));
		sales.setUsePoint(rs.getDouble("usePoint"));
		sales.setPlace(rs.getString("place").charAt(0));
		sales.setRegDate(rs.getDate("regDate"));
		
		return sales;
	}

}
