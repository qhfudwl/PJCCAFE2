package cafe.pj.jvx330.menu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public ModelAndView updateMenu(@ModelAttribute("menuCommand") MenuCommand menuCommand,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
		
		ModelAndView mav = new ModelAndView();
		
		Menu menu = new Menu(menuCommand.getId(), menuCommand.getMenuType(), 
				menuCommand.getMenuName(), Double.parseDouble(menuCommand.getMenuPrice()), menuCommand.isStock(),"");
		
		if (!validator.isEmpty(file.getOriginalFilename())) {
			fileAux.removeImgFile(request, menu, fileAux.getImgName(menuCommand.getImgPath()));
			menu.setImgPath(fileAux.getRelativePath(request, menuCommand.getMenuType(), menuCommand.getMenuName(), file.getOriginalFilename()));
			fileAux.uploadImgFile(request, file, menu);
		} else {
			String imgPath = fileAux.getImgName(menuCommand.getImgPath());
			menu.setImgPath(fileAux.getRelativePath(request, menuCommand.getMenuType(), menuCommand.getMenuName(), imgPath));	
		}
		
		ms.updateMenuById(menu);
		
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
	public ModelAndView addMenu(@ModelAttribute MenuCommand menuCommand, 
			@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {

		ModelAndView mav = new ModelAndView();
		
		Map<String, String> errMsg = new HashMap<>();
		
		if (validator.isEmpty(menuCommand.getMenuName())) { // 이름 미입력
			errMsg.put("menuNameErr", "메뉴 이름을 입력해주세요.");
			mav.addObject("errMsg", errMsg);
			mav.addObject("menuCommand", menuCommand);
			mav.setViewName("menu/menu_list_popup_add");
			return mav;
		} else if (validator.isEmpty(menuCommand.getMenuPrice())) { // 가격 미입력
			errMsg.put("menuPriceErr", "메뉴 가격을 입력해주세요.");
			mav.addObject("errMsg", errMsg);
			mav.addObject("menuCommand", menuCommand);
			mav.setViewName("menu/menu_list_popup_add");
			return mav;
		}
		
		Menu menu = new Menu(menuCommand.getMenuType(),
				menuCommand.getMenuName(), Double.parseDouble(menuCommand.getMenuPrice()), menuCommand.isStock(),
				fileAux.getRelativePath(request, menuCommand.getMenuType(), menuCommand.getMenuName(), menuCommand.getImgPath()));
		
		fileAux.uploadImgFile(request, file, menu);
		
		ms.addMenu(menu);
		
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
		
		ModelAndView mav = new ModelAndView();
		// 리다이렉트 시 메뉴 타입을 get으로 넘겨준다
		mav.setViewName("redirect:/menu/viewMenuList?choiceMenu=" + choiceMenu);
		
		return mav;
	}
}
