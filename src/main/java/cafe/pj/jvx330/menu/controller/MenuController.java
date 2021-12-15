package cafe.pj.jvx330.menu.controller;

import javax.annotation.Resource;

import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.menu.service.MenuService;
import cafe.pj.jvx330.menu.util.FileAuxiliaryFunction;
import cafe.pj.jvx330.web.command.MenuCommand;
import cafe.pj.jvx330.web.controller.CafeController;

public class MenuController extends CafeController {
	@Resource(name="menuService")
	MenuService ms;
	
	@Resource(name="fileAuxiliaryFunction")
	protected FileAuxiliaryFunction fileAux;
	
	/**
	 * menu 를 menuCommand 로 변환
	 * @param menu
	 * @return
	 */
	public MenuCommand convertMenuToMenuCommand(Menu menu) {
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
	
	
}
