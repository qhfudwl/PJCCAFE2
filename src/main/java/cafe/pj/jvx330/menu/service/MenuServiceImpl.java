package cafe.pj.jvx330.menu.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.pj.jvx330.menu.dao.MenuDao;
import cafe.pj.jvx330.menu.domain.Menu;
import cafe.pj.jvx330.menu.util.FileAuxiliaryFunction;
import cafe.pj.jvx330.web.util.Validator;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
	@Resource(name="menuDao")
	private MenuDao md;
	
	@Autowired
	private Validator validator;
	@Autowired
	private FileAuxiliaryFunction fileAux;

	@Transactional
	@Override
	public Menu addMenu(Menu menu) {
		md.addMenu(menu);
		return findLastMenuByMenuType(menu.getMenuType());
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

	@Transactional
	@Override
	public void removeMenuById(HttpServletRequest request, Menu menu) {
		fileAux.removeImgFile(request, menu.getMenuType(), menu.getMenuName(), fileAux.getImgName(menu.getImgPath()));
		md.removeMenuById(menu.getId());
	}

	@Override
	public Menu findLastMenuByMenuType(char menuType) {
		return md.findLastMenuByMenuType(menuType);
	}

	@Transactional
	@Override
	public boolean isMenuName(String menuName) {
		Menu menu = md.findMenuByMenuName(menuName);
		if (validator.isEmpty(menu)) {
			return false;
		}
		return true;
	}

	@Override
	public List<Menu> findNewMenus(){
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:ss:mm");
		Date date = new Date();
		LocalDate ld = new LocalDate();
		date = format.parse()
				format.parse(sDateList.get(0)); */
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = sdf.format(cal.getTime());
		
		cal.add(Calendar.DATE, 1-cal.get(Calendar.DAY_OF_MONTH));
		String date2 = sdf.format(cal.getTime());
		
		List<Menu> menu = md.findNewMenus(date1,date2);
		
		return menu;

	}
	
	
}
