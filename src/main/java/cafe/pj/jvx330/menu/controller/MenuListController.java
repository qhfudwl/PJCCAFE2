package cafe.pj.jvx330.menu.controller;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.web.command.MenuCommand;

@Component("menu.controller.menuListController")
public class MenuListController extends MenuController {
	/**
	 * 메뉴 목록에서 메뉴 타입 선택 시 오는 화면
	 * 해당 타입에 대한 메뉴 리스트를 뽑아서 화면에 뿌려준다.
	 * @param menuType
	 * @return
	 */
	@GetMapping("/menu/findMenus")
	public ModelAndView findMenus(@RequestParam char menuType) {
		List<Menu> menus = ms.findAllMenusByMenuType(menuType);
		ModelAndView mav = new ModelAndView();
		mav.addObject("menus", menus);
		mav.setViewName("menu/menu_list");
		return mav;
	}
	
	@GetMapping("menu/updateMenu")
	public ModelAndView updateMenu(@ModelAttribute("menu") MenuCommand menuCommand) {
		
		Menu menu = new Menu(menuCommand.getId(), menuCommand.getMenuType(), 
				menuCommand.getMenuName(), menuCommand.getMenuPrice(), menuCommand.isStock(), 
				menuCommand.getImgPath());
		
		ms.updateMenuById(menu);
		
		List<Menu> menus = ms.findAllMenusByMenuType(menuCommand.getMenuType());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("menus", menus);
		mav.setViewName("menu/menu_list");
		
		return mav;
	}
	
	@GetMapping("menu/addMenu")
	public ModelAndView addMenu(@ModelAttribute("menu") MenuCommand menuCommand) {
		
		Menu menu = new Menu(menuCommand.getMenuType(), menuCommand.getMenuName(),
				menuCommand.getMenuPrice(), menuCommand.isStock(), menuCommand.getImgPath());
		
		menu = ms.addMenu(menu);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("menu", menu);
		mav.setViewName("menu/menu_list");
		
		return mav;
	}
	
	@GetMapping("menu/removeMenu")
	public ModelAndView removeMenu(@ModelAttribute("menu") MenuCommand menuCommand) {
		
		ms.removeMenuById(menuCommand.getId());
		
		List<Menu> menus = ms.findAllMenusByMenuType(menuCommand.getMenuType());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("menus", menus);
		mav.setViewName("menu/menu_list");
		
		return mav;
	}
}
