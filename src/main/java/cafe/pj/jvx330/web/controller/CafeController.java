package cafe.pj.jvx330.web.controller;

import javax.annotation.Resource;

import cafe.pj.jvx330.web.utill.AuxiliaryFunction;
import cafe.pj.jvx330.web.utill.Validator;

public abstract class CafeController {
	@Resource(name="validator")
	protected Validator validator;
	
	@Resource(name="auxiliaryFunction")
	protected AuxiliaryFunction auxFunc;
}
