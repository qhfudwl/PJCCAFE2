package cafe.pj.jvx330.sales.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import cafe.pj.jvx330.config.DataSourceConfig;
import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.domain.Product;

@Component("salesServiceTest")
public class SalesServiceTest {
	
	private SalesService ss;
	
	@Autowired
	public SalesServiceTest(SalesService salesService) {
		this.ss = salesService;
	}
	
	public static void main(String[] args) {
		GenericApplicationContext context = 
				new AnnotationConfigApplicationContext(DataSourceConfig.class);
		SalesServiceTest sst = context.getBean("salesServiceTest", SalesServiceTest.class);
		
		List<Product> order = new ArrayList<Product>();
		Menu menu = new Menu(1);
		 
		Product p1 = new Product(menu, 1);
		Product p2 = new Product(menu, 2);
		Product p3 = new Product(menu, 3);
		Product p4 = new Product(menu, 4);
		Product p5 = new Product(menu, 5);
		Product p6 = new Product(menu, 6);
		
		order.add(p1);
		order.add(p2);
		order.add(p3);
		order.add(p4);
		order.add(p5);
		order.add(p6);	
	
//		sst.addOrderRecordTest(order);
		
//		System.out.println(sst);
				
	}
	// 테스트 하려면 ss.addOrderRecord() 의 반환형을 int[] 로 맞춰줘야함. 혹은 sd의 메소드를 써야함.
//	public int addOrderRecordTest(List<Product> order) {
//		System.out.println("Message From sst method");
//		int batchSize = 0;
//		
//		batchSize = ss.addOrderRecord(order).length; 
//		return batchSize;
//	}
}
