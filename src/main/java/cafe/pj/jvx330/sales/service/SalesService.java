package cafe.pj.jvx330.sales.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cafe.pj.jvx330.domain.Product;
import cafe.pj.jvx330.domain.Sales;
import cafe.pj.jvx330.web.util.OrderStorage;

public interface SalesService {
	
	void addSales(Sales sales);
	
	List<Sales> findSalesByDate(Date date1, Date date2);
	
	List<Sales> findSalesByDate(Date date);
	
	List<Sales> findSalesByMenuNameAndDate(String menuName, Date date1, Date date2);
	
	List<Sales> findSalesByDate(char type, Date date1, Date date2);
	
	Sales findSalesByOrderNumber(String orderNumber);

	/*Sales updateSales(long sid);*/
	void removeSales(String orderNumber); 
	
	/**
	 * 
	 * @param dateType
	 * sd에서 받은 List<Product> 로 
	 * 화면에 뿌리기 위해 필요한 List<OrderStorage> 반
	 */
	List<OrderStorage> findOrderRecordForMenu(char dateType, char menuType);

	List<Product> sumOrder(List<Product> temp_order);
	
	void addOrderRecord(List<Product> order);
	
}
