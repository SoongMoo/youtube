package springBootMVCShopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootMVCShopping.command.LoginCommand;

@Controller
public class MainController {
	//LoginCommand loginCommand만 사용해도 되나 
	//@ModelAttribute("loginCommand")와 같이 사용하는 방법도 있다 둘중에 하나.
	//자주 사용하는 방법이 아니므로 여기서는 @ModelAttribute("loginCommand") LoginCommand loginCommand
	//사용하겠습니다.
	@RequestMapping("/")
	// 첫 페이지에 th:object="${loginCommand}"로 인해 발생하는 오류가 발생하지 하기 위해 
	// command객체 생성
	public String index( /*LoginCommand loginCommand */
			@ModelAttribute("loginCommand") LoginCommand loginCommand) {
		return "thymeleaf/index";
	}
}