package springBootMVCShopping.service.employeeMyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.EmployeeCommand;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.EmployeeDTO;
import springBootMVCShopping.mapper.EmployeeMyMapper;

@Service
public class EmployeeInfoUpdateService {
	// 비밀번호를 비교하게 PasswordEncoder를 가져옴니다.
	@Autowired
	PasswordEncoder passwordEncoder;
	// 디비에 저장
	@Autowired
	EmployeeMyMapper employeeMyMapper;
	public int execute(EmployeeCommand employeeCommand , HttpSession session, BindingResult result) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(! passwordEncoder.matches(employeeCommand.getEmpPw(), auth.getUserPw())) {
			result.rejectValue("empPw", "employeeCommand.empPw", "비밀번호가 틀렸습니다.");
			return 0;
		}else {
			//command로 받아온 값을 dto에 저장
			EmployeeDTO dto = new EmployeeDTO();
			dto.setEmpAddr(employeeCommand.getEmpAddr());
			dto.setEmpAddrDetail(employeeCommand.getEmpAddrDetail());
			dto.setEmpEmail(employeeCommand.getEmpEmail());
			dto.setEmpId(employeeCommand.getEmpId());
			dto.setEmpJumin(employeeCommand.getEmpJumin());
			dto.setEmpName(employeeCommand.getEmpName());
			dto.setEmpNum(employeeCommand.getEmpNum());
			dto.setEmpPhone(employeeCommand.getEmpPhone());
			dto.setEmpPost(employeeCommand.getEmpPost());
			dto.setEmpPw(employeeCommand.getEmpPw());
			dto.setEmpRegiDate(employeeCommand.getEmpRegiDate());
			employeeMyMapper.employeeInfoUpdate(dto);
			return 1;
		}
	}
}
