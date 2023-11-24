package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.EmployeeCommand;
import springBootMVCShopping.service.employeeMyPage.EmployeeInfoService;
import springBootMVCShopping.service.employeeMyPage.EmployeeInfoUpdateService;
import springBootMVCShopping.service.employeeMyPage.EmployeePassConfirmService;

@Controller
@RequestMapping("employee")
public class EmployeeMyPageController {
	@Autowired
	EmployeeInfoService employeeInfoService;
	@Autowired
	EmployeeInfoUpdateService employeeInfoUpdateService;
	@Autowired
	EmployeePassConfirmService employeePassConfirmService;
	@RequestMapping("empMyPage")
	// 자신의 정보를 가져오기 위해선 로그인할 때 만들어진 session정보를 가져와야 합니다.
	public String empMyPage(HttpSession session, Model model) {
		employeeInfoService.execute(session, model);
		// html을 만들어 model에 있는 값을 출력합니다. 
		return "thymeleaf/worker/myInfo";
	}
	@GetMapping("employeeUpdate")
	// 여기서도 자신의 정보를 가져오기 위해선 로그인할 때 만들어진 session정보를 가져와야 합니다.
	public String employeeUpdate(HttpSession session, Model model) {
		// myModify.html에 출력되는 값이 myInfo.html에서 사용하는 값이 같으므로 같은 service를 사용합니다.
		employeeInfoService.execute(session, model);
		return "thymeleaf/worker/myModify";
	}
	@PostMapping("employeeUpdate")
	public String employeeUpdate(@Validated EmployeeCommand employeeCommand
			, BindingResult result, HttpSession session) {
		employeeInfoUpdateService.execute(employeeCommand, session, result);
		if(result.hasErrors()) {
			///수정시 오류가 있으면 현재 페이지로...
			return "thymeleaf/worker/myModify";
		}else {
			// 수정이 완료되면 내정보 페이지로 
			return "redirect:empMyPage";
		}
	}
	@GetMapping("employeePwModify")
	public String employeePwModify() {
		return "thymeleaf/worker/myPwCon";
	}
	@PostMapping("employeePwModify")
	public String employeePwModify(@RequestParam("empPw")String empPw, Model model, HttpSession session) {
		return "thymeleaf/worker/myNewPw";
	}
	@PostMapping("empPwUpdate")
	public @ResponseBody boolean empPwUpdate( //boolean타입으로 보내기 위해서는 ResponseBody를 사용해야 한다.
			@RequestParam("oldPw")String oldPw, @RequestParam("newPw") String newPw,
			HttpSession session) {
		return employeePassConfirmService.execute(newPw, oldPw, session);
	}
}













