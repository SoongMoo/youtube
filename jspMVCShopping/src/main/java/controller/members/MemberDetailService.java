package controller.members;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;
import model.dto.MemberDTO;

public class MemberDetailService {
	public void execute(HttpServletRequest request) {
		String memberNum = request.getParameter("memberNum");
		MemberDAO dao = new MemberDAO();
		if(memberNum == null) {
			HttpSession session = request.getSession();
			AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
			memberNum = dao.memberNumSelect(auth.getUserId());
		}
		MemberDTO dto = dao.memberSelectOne(memberNum);
		request.setAttribute("dto", dto);
	}
}
