package springBootMVCShopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.LoginCommand;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.mapper.UserMapper;

@Service
public class CookiesService {
	@Autowired
	UserMapper userMapper;
	public void execute(HttpServletRequest request, Model model) {
		// request가 웹브라우져에 있는 쿠키를 모두 가지고 옵니다. 모든 쿠키를 쿠키 배열로 받습니다.
		Cookie [] cookies = request.getCookies();
		// 그전에 쿠키를 가져왔는지 부터 검사하겠습니다.
		if(cookies != null && cookies.length > 0) {
			//내가원하는 쿠키가 있는지 확인합니다.
			for(Cookie cookie : cookies) {
				//idStore라는 이름의 쿠키가 있는지 확인 하겠습니다.
				if(cookie.getName().equals("idStore")) {
					System.out.println("쿠키가 있나요???");
					// loginCommand에 저장해서 index.html에 아이디등을 전달합니다.
					LoginCommand loginCommand = new LoginCommand();
					loginCommand.setUserId(cookie.getValue());
					loginCommand.setIdStore(true);
					model.addAttribute("loginCommand", loginCommand);
					// index.html에서 받도록해주세요..
				}// 메인에서 있는지 확인해야 겠죠
				if(cookie.getName().equals("autoLogin")) {
					// 있으면 로그인 정보를 가지고와서 session에 저장합니다.
					AuthInfoDTO auth = userMapper.loginSelect(cookie.getValue());
					HttpSession session = request.getSession();
					session.setAttribute("auth", auth);
					// 자동로그인을 하면 로그아웃해도 main에서 계속 seesion을 만들어서 로그아웃이 되지 않습니다.
				}
			}
		}
		
	}
}
