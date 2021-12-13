package cafe.pj.jvx330.web.controller;

import javax.annotation.Resource;

import cafe.pj.jvx330.menu.util.FileAuxiliaryFunction;
import cafe.pj.jvx330.web.util.Validator;

public abstract class CafeController {
	@Resource(name="validator")
	protected Validator validator;
}
