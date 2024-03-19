package controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;

public class MemberPasswordService {
	public int execute(HttpServletRequest request) {
		String oldPw = request.getParameter("oldPw");
		String newPw = request.getParameter("newPw");
		
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(!auth.getUserPw().equals(oldPw)) {
			request.setAttribute("errPw", "비밀번호가 틀렸습니다.");
			return 0;
		}else {
			MemberDAO dao = new MemberDAO(); 
			dao.memberPwUpdate(newPw, auth.getUserId());
			auth.setUserPw(newPw);
			return 1;
		}
	}
}
