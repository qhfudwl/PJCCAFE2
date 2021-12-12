package cafe.pj.jvx330.menu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.menu.dao.MenuDao;

@Component("menuService")
public class MenuServiceImpl implements MenuService {
	
	@Resource(name="menuDao")
	private MenuDao md;

	@Override
	public Menu addMenu(Menu menu) {
		md.addMenu(menu);
		return md.findAllMenuByMenuName(menu.getMenuName()).get(0);
		// 마지막거를 가져오면 더 빠르다.
	}

	@Override
	public Menu findMenuById(long id) {
		return md.findMenuById(id);
	}

	@Override
	public List<Menu> findAllMenuByMenuName(String menuName) {
		return md.findAllMenuByMenuName(menuName);
	}

	@Override
	public List<Menu> findAllMenus() {
		return md.findAllMenus();
	}

	@Override
	public List<Menu> findAllMenusByMenuType(char menuType) {
		return md.findAllMenusByMenuType(menuType);
	}

	@Override
	public Menu updateMenuById(Menu menu) {
		md.updateMenuById(menu);
		return menu;
	}

	@Override
	public void removeMenuById(long id) {
		md.removeMenuById(id);
	}
	
	
}
