package cafe.pj.jvx330.menu.service;

import java.util.List;

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
//		mst.addMenuTest();
//		mst.findMenuByMenuNameTest("핫아메리카노");
//		mst.findAllMenus();
//		mst.findAllMenusByMenuTypeTest('C');
//		mst.updateMenuByIdTest(new Menu(4, 'C', "핫아메리카노", 5000, true));
//		mst.removeMenuByIdTest(4);
				
		
	}
	
	public void addMenuTest() {
		ms.addMenu(new Menu('B', "자몽에이드", 1000.0, true, ""));
		ms.addMenu(new Menu('B', "레몬에이드", 1000.0, true, ""));
		ms.addMenu(new Menu('B', "자스민티", 1000.0, true, ""));
		ms.addMenu(new Menu('C', "핫아메리카노", 1500.0, true, ""));
		ms.addMenu(new Menu('C', "아이스아메리카노", 1500.0, true, ""));
		ms.addMenu(new Menu('C', "핫카페라떼", 2000.0, true, ""));
		ms.addMenu(new Menu('C', "아이스카페라떼", 2000.0, true, ""));
		ms.addMenu(new Menu('C', "핫카페모카", 2500.0, true, ""));
		ms.addMenu(new Menu('C', "아이스카페모카", 2500.0, true, ""));
		ms.addMenu(new Menu('F', "햄치즈샌드위치", 3000.0, true, ""));
		ms.addMenu(new Menu('F', "촉촉한초코칩쿠키", 1000.0, true, ""));
		ms.addMenu(new Menu('F', "알록달록마카롱", 1000.0, true, ""));
	}
	
	public void findMenuByMenuNameTest(String menuName) {
		Menu menu = ms.findAllMenuByMenuName(menuName).get(0);
		System.out.println(menu);
	}
	
	public void findAllMenus() {
		List<Menu> menus = ms.findAllMenus();
		for(Menu m : menus) {
			System.out.println(m);
		}
	}
	
	public void findAllMenusByMenuTypeTest(char menuType) {
		List<Menu> menus = ms.findAllMenusByMenuType(menuType);
		for(Menu m : menus) {
			System.out.println(m);
		}
	}
	
	public void updateMenuByIdTest(Menu menu) {
		ms.updateMenuById(menu);
		Menu m = ms.findAllMenuByMenuName(menu.getMenuName()).get(0);
		System.out.println(m);
	}
	
	public void removeMenuByIdTest(long id) {
		ms.removeMenuById(id);
		findAllMenus();
	}
}
