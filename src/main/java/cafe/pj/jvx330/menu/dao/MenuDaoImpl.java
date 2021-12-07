package cafe.pj.jvx330.menu.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import cafe.pj.jvx330.domain.Menu;

public class MenuDaoImpl implements MenuDao {
	private JdbcTemplate jt;
	
	public MenuDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jt = jdbcTemplate;
	}

	@Override
	public Menu addMenu(Menu menu) {
		String sql = "INSERT INTO Menu(menuType, menuName, menuPrice, stock)"
				+ " VALUES (?, ?, ?, ?)";
		
		jt.update(sql, String.valueOf(menu.getMenuType()), menu.getMenuName(),
				menu.getMenuPrice(), menu.isStock());
		
		return null;
	}

	@Override
	public Menu findMenuByMenuName(String menuName) {
		
		String sql = "SELECT id, menuType, menuName, menuPrice, stock"
				+ " FROM Menu WHERE menuName=?";
		
		return jt.queryForObject(sql, new MenuRowMapper(), menuName);
	}

	@Override
	public List<Menu> findAllMenus() {
		
		String sql = "SELECT id, menuType, menuName, menuPrice, stock"
				+ " FROM Menu";
		
		List<Menu> menus = jt.query(sql, new MenuRowMapper());
		
		return menus;
	}

	@Override
	public List<Menu> findAllMenusByMenuType(char menuType) {

		String sql = "SELECT id, menuType, menuName, menuPrice, stock"
				+ " FROM Menu WHERE menuType=?";
		
		List<Menu> menus = jt.query(sql, new MenuRowMapper(), String.valueOf(menuType));
		
		return menus;
	}

	@Override
	public void UpdateMenuById(Menu menu) {
		
		String sql = "UPDATE Menu SET (menuType, menuName, menuPrice, stock)"
				+ "=(?, ?, ?, ?) WHERE id=?";
		
		jt.update(sql, String.valueOf(menu.getMenuType()), menu.getMenuName(),
				menu.getMenuPrice(), menu.isStock(), menu.getId());
	}

	@Override
	public void RemoveMenuById(Menu menu) {
		
		String sql = "DELETE FROM Menu WHERE id=?";
		
		jt.update(sql, menu.getId());
	}

}
