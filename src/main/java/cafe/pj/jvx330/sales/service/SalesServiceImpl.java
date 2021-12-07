package cafe.pj.jvx330.sales.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import cafe.pj.jvx330.domain.Sales;

@Component("SalesService")
public class SalesServiceImpl implements SalesService{

	@Override
	public void addSales(Sales sales) {
		
		
	}

	@Override
	public List<Sales> findSalesByDate(Date date1, Date date2) {
		
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
	public Sales UpdateSales(long sid) {
		
		return null;
	}

	@Override
	public void RemoveSales(String orderNumber) {
		
		
	}

}
