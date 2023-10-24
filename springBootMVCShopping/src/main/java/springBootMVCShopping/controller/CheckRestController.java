package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springBootMVCShopping.service.EmailCheckService;

// 같은 방법 다른 형식으로 @ResponseBody를 사용했다면 @RestController를 사용하겠습니다.
@RestController
public class CheckRestController {
	@Autowired
	EmailCheckService emailCheckService;
	@RequestMapping(value="checkRest/userEmailCheck", method = RequestMethod.POST)
	public String userEmailCheck(@RequestParam(value="userEmail") String userEmail) {
		String resultEmail = emailCheckService.execute(userEmail);
		if(resultEmail == null) {
			return "사용가능한 Email입니다.";
		}else {
			return "사용중인 Email입니다.";
		}
	}
}
