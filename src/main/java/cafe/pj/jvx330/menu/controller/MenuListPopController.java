package cafe.pj.jvx330.menu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.web.command.MenuCommand;

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
	public ModelAndView popUpdateMenu(@RequestParam("choiceItem") long choiceItem) {
		ModelAndView mav = new ModelAndView();
		Map<String, String> errMsg = new HashMap<>();
		
		if (validator.isEmpty(choiceItem)) {
			errMsg.put("choiceErr", "메뉴를 선택해주세요.");
			mav.addObject("errMsg", errMsg);
			mav.setViewName("menu/menu_list_popup_update");
			return mav;
		}

		Menu menu = ms.findMenuById(choiceItem);
		MenuCommand menuCommand = new MenuCommand();
		menuCommand.setId(menu.getId());
		menuCommand.setImgPath(menu.getImgPath());
		menuCommand.setMenuName(menu.getMenuName());
		menuCommand.setMenuPrice(String.valueOf(menu.getMenuPrice()));
		menuCommand.setMenuType(menu.getMenuType());
		menuCommand.setStock(menu.isStock());
		
		mav.addObject("menuCommand", menuCommand);
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
		MenuCommand menuCommand = new MenuCommand();
		menuCommand.setMenuType(choiceMenu);
		mav.addObject("menuCommand", menuCommand);
		mav.setViewName("menu/menu_list_popup_add");
		
		return mav;
	}
}
