package cafe.pj.jvx330.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.menu.dao.MenuDao;

@Component("menuService")
public class MenuServiceImpl implements MenuService {
	private MenuDao md;
	
	@Autowired
	public MenuServiceImpl(MenuDao menuDao) {
		this.md = menuDao;
	}

	@Override
	public Menu addMenu(Menu menu) {
		md.addMenu(menu);
		return menu;
	}

	@Override
	public Menu findMenuByMenuName(String menuName) {
		return md.findMenuByMenuName(menuName);
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
