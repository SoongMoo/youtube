package controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;

public class MemberDropService {
	public int execute(HttpServletRequest request) {
		String memberPw = request.getParameter("memberPw");
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(!memberPw.equals(auth.getUserPw())) {
			request.setAttribute("errPw", "비밀번호가 틀렸습니다.");
			return 0;
		}else {
			MemberDAO dao = new MemberDAO();
			dao.memberDrop(auth.getUserId());
			return 1;
		}
		
	}
}
