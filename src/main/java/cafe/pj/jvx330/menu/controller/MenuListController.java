package cafe.pj.jvx330.menu.controller;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Menu;

@Component("menu.controller.menuListController")
public class MenuListController extends MenuController {
	/**
	 * 메뉴 목록에서 메뉴 타입 선택 시 오는 화면
	 * 해당 타입에 대한 메뉴 리스트를 뽑아서 화면에 뿌려준다.
	 * @param menuType
	 * @return
	 */
	@GetMapping("/menu/menuList")
	public ModelAndView menuList(@RequestParam char menuType) {
		List<Menu> menus = ms.findAllMenusByMenuType(menuType);
		ModelAndView mav = new ModelAndView();
		mav.addObject("menus", menus);
		mav.setViewName("menu/menu_list");
		return mav;
	}
	
	@GetMapping("menu/menuUpdate")
	public ModelAndView menuUpdate(@ModelAttribute("menu") MenuCommand menuCommand) {
		
		return null;
	}
}
