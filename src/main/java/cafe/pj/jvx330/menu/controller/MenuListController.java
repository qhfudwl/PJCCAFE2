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
		
		// 네비게이션 표시용
		session.setAttribute("contentName", "메뉴목록");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("menus", menus);
		mav.addObject("choiceMenu", choiceMenu);
		
		// 검색어가 있을 경우 넣기
		if(!validator.isEmpty(request.getParameter("searchText"))) {
			String searchText = request.getParameter("searchText");
			mav.addObject("searchText", searchText);
		} else {
			mav.addObject("searchText", "");
		}
		
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
			@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestParam("beforeName") String beforeName) throws IllegalStateException, IOException {
		
		ModelAndView mav = new ModelAndView();
		Map<String, String> errMsg = new HashMap<>();
		
		// 유효성 검사
		if(validator.isEmpty(menuCommand.getMenuName())) { // 이름이 없을 경우
			errMsg.put("menuNameErr", "메뉴 이름을 입력해주세요.");
		} else {
			if(!validator.isImgName(menuCommand.getMenuName()) && menuCommand.getMenuType() != 'F') { // 이름에 핫 / 아이스가 없는 경우
				errMsg.put("menuNameErr", "메뉴 이름 앞에 아이스 혹은 핫을 붙혀주세요.");
			} else if (!beforeName.equals(menuCommand.getMenuName()) && ms.isMenuName(menuCommand.getMenuName())) { // 해당 이름이 있는지 확인
				errMsg.put("menuNameErr", "이미 존재하는 이름입니다.");
			}
		}
		if (validator.isEmpty(menuCommand.getMenuPrice())) { // 가격이 없는 경우
			errMsg.put("menuPriceErr", "메뉴 가격을 입력해주세요.");
		} else {
			if (!validator.isNumber(menuCommand.getMenuPrice())) { // 가격이 숫자가 아닌 경우
				errMsg.put("menuPriceErr", "메뉴 가격은 숫자로 입력해주세요.");
			}
		}
		if (!errMsg.isEmpty()) {
			setModelAndViewM(mav, errMsg, menuCommand, "menu/menu_list_popup_update");
			return mav;
		}
		
		
		// 메뉴 이미지 경로가 어떻게 될 지 모르기때문에 일단 빈 문자열을 적용
		Menu menu = convertMenuCommandToMenu(request, menuCommand, "");
		
		String imgPath = ""; // 이미지 경로
		
		// 만일 파일을 골랐다면
		if (!validator.isEmpty(file.getOriginalFilename())) {
			// 이 전 이미지 경로에 placeholdImg 가 포함되어있을 경우 해당 파일은 지우면 안된다.
			// 그냥 새로운 파일만 업로드 및 적용해야한다.
			if (!menuCommand.getImgPath().contains("placeholdImg")) {
				fileAux.removeImgFile(request, menu.getMenuType(), menu.getMenuName(), fileAux.getImgName(menuCommand.getImgPath()));
			}
			imgPath = file.getOriginalFilename();
			fileAux.uploadImgFile(request, file, menu.getMenuType(), menu.getMenuName());
		} else { // 파일을 고르지 않았다면 이 전 이미지는 삭제하면 안된다.
			imgPath = fileAux.getImgName(menuCommand.getImgPath());	
		}
		// 이미지 경로를 받아서 처리
		menu.setImgPath(fileAux.getRelativePath(request, menuCommand.getMenuType(), menuCommand.getMenuName(), imgPath));
		
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
		
		// 유효성 검사
		if(validator.isEmpty(menuCommand.getMenuName())) { // 이름이 없을 경우
			errMsg.put("menuNameErr", "메뉴 이름을 입력해주세요.");
		} else {
			if(!validator.isImgName(menuCommand.getMenuName()) && menuCommand.getMenuType() != 'F') { // 이름에 핫 / 아이스가 없는 경우
				errMsg.put("menuNameErr", "메뉴 이름 앞에 아이스 혹은 핫을 붙혀주세요.");
			} else if (ms.isMenuName(menuCommand.getMenuName())) { // 해당 이름이 있는지 확인
				errMsg.put("menuNameErr", "이미 존재하는 이름입니다.");
			}
		}
		if (validator.isEmpty(menuCommand.getMenuPrice())) { // 가격이 없는 경우
			errMsg.put("menuPriceErr", "메뉴 가격을 입력해주세요.");
		} else {
			if (!validator.isNumber(menuCommand.getMenuPrice())) { // 가격이 숫자가 아닌 경우
				errMsg.put("menuPriceErr", "메뉴 가격은 숫자로 입력해주세요.");
			}
		}
		
		if (!errMsg.isEmpty()) {
			setModelAndViewM(mav, errMsg, menuCommand, "menu/menu_list_popup_add");
			return mav;
		}
		
		Menu menu = convertMenuCommandToMenu(request, menuCommand, menuCommand.getImgPath());
		
		fileAux.uploadImgFile(request, file, menu.getMenuType(), menu.getMenuName());
		
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
//		fileAux.removeImgFile(request, menu, fileAux.getImgName(menu.getImgPath()));
		ms.removeMenuById(request, menu);
		
		ModelAndView mav = new ModelAndView();
		// 리다이렉트 시 메뉴 타입을 get으로 넘겨준다
		mav.setViewName("redirect:/menu/viewMenuList?choiceMenu=" + choiceMenu);
		
		return mav;
	}
}
