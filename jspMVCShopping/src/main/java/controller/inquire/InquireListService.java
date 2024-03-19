package controller.inquire;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.InquireDAO;
import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;
import model.dto.InquireDTO;
import model.dto.MemberDTO;

public class InquireListService {
	public void execute(HttpServletRequest request) {
		String goodsNum = request.getParameter("goodsNum");
		InquireDAO dao = new InquireDAO();
		List<InquireDTO> list = dao.inquireSelectAll(goodsNum);
		request.setAttribute("list", list);
		request.setAttribute("newLine", "\n");
		
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(auth != null) {
			MemberDAO memDao = new MemberDAO();
			String memberNum =  memDao.memberNumSelect(auth.getUserId());
			request.setAttribute("memberNum", memberNum);
		}		
	}
}
