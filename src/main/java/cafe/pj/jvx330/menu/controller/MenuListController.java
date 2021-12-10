package cafe.pj.jvx330.menu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.domain.Product;
import cafe.pj.jvx330.web.command.MenuCommand;

@Controller("menu.controller.menuListController")
public class MenuListController extends MenuController {
	
	/**
	 * 메뉴 목록에서 메뉴 타입 선택 시 오는 화면
	 * 해당 타입에 대한 메뉴 리스트를 뽑아서 화면에 뿌려준다.
	 * @param menuType
	 * @return
	 */
	@GetMapping("/menu/viewMenuList")
	public ModelAndView viewMenuList(@RequestParam("choiceMenu") char choiceMenu) {
		List<Menu> menus = ms.findAllMenusByMenuType(choiceMenu);
		ModelAndView mav = new ModelAndView();
		mav.addObject("menus", menus);
		mav.addObject("choiceMenu", choiceMenu);
		mav.setViewName("menu/menu_list");
		return mav;
	}
	
	@PostMapping("/menu/updateMenu")
	public ModelAndView updateMenu(@ModelAttribute("menu") MenuCommand menuCommand) {
		
		Menu menu = new Menu(menuCommand.getId(), menuCommand.getMenuType(), 
				menuCommand.getMenuName(), menuCommand.getMenuPrice(), menuCommand.isStock(), 
				menuCommand.getImgPath());
		
		ms.updateMenuById(menu);
		
		List<Menu> menus = ms.findAllMenusByMenuType(menuCommand.getMenuType());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("menus", menus);
		mav.addObject("choiceMenu", menu.getMenuType());
		mav.setViewName("menu/menu_list");
		
		return mav;
	}
	
	@PostMapping("/menu/addMenu")
	public ModelAndView addMenu(@ModelAttribute("menu") MenuCommand menuCommand) {
		
		Menu menu = new Menu(menuCommand.getMenuType(),
				menuCommand.getMenuName(), menuCommand.getMenuPrice(), menuCommand.isStock(),
				menuCommand.getImgPath());
		
		ms.addMenu(menu);
		
		List<Menu> menus = ms.findAllMenusByMenuType(menuCommand.getMenuType());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("menus", menus);
		mav.addObject("choiceMenu", menu.getMenuType());
		mav.setViewName("menu/menu_list");
		
		return mav;
	}
	
	@PostMapping("/menu/removeMenu")
	public ModelAndView removeMenu(@RequestParam("choiceItem") long choiceItem,
			@RequestParam("choiceMenu") char choiceMenu) {
		ms.removeMenuById(choiceItem);
		
		List<Menu> menus = ms.findAllMenusByMenuType(choiceMenu);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("menus", menus);
		mav.addObject("choiceMenu", choiceMenu);
		mav.setViewName("menu/menu_list");
		
		return mav;
	}
}
