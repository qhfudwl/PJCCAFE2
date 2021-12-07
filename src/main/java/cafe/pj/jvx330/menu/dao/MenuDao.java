package cafe.pj.jvx330.menu.dao;

import java.util.List;

import cafe.pj.jvx330.domain.Menu;

public interface MenuDao {
	Menu addMenu(Menu menu);
	
	List<Menu> findMenusByMenuName(String menuName);
	
	List<Menu> findAllMenus();
	
	List<Menu> findAllMenusByMenuType();
	
	Menu UpdateMenuById(long id);
	
	void RemoveMenuById(long id); 
}
