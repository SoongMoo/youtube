package controller.members;

import javax.servlet.http.HttpServletRequest;

import model.dao.MemberDAO;

public class MemberAutoNumService {
	public void execute(HttpServletRequest request) {
		MemberDAO dao = new MemberDAO();
		String memberNum = dao.memberAutoNum();
		request.setAttribute("memberNum", memberNum);
	}
}
