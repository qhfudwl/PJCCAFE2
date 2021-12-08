package cafe.pj.jvx330.menu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cafe.pj.jvx330.domain.Menu;

/**
 * 모든 칼럼 정보를 담은 메뉴 객체를 뽑을 때 사용
 * @author 김보령
 *
 */
public class MenuRowMapper implements RowMapper<Menu> {

	@Override
	public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
		Menu menu = new Menu(rs.getLong("id"), rs.getString("menuType").charAt(0),
				rs.getString("menuName"), rs.getDouble("menuPrice"), 
				rs.getBoolean("stock"), rs.getString("imgPath"));
		
		return menu;
	}
	
}
