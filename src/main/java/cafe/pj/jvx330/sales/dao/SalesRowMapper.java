package cafe.pj.jvx330.sales.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import cafe.pj.jvx330.domain.Order;
import cafe.pj.jvx330.domain.Sales;
import cafe.pj.jvx330.domain.User;

public class SalesRowMapper implements RowMapper<Sales>{

	@Override
	public Sales mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sales sales = null;
		sales.setId(rs.getLong("id"));
		User user = new User();
		user.setId(rs.getLong("id"));
		sales.setOrderNumber(rs.getString("orderNumber"));
		sales.setPlace(rs.getString("place").charAt(0));
		sales.setPays(rs.getDouble("pays"));
		sales.setUserPoint(rs.getDouble("userPoint"));
		
		sales.setOrders(rs.getArray("orders"));
		sales.setRegDate(rs.getTimestamp("regdate"));
		return sales;
	}

}
