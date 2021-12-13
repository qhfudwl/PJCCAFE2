package cafe.pj.jvx330.menu.controller;

import javax.annotation.Resource;

import cafe.pj.jvx330.menu.service.MenuService;
import cafe.pj.jvx330.menu.utill.FileAuxiliaryFunction;
import cafe.pj.jvx330.web.controller.CafeController;

public class MenuController extends CafeController {
	@Resource(name="menuService")
	MenuService ms;
	
	@Resource(name="fileAuxiliaryFunction")
	protected FileAuxiliaryFunction fileAux;
}
