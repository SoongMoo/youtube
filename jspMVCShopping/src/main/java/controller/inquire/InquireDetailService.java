package controller.inquire;

import javax.servlet.http.HttpServletRequest;

import model.dao.InquireDAO;
import model.dto.InquireDTO;

public class InquireDetailService {
	public void execute(HttpServletRequest request) {
		String inquireNum = request.getParameter("inquireNum");
		InquireDAO dao = new InquireDAO();
		InquireDTO dto = dao.selectOne(inquireNum);
		request.setAttribute("dto", dto);
	}
}
