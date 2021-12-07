package cafe.pj.jvx330.sales.dao;

import java.util.Date;
import java.util.List;

import cafe.pj.jvx330.domain.Sales;

public interface SalesDao {
	void addSales(Sales sales);
	List<Sales> findSalesByDate(Date date1, Date date2);
	List<Sales> findSalesByMenuNameAndDate(String menuName, Date date1, Date date2);
	Sales findSalesByOrderNumber(String orderNumber);
	Sales UpdateSales(long sid);
	void RemoveSales(String orderNumber);
	
}
