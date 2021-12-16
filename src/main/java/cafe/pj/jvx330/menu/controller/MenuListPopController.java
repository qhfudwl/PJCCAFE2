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

/**
 * 메뉴 팝업창 컨트롤러
 * @author 김보령
 *
 */
@Controller("menu.controller.menuListPopController")
public class MenuListPopController extends MenuController {
	
	@PostMapping("/menu/pupUploadImg")
	public String popUploadImg() {
		return "menu/menu_img_popup_upload";
	}
	
	/**
	 * 메뉴 id 를 받아 팝업창에 표시
	 * @param choiceItem
	 * @return
	 */
	@PostMapping("/menu/popUpdateMenu")
	public ModelAndView popUpdateMenu(@RequestParam("choiceItem") long choiceItem) {
		Menu menu = ms.findMenuById(choiceItem);
		ModelAndView mav = new ModelAndView();
		mav.addObject("menu", menu);
		mav.setViewName("menu/menu_list_popup_update");
		
		return mav;
	}
	
	/**
	 * 메뉴 타입을 팝업창에 적용
	 * @param choiceMenu
	 * @return
	 */
	@PostMapping("/menu/popAddMenu")
	public ModelAndView popAddMenu(@RequestParam("choiceMenu") char choiceMenu) {
		Menu menu = new Menu();
		menu.setMenuType(choiceMenu);
		ModelAndView mav = new ModelAndView();
		mav.addObject("menu", menu);
		mav.setViewName("menu/menu_list_popup_add");
		
		return mav;
	}
}
