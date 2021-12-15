package cafe.pj.jvx330.sales.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cafe.pj.jvx330.domain.Product;
import cafe.pj.jvx330.domain.Sales;

@Component("SalesDao")
public class SalesDaoImpl implements SalesDao {
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jt;

	@Override
	public void addSales(Sales sales) {
		String sql = "INSERT INTO SalesRecord(customerId, orderNumber, amount, usePoint, place)"
				+ " VALUES (?, ?, ?, ?, ?)";
		
		jt.update(sql, sales.getUser().getId(), sales.getOrderNumber(), sales.getAmount(),
				sales.getUsePoint(), String.valueOf(sales.getPlace()));
	}

	@Override
	public List<Sales> findSalesByDate(Date date1, Date date2) {
		String sql = "SELECT id, customerId, orderNumber, amount, usePoint, place, regDate"
				+ " FROM SalesRecord WHERE DATE(regDate) BETWEEN ? AND ?";
		List<Sales> sales = jt.query(sql, new SalesRowMapper(), date1, date2); 
	
		return sales;
	}

	@Override
	public List<Sales> findSalesByDate(Date date) {
		String sql = "SELECT id, customerId, orderNumber, amount, usePoint, place, regDate"
				+ " FROM SalesRecord WHERE DATE(regDate)=?";
		List<Sales> sales = jt.query(sql, new SalesRowMapper(), date); 
	
		return sales;
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
	
	public List<Product> findOrderRecordForMenu(String sDate1, String sDate2){
		String sql = "SELECT Date(regDate), menuID, quantity FROM OrderRecord WHERE Date(regDate) Between ? AND ?";
		List<Product> order = jt.query(sql, new ProductRowMapper(), java.sql.Date.valueOf(sDate1), java.sql.Date.valueOf(sDate2));
		return order;
	}	
	
	// addOrderRecord Using batch.
	public int[] addOrderRecord(List<Product> order) {
		String sql = "INSERT INTO OrderRecord(menuId, quantity) VALUES (?, ?)";
		System.out.println("message from addOrderRecord class");
		
		return jt.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Product p = order.get(i); 
				long id = p.getMenu().getId();
				int quantity = p.getQuantity();
				ps.setLong(1, id);
				ps.setInt(2, quantity);	
				System.out.println("message from addOrderRecord method");
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return order.size();
			}
		});
	}
	
}
