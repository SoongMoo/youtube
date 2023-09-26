package controller.user;

import javax.servlet.http.HttpServletRequest;

import model.dao.UserDAO;
import model.dto.AuthInfoDTO;

public class IDCheckService {
	public void execute(HttpServletRequest request) {
		String userId = request.getParameter("idCheck");
		UserDAO dao = new UserDAO(); 
		AuthInfoDTO dto = dao.loginSelect(userId);
		request.setAttribute("auth", dto);
		request.setAttribute("idCheck", userId);
		// 로그인 정보가 있으면 사용중인 아이디 
		// 로그인 정보가 없으면 사용가능한 아이디
	}
}