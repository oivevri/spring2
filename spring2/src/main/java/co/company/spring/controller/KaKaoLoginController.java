package co.company.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KaKaoLoginController {
	@RequestMapping("/loginCallback")
	public String callback() {
		return "";
	}
}
