package cafe.pj.jvx330.menu.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.web.config.DataSourceConfig;

@SpringJUnitConfig(classes= {DataSourceConfig.class})
public class MenuDaoTest {

	@Autowired
	private MenuDao menuDao;
	
	@Test
	public void findMenuById() {
		assertTrue(menuDao.findMenuById(1).getMenuName().equals("레몬에이드"));
	}
	
	@Test
	@Transactional
	@Commit
	public void addMenu() {
		Menu m = new Menu();
		m.setMenuType('C');
		m.setMenuName("호떡라떼");
		m.setMenuPrice(500);
		m.setStock(true);
		m.setImgPath("ddd");
		menuDao.addMenu(m);
		System.out.println("completed");
	}
}
