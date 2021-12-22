package cafe.pj.jvx330.sales.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import cafe.pj.jvx330.menu.domain.Menu;
import cafe.pj.jvx330.sales.domain.Product;
import cafe.pj.jvx330.sales.domain.Sales;
import cafe.pj.jvx330.user.domain.Customer;
import cafe.pj.jvx330.user.domain.User;

public class ProductRowMapper implements RowMapper<Product>{
	

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Menu menu = new Menu();
		menu.setId(rs.getLong("menuId"));
		Product product = new Product(menu, rs.getInt("quantity"));
		
		return product;
	}
	

}
