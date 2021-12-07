package cafe.pj.jvx330.menu.service;

import java.util.List;

import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.menu.dao.MenuDao;

public class MenuServiceImpl implements MenuService {
	private MenuDao md;
	
	public MenuServiceImpl(MenuDao menuDao) {
		this.md = menuDao;
	}

	@Override
	public Menu addMenu(Menu menu) {
		
		return null;
	}

	@Override
	public Menu findMenuByMenuName(String menuName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> findAllMenus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> findAllMenusByMenuType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu UpdateMenuById(Menu menu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void RemoveMenuById(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	
}
