package cafe.pj.jvx330.menu.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.menu.service.MenuService;
import cafe.pj.jvx330.menu.util.FileAuxiliaryFunction;
import cafe.pj.jvx330.web.command.MenuCommand;
import cafe.pj.jvx330.web.util.Validator;

public class MenuController {
	@Resource(name="validator")
	protected Validator validator;
	
	@Resource(name="menuService")
	MenuService ms;
	
	@Resource(name="fileAuxiliaryFunction")
	protected FileAuxiliaryFunction fileAux;
	
	/**
	 * menu 를 menuCommand 로 변환
	 * @param menu
	 * @return
	 */
	protected MenuCommand convertMenuToMenuCommand(Menu menu) {
		MenuCommand menuCommand = new MenuCommand();
		
		long id = menu.getId();
		char menuType = menu.getMenuType();
		String menuName = menu.getMenuName();
		String menuPrice = String.valueOf(menu.getMenuPrice());
		boolean stock = menu.isStock();
		String imgPath = menu.getImgPath();
		
		if (!validator.isEmpty(id)) {
			menuCommand.setId(id);
		}
		if (!validator.isEmpty(menuType)) {
			menuCommand.setMenuType(menuType);
		}
		if (!validator.isEmpty(menuName)) {
			menuCommand.setMenuName(menuName);
		}
		if (!validator.isEmpty(menuPrice)) {
			menuCommand.setMenuPrice(menuPrice);
		}
		menuCommand.setStock(stock);
		if (!validator.isEmpty(imgPath)) {
			menuCommand.setImgPath(imgPath);
		}
		
		return menuCommand;
	}
	
	/**
	 * menuCommand를 menu 객체로
	 * @param request
	 * @param menuCommand
	 * @param imgPath
	 * @return
	 */
	protected Menu convertMenuCommandToMenu(HttpServletRequest request, MenuCommand menuCommand, String imgPath) {
		Menu menu = new Menu();
		
		long id = menuCommand.getId();
		char menuType = menuCommand.getMenuType();
		String menuName = menuCommand.getMenuName();
		double menuPrice = Double.parseDouble(menuCommand.getMenuPrice());
		boolean stock = menuCommand.isStock();
		
		if (!validator.isEmpty(id)) {
			menu.setId(id);
		}
		if (!validator.isEmpty(menuType)) {
			menu.setMenuType(menuType);
		}
		if (!validator.isEmpty(menuName)) {
			menu.setMenuName(menuName);
		}
		if (!validator.isEmpty(menuPrice)) {
			menu.setMenuPrice(menuPrice);
		}
		menu.setStock(stock);
		if (validator.isEmpty(imgPath)) {
			imgPath = "placeholdImg.png";
		}
		menu.setImgPath(fileAux.getRelativePath(request, menuType, menuName, imgPath));
		
		return menu;
	}
	
	/**
	 * 유효성 검사 중 model and view 세팅해주기
	 * @param mav
	 * @param errMsg
	 * @param mc
	 * @param path
	 * @return
	 */
	protected ModelAndView setModelAndViewM(ModelAndView mav, Map<String, String> errMsg, MenuCommand mc, String path) {
		
		mav.addObject("errMsg", errMsg);
		mav.addObject("menuCommand", mc);
		mav.setViewName(path);
		
		return mav;
	}
}
