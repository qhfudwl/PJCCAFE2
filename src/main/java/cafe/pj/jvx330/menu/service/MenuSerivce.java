package cafe.pj.jvx330.menu.service;

import cafe.pj.jvx330.domain.Menu;
import java.util.List;

public interface MenuSerivce {
	Menu addMenu(Menu menu);
	
	List<Menu> findMenusByMenuName(String menuName);
	
	List<Menu> findAllMenus();
	
	List<Menu> findAllMenusByMenuType();
	
	Menu UpdateMenuById(long id);
	
	void RemoveMenuById(long id); 
}
