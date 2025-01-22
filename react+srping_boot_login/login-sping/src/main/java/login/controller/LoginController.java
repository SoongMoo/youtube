package login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import login.domain.AuthInfoDTO;
import login.service.UserLoginService;

@RestController
@RequestMapping("/api")
public class LoginController {
	@Autowired
	UserLoginService userLoginService;
	@PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody AuthInfoDTO auth,BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			System.out.println(auth.getUserId());
			System.out.println("로그인 유효값 오류");
			return ResponseEntity.badRequest().body("로그인 유효값 확인하기");
		}
		return userLoginService.execute(auth, session);
    }
	@PostMapping("/logout")
	public void logout(HttpSession session) {
		System.out.println("로그아웃되었습니다.");
		session.invalidate();
	}
}
