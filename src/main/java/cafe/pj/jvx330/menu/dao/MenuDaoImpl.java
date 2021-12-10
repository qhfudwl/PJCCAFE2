package cafe.pj.jvx330.menu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cafe.pj.jvx330.domain.Menu;
@Component("menuDao")
public class MenuDaoImpl implements MenuDao {
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jt;

	@Override
	public Menu addMenu(Menu menu) {
		String sql = "INSERT INTO Menu(menuType, menuName, menuPrice, stock, imgPath)"
				+ " VALUES (?, ?, ?, ?, ?)";
		
		jt.update(sql, String.valueOf(menu.getMenuType()), menu.getMenuName(),
				menu.getMenuPrice(), menu.isStock(), menu.getImgPath());
		
		return null;
	}

	@Override
	public Menu findMenuById(long id) {
		String sql = "SELECT id, menuType, menuName, menuPrice, stock, imgPath"
				+ " FROM Menu WHERE id=?";
		
		return jt.queryForObject(sql, new MenuRowMapper(), id);
	}

	@Override
	public List<Menu> findAllMenuByMenuName(String menuName) {
		
		String sql = "SELECT id, menuType, menuName, menuPrice, stock, imgPath"
				+ " FROM Menu WHERE menuName=?";
		
		return jt.query(sql, new MenuRowMapper(), menuName);
	}

	@Override
	public List<Menu> findAllMenus() {
		
		String sql = "SELECT id, menuType, menuName, menuPrice, stock, imgPath"
				+ " FROM Menu";
		
		List<Menu> menus = jt.query(sql, new MenuRowMapper());
		
		return menus;
	}

	@Override
	public List<Menu> findAllMenusByMenuType(char menuType) {

		String sql = "SELECT id, menuType, menuName, menuPrice, stock, imgPath"
				+ " FROM Menu WHERE menuType=?";
		
		List<Menu> menus = jt.query(sql, new MenuRowMapper(), String.valueOf(menuType));
		
		return menus;
	}

	@Override
	public void updateMenuById(Menu menu) {
		
		String sql = "UPDATE Menu SET menuType=?, menuName=?, menuPrice=?, stock=?, imgPath=?"
				+ " WHERE id=?";
		
		jt.update(sql, String.valueOf(menu.getMenuType()), menu.getMenuName(),
				menu.getMenuPrice(), menu.isStock(), menu.getImgPath(), menu.getId());
	}

	@Override
	public void removeMenuById(long id) {
		
		String sql = "DELETE FROM Menu WHERE id=?";
		
		jt.update(sql, id);
	}

}
