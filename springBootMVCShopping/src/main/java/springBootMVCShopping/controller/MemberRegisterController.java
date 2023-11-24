package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootMVCShopping.command.MemberCommand;
import springBootMVCShopping.service.memberRegister.UserWriteService;

@Controller
@RequestMapping("register")
public class MemberRegisterController {
	@Autowired
	UserWriteService userWriteService;
	/* 모든 주소에서 memberCommand를 사용할수 있게 정의 해준다. */
	@ModelAttribute
	public MemberCommand memberCommand() {
		return new MemberCommand();
	}
	
	@RequestMapping(value="userAgree", method = RequestMethod.GET)
	public String agree() {
		return "thymeleaf/memberRegist/userAgree";
	}
	@RequestMapping(value="userWrite", method = RequestMethod.POST)
	public String userWrite( /*userForm.html 페이지를 열때 memberCommand가 필요하다. */
			/* 그러므로 userForm.html가 열리는 곳에서는 모두 memberCommand가 전송되어야 한다. 여기는 없다.*/
			@RequestParam(value="agree", defaultValue = "false") boolean agree) {
		//동의하지 않아도 넘어가는 것 차단.
		if(agree == false) {
			return "thymeleaf/memberRegist/userAgree"; // 동의하지 않았으면 동의페이지가 
		}
		return "thymeleaf/memberRegist/userForm";
	}
	@PostMapping("userRegist")
	public String userRegist(@Validated MemberCommand memberCommand,BindingResult result,
			Model model) {
		// 오류가 있는 경우 오류 메시지가 출력되게 한다.
		if(result.hasErrors()) {
			return "thymeleaf/memberRegist/userForm";
		}
		userWriteService.execute(memberCommand, model);//  정상적으로 저장이 되었다.
		return "thymeleaf/memberRegist/welcome";
	}
}









