package cafe.pj.jvx330.sales.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import cafe.pj.jvx330.domain.Customer;
import cafe.pj.jvx330.domain.Product;
import cafe.pj.jvx330.domain.Sales;
import cafe.pj.jvx330.domain.User;

public class SalesRowMapper implements RowMapper<Sales>{
	

	@Override
	public Sales mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new Customer();
		user.setId(rs.getLong("customerId"));
		 
//		String[] temp = orderListString.split("/");
//		String[] temp2;
//		
//		for(String temp1 : temp) {
//			temp2 = temp1.split(",");
//		}
		
		
		
		Sales sales = new Sales(rs.getLong("id"), user, rs.getString("orderNumber"), rs.getString("place").charAt(0), 
				rs.getDouble("amount"), rs.getDouble("usePoint"), rs.getString("orderList"), rs.getDate("regDate"));
		
		
		return null;
	}

}
