package cafe.pj.jvx330.sales.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import cafe.pj.jvx330.config.DataSourceConfig;
import cafe.pj.jvx330.domain.Product;
import cafe.pj.jvx330.domain.Sales;
import cafe.pj.jvx330.sales.dao.SalesDao;
import cafe.pj.jvx330.sales.dao.SalesDaoImpl;
import cafe.pj.jvx330.web.util.OrderStorage;

@Component("setter_dateTest")
public class Setter_dateTest {
	private SalesService ss;
	
	@Autowired
	public Setter_dateTest(SalesService salesService) {
		this.ss = salesService;
	}
	
	public static void main(String[] args) {
		GenericApplicationContext context = 
				new AnnotationConfigApplicationContext(DataSourceConfig.class);
		Setter_dateTest sdt = context.getBean("setter_dateTest", Setter_dateTest.class);
		SalesDao sd = new SalesDaoImpl();
		List<Sales> sales;
		List<Product> order = null;
		
		sdt.test_findOrderRecord();
		
	}
	
	public void test_findOrderRecord() {
		List<OrderStorage> osList = new ArrayList<OrderStorage>();
		osList.addAll(ss.findOrderRecordForMenu('M','B')) ;
		
		for(int i=0; i<osList.size(); i++) {
			System.out.println("weekDate : " + osList.get(i).getWeekDate()+" menuName: " + osList.get(i).getMenuName() + 
					" / Quantity : " + osList.get(i).getQuantity()
					+ "/ Price : " + osList.get(i).getPrice()); 
			
		}
		
	}
	
	
	
}
