package springBootMVCShopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
	public String login(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session
			, HttpServletResponse response) {
		userLoginService.execute(loginCommand, session, result, response);
		// 오류가 있으면 index.html페이지 열리게 만들자.
		if(result.hasErrors()) {
			return "thymeleaf/index";
		}
		return "redirect:/";
	}
	@GetMapping("logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		//로그아웃에서 해당 쿠키만 삭제합니다.
		Cookie cookie = new Cookie("autoLogin", "");
		cookie.setPath("/");
		cookie.setMaxAge(0); // 해당쿠키 이름에 수명을 0으로 줘서 다시 사용자에게 보냅니다.
		response.addCookie(cookie);	
		session.invalidate(); // 로그아웃시 모든 session삭제
		return "redirect:/"; // 그리고 첫 페이지로
		// 자동로그인은 로그아웃 하기 전 까지는 계속 로그인이 됩니다./
	}
	
	@RequestMapping(value="item.login",method= RequestMethod.GET)
	public String item(LoginCommand loginCommand) { // 여기에 command추가
		return "thymeleaf/login";
	}
	@RequestMapping(value="item.login",method= RequestMethod.POST)
	public String item(@Validated LoginCommand loginCommand,BindingResult result  , //유효성검사를 합니다.
			HttpSession session, HttpServletResponse response) {
		//이전에 사용했던 로그인service를 사용합니다.
		userLoginService.execute(loginCommand, session, result, response);
		if(result.hasErrors()) { 
			// 입력하지 않은 값이 있으면 다시 페이지를 로딩
			return "thymeleaf/login";
		}
		// 정상적으로 로그인 됭었을 때 코드를 작성합니다.
		// 정상적으로 로그인 되었다면 popup창을 닫고 부모창은 새로고침을 하게 합니다.
		// 그러기 위해선 servlet코드를 작성하겠습니다.
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 자바스크립트코드를 작성합니다.
		String str = "<script language='javascript'>"
				   + " opener.location.reload();"
				   + " window.self.close();"
				   + " </script>"; 
		out.print(str);
		out.close();
		return null;
	}
}










