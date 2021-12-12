package cafe.pj.jvx330.sales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("web.controller.indexViewController")
public class IndexViewController {
	@GetMapping("/indexView")
	public String indexView() {
		return "index";
	}
}
