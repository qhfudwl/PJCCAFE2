package cafe.pj.jvx330.menu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.web.config.DataSourceConfig;

@Component("menuServiceTest")
public class MenuServiceTest {
	private MenuService ms;
	
	@Autowired
	public MenuServiceTest(MenuService menuService) {
		this.ms = menuService;
	}
	
	public static void main(String[] args) {
		GenericApplicationContext context = 
				new AnnotationConfigApplicationContext(DataSourceConfig.class);
		MenuServiceTest mst = context.getBean("menuServiceTest", MenuServiceTest.class);
		mst.addMenuTest();
	}
	
	public void addMenuTest() {
		ms.addMenu(new Menu('C', "핫아메리카노", 1000, true));
	}
}
