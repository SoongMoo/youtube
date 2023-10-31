package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.LoginCommand;
import springBootMVCShopping.service.login.IdcheckService;
import springBootMVCShopping.service.login.UserLoginService;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	IdcheckService idcheckService;
	@Autowired
	UserLoginService userLoginService;
	@PostMapping("userIdCheck")
	// html문서가 아닌 텍스트를 전달하기 위해서는 @ResponseBody이 필요
	public @ResponseBody String userIdCheck(
		@RequestParam(value="userId") String userId) {
		String resultId = idcheckService.execute(userId);
		if(resultId == null) {
			return "사용가능한 아이디입니다.";
		}else {
			return "사용중인 아이디입니다.";
		}
	}
	@PostMapping("login")
	// 아이디와 비밀번호를 command로 받아온다.
	public String login(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session) {
		userLoginService.execute(loginCommand, session, result);
		// 오류가 있으면 index.html페이지 열리게 만들자.
		if(result.hasErrors()) {
			return "thymeleaf/index";
		}
		return "redirect:/";
	}
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 로그아웃시 모든 session삭제
		return "redirect:/"; // 그리고 첫 페이지로
	}
}










