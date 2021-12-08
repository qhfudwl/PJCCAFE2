package cafe.pj.jvx330.sales.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cafe.pj.jvx330.domain.Sales;

@Component("SalesDao")
public class SalesDaoImpl implements SalesDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SalesDaoImpl(JdbcTemplate jdbcTemplate) {
		jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addSales(Sales sales) {
		
		
	}

	@Override
	public List<Sales> findSalesByDate(Date date1, Date date2) {
		String sql = "SELECT orderList, regDate FROM SalesRecord WHERE DATE(regDate) <= ? && DATE(regDate) >= ?";
//		List<Sales> sales = jdbcTemplate.query(sql, new SalesRowMapper(), date1, date2); 
//		
//		
//		return sales;
		return null;
	}

	@Override
	public List<Sales> findSalesByMenuNameAndDate(String menuName, Date date1, Date date2) {
		
		return null;
	}

	@Override
	public Sales findSalesByOrderNumber(String orderNumber) {
		
		return null;
	}

	@Override
	public Sales updateSales(long sid) {
		
		return null;
	}

	@Override
	public void removeSales(String orderNumber) {
		
		
	}

		
	
}
