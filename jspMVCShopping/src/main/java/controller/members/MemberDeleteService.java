package controller.members;

import javax.servlet.http.HttpServletRequest;

import model.dao.MemberDAO;

public class MemberDeleteService {
	public void execte(HttpServletRequest request) {
		String memberNum = request.getParameter("memberNum");
		MemberDAO dao = new MemberDAO();
		dao.memberDelete(memberNum);
	}
}
