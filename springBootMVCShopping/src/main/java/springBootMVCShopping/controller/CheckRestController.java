package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.FileCommand;
import springBootMVCShopping.service.EmailCheckService;
import springBootMVCShopping.service.FileDelService;
import springBootMVCShopping.service.UserEmailCheckService;

// 같은 방법 다른 형식으로 @ResponseBody를 사용했다면 @RestController를 사용하겠습니다.
@RestController
public class CheckRestController {
	@Autowired
	EmailCheckService emailCheckService;
	@Autowired
	UserEmailCheckService userEmailCheckService;
	@RequestMapping(value="checkRest/userEmailCheck", method = RequestMethod.POST)
	public String userEmailCheck(@RequestParam(value="userEmail") String userEmail) {
		String resultEmail = emailCheckService.execute(userEmail);
		if(resultEmail == null) {
			return "사용가능한 Email입니다.";
		}else {
			return "사용중인 Email입니다.";
		}
	}
//@RestAPI를 이용해보자..	
	@RequestMapping("userConfirm")
	public String userConfirm(@RequestParam(value="chk") String chk) {
		int i = userEmailCheckService.execute(chk);;
		if(i == 0) 
			return "이미 인증되었습니다. ";
		else	return "인증되었습니다.";
	}
	// RestAPI를 이용하여 파일 삭제할 정보를 저장합니다/.
	// 먼저 ajax로부터 넘어온 삭제할 파일의 정보를  FileCommand에 저장합니다.
	@Autowired
	FileDelService fileDelService;
	@PostMapping("goods/fileDel")
	public String fileDel(FileCommand fileCommand, HttpSession session) {
		return fileDelService.execute(fileCommand, session);
	}
}










