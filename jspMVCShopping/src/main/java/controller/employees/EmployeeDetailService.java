package controller.employees;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.EmployeeDAO;
import model.dto.AuthInfoDTO;
import model.dto.EmployeeDTO;

public class EmployeeDetailService {
	public void execute(HttpServletRequest request) {
		String empNum = request.getParameter("num");
		EmployeeDAO dao = new EmployeeDAO();
		if(empNum == null) {
			HttpSession session = request.getSession();
			AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
			empNum = dao.getEmpNum(auth.getUserId()); 			
		}
		EmployeeDTO dto = dao.selectOne(empNum);
		request.setAttribute("dto", dto);
	}
}
