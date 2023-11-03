package springBootMVCShopping.service.employeeMyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.EmployeeCommand;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.EmployeeDTO;
import springBootMVCShopping.mapper.EmployeeMyMapper;

@Service
public class EmployeeInfoService {
	@Autowired
	EmployeeMyMapper employeeMyMapper;
	public void execute(HttpSession session, Model model) {
		// session을 이용하여 자신의 정보를 가져옵니다.
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("auth");
		String empId = authInfo.getUserId();// 로그인시 저장한 아이디를 불러옴니다.
		// 아이디를 이용해서 자신의 정보를 가져와서 dto에저장하겠습니다.
		EmployeeDTO dto = employeeMyMapper.employeeInfo(empId);
		//  dto에 저장한 값을 command에 다시 저장하겠습니다. 필요에 따라 안해도 되는 작업입니다.
		EmployeeCommand employeeCommand = new EmployeeCommand();
		employeeCommand.setEmpAddr(dto.getEmpAddr());
		employeeCommand.setEmpAddrDetail(dto.getEmpAddrDetail());
		employeeCommand.setEmpEmail(dto.getEmpEmail());
		employeeCommand.setEmpId(dto.getEmpId());
		employeeCommand.setEmpJumin(dto.getEmpJumin());
		employeeCommand.setEmpName(dto.getEmpName());
		employeeCommand.setEmpNum(dto.getEmpNum());
		employeeCommand.setEmpPhone(dto.getEmpPhone());
		employeeCommand.setEmpPost(dto.getEmpPost());
		employeeCommand.setEmpRegiDate(dto.getEmpRegiDate());
		//html에 출력할 수 있게 model에 저장합니다.
		model.addAttribute("employeeCommand", employeeCommand);
	}
}
