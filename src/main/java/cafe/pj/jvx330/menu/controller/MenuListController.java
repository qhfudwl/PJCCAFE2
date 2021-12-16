package cafe.pj.jvx330.menu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.web.command.MenuCommand;

/**
 * 메뉴 목록 화면 컨트롤러
 * @author 김보령
 *
 */
@Controller("menu.controller.menuListController")
public class MenuListController extends MenuController {
	
	/**
	 * 메뉴 목록에서 메뉴 타입 선택 시 오는 화면
	 * 해당 타입에 대한 메뉴 리스트를 뽑아서 화면에 뿌려준다.
	 * @param menuType
	 * @return
	 */
	@GetMapping("/menu/viewMenuList")
	public ModelAndView viewMenuList(@RequestParam("choiceMenu") char choiceMenu,
			HttpSession session, HttpServletRequest request) {
		List<Menu> menus = ms.findAllMenusByMenuType(choiceMenu);
		session.setAttribute("contentName", "메뉴목록");
		ModelAndView mav = new ModelAndView();
		mav.addObject("menus", menus);
		mav.addObject("choiceMenu", choiceMenu);
		mav.setViewName("menu/menu_list");
		return mav;
	}
	
	/**
	 * 기존의 이미지 파일을 삭제하고 새로운 이미지 파일을 업로드
	 * 새로운 메뉴는 추가
	 * @param menuCommand
	 * @param file
	 * @param request
	 * @param removeImgName
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("/menu/updateMenu")
	public ModelAndView updateMenu(@ModelAttribute("menu") MenuCommand menuCommand,
			@RequestParam("file") MultipartFile file, HttpServletRequest request,
			@RequestParam("sendImgPathText") String removeImgName) throws IllegalStateException, IOException {
		
		Menu menu = new Menu(menuCommand.getId(), menuCommand.getMenuType(), 
				menuCommand.getMenuName(), menuCommand.getMenuPrice(), menuCommand.isStock(),
				fileAux.getRelativePath(request, menuCommand.getMenuType(), menuCommand.getMenuName(), menuCommand.getImgPath()));
		
		fileAux.removeImgFile(request, menu, fileAux.getImgName(removeImgName));
		fileAux.uploadImgFile(request, file, menu);
		
		ms.updateMenuById(menu);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("close", "close");
		mav.setViewName("menu/menu_list_popup_update");
		
		return mav;
	}
	
	/**
	 * 메뉴 추가 후 다시 팝업창으로 가기
	 * @param menuCommand
	 * @param file
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("/menu/addMenu")
	public ModelAndView addMenu(@ModelAttribute("menu") MenuCommand menuCommand, 
			@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {

		Menu menu = new Menu(menuCommand.getMenuType(),
				menuCommand.getMenuName(), menuCommand.getMenuPrice(), menuCommand.isStock(),
				fileAux.getRelativePath(request, menuCommand.getMenuType(), menuCommand.getMenuName(), menuCommand.getImgPath()));

		fileAux.uploadImgFile(request, file, menu);
		
		ms.addMenu(menu);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("close", "close");
		mav.setViewName("menu/menu_list_popup_add");
		
		return mav;
	}
	
	/**
	 * 해당 메뉴의 이미지 삭제 후 메뉴 삭제
	 * @param choiceItem
	 * @param request
	 * @param choiceMenu
	 * @return
	 */
	@RequestMapping("/menu/removeMenu")
	public ModelAndView removeMenu(@RequestParam("choiceItem") long choiceItem, HttpServletRequest request,
			@RequestParam("choiceMenu") char choiceMenu) {
		
		Menu menu = ms.findMenuById(choiceItem);
		fileAux.removeImgFile(request, menu, fileAux.getImgName(menu.getImgPath()));
		ms.removeMenuById(menu.getId());
		
		List<Menu> menus = ms.findAllMenusByMenuType(choiceMenu);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("menus", menus);
		mav.addObject("choiceMenu", choiceMenu);
		mav.setViewName("menu/menu_list");
		
		return mav;
	}
}
