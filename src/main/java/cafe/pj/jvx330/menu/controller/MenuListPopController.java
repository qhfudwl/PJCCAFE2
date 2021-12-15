package cafe.pj.jvx330.menu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Menu;

/**
 * 메뉴 팝업창 컨트롤러
 * @author 김보령
 *
 */
@Controller("menu.controller.menuListPopController")
public class MenuListPopController extends MenuController {
	
	/**
	 * 메뉴 id 를 받아 팝업창에 표시
	 * @param choiceItem
	 * @return
	 */
	@PostMapping("/menu/popUpdateMenu")
	public ModelAndView popUpdateMenu(HttpServletRequest request) {
		long menuId = Long.parseLong(request.getParameter("choiceItem"));
		ModelAndView mav = new ModelAndView();
		Map<String, String> errMsg = new HashMap<>();
		
		if (validator.isEmpty(menuId)) {
			errMsg.put("choiceErr", "메뉴를 선택해주세요.");
			mav.addObject("errMsg", errMsg);
			mav.setViewName("menu/menu_list_popup_update");
			return mav;
		}

		Menu menu = ms.findMenuById(menuId);
		mav.addObject("menu", menu);
		mav.setViewName("menu/menu_list_popup_update");
		
		return mav;
	}
	
	/**
	 * 메뉴 타입을 팝업창에 적용 (메뉴 추가)
	 * @param choiceMenu
	 * @return
	 */
	@PostMapping("/menu/popAddMenu")
	public ModelAndView popAddMenu(@RequestParam("choiceMenu") char choiceMenu) {
		ModelAndView mav = new ModelAndView();
		Menu menu = new Menu();
		menu.setMenuType(choiceMenu);
		mav.addObject("menu", menu);
		mav.setViewName("menu/menu_list_popup_add");
		
		return mav;
	}
}
