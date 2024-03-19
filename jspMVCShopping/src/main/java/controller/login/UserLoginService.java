package controller.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.dto.AuthInfoDTO;

public class UserLoginService {
	public int execute(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");

		UserDAO dao = new UserDAO();
		AuthInfoDTO auth = dao.loginSelect(userId); 		
		HttpSession session = request.getSession();
		int i = 0;
		if(auth == null ) {
			request.setAttribute("errId", "아이디가 존재하지 않습니다.");
		}else {
			if(auth.getUserPw().equals(userPw)) {
				session.setAttribute("auth", auth);
				String storeId = request.getParameter("storeId"); //1
				String keepLogin = request.getParameter("keepLogin");
				if(storeId != null && storeId.equals("store")) {
					//2
					System.out.println(" 쿠키 생성");
					Cookie cookie = new Cookie("storeId", userId);
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					//3
					response.addCookie(cookie);
				}else {
					System.out.println(" 쿠키 생성");
					Cookie cookie = new Cookie("storeId", userId);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					//3
					response.addCookie(cookie);
				}
				if(keepLogin != null && keepLogin.equals("on")) {
					//2
					System.out.println("자동 로그인 쿠키 생성");
					Cookie cookie = new Cookie("keepLogin", userId);
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					//3
					response.addCookie(cookie);
				}
				i = 1;
			}else {
				request.setAttribute("errPw", "비밀번호가 틀렸습니다..");
			}
		}
		return i;
	}
}