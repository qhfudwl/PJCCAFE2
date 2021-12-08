package cafe.pj.jvx330.menu.controller;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Menu;

@Component("menu.controller.menuViewController")
public class MenuViewController extends MenuController {
	
	@GetMapping("/menu/menuView")
	public String menuView() {
		return "menu/menu_view";
	}
	
	@GetMapping("/menu/menuTypeView")
	public ModelAndView menuTypeView(@RequestParam char menuType) {
		
		List<Menu> menus = ms.findAllMenusByMenuType(menuType);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("menus", menus);
		mav.setViewName("menu/menu_view");
		
		return mav;
	}
}
 