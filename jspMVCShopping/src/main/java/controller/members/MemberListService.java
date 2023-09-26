package controller.members;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class MemberListService {
	public void execute(HttpServletRequest request) {
		MemberDAO dao = new MemberDAO();
		List<MemberDTO> list = dao.selectAll();
		request.setAttribute("list", list);
	}
}
