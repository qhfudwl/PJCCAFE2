package cafe.pj.jvx330.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import cafe.pj.jvx330.config.DataSourceConfig;

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
				
	}
	
}
