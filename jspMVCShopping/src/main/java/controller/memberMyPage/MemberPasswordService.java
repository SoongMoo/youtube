package controller.memberMyPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.MemberMyDAO;
import model.dto.AuthInfoDTO;

public class MemberPasswordService {
	public int execute(HttpServletRequest request) {
		String oldPw = request.getParameter("oldPw");
		String newPw = request.getParameter("newPw");
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberId = auth.getUserId();
		if(oldPw.equals(auth.getUserPw())) {
			MemberMyDAO dao = new MemberMyDAO();
			dao.memberPwUpdate(memberId, newPw);
			//변경된비밀번호를 session에 저장
			auth.setUserPw(newPw);
			return 1;
		}else {
			request.setAttribute("errPw", "현재비밀번호가 일치하지 않습니다.");
			return 0;
		}
		
	}
}
