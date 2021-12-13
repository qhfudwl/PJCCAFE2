package cafe.pj.jvx330.sales.controller;

import javax.annotation.Resource;

import cafe.pj.jvx330.sales.service.SalesService;
import cafe.pj.jvx330.web.controller.CafeController;

public class SalesController extends CafeController {
	@Resource(name="salesService")
	SalesService ss;
}
