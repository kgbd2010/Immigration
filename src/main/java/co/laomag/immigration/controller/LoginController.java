package co.laomag.immigration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 项目代码 控制器
 */
@Controller
public class LoginController {
	
	@GetMapping({"","/main"})
	public String main() {
		return "main";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
}
