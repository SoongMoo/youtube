package controller.memberMyPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.MemberMyDAO;
import model.dto.AuthInfoDTO;
import model.dto.MemberDTO;

public class MemberInfoService {
	public void execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("auth");
		String memberId = authInfo.getUserId();
		MemberMyDAO dao = new MemberMyDAO();
		MemberDTO dto =  dao.memberInfo(memberId);
		request.setAttribute("dto", dto);
	}
}
