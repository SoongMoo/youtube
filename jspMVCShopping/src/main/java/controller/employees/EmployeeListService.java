package controller.employees;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.EmployeeDAO;
import model.dto.EmployeeDTO;

public class EmployeeListService {
	public void execute(HttpServletRequest request) {
		EmployeeDAO dao = new EmployeeDAO();
		List<EmployeeDTO> list = dao.selectAll();
		request.setAttribute("dtos", list);
	}
}
