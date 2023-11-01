package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.MemberCommand;
import springBootMVCShopping.service.memberMyPage.MemberDropService;
import springBootMVCShopping.service.memberMyPage.MemberInfoService;
import springBootMVCShopping.service.memberMyPage.MemberInfoUpdateService;
import springBootMVCShopping.service.memberMyPage.MemberPwModifyService;
import springBootMVCShopping.service.memberMyPage.MyPassConfirmService;

@Controller
@RequestMapping("mypage")
public class MemberMyPageController {
	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	MemberPwModifyService memberPwModifyService;
	@Autowired
	MyPassConfirmService myPassConfirmService;
	@Autowired
	MemberDropService memberDropService;
	@GetMapping("myDetail")
	//로그인 할때 저장한 session을 가져와서 내정보를 디비에서 가져오도록 해봅니다.
	// 디비로 가져온 정보를 model을 이용해서 myInfo.html에 보내도록한다.
	public String myDetail(HttpSession session,Model model) {
		memberInfoService.execute(session, model);
		return "thymeleaf/memberShip/myInfo";
	}
	@RequestMapping(value="userPwModify", method = RequestMethod.GET)
	public String userPwModify() {
		return "thymeleaf/memberShip/myPwCon";
	}
	@RequestMapping(value="memberPwModify", method = RequestMethod.POST )
	public String userPwModify(@RequestParam("memberPw") String memberPw,
			Model model,HttpSession session) {
		return memberPwModifyService.execute(session, model, memberPw);
	}
	@PostMapping("myPwUpdate")
	@ResponseBody // html이 아닌 값을 전달 할 때 사용 RestController와 같은 역할
	public  boolean myPwUpdate(@RequestParam("oldPw") String oldPw,
			@RequestParam(value="newPw") String newPw,
			HttpSession session) {
		return myPassConfirmService.execute(newPw, oldPw, session);
	}
	@GetMapping("memberDrop")
	public String memberDrop() {
		return "thymeleaf/memberShip/memberDrop";
	}
	@PostMapping("memberDropOk")
	public String memberDrop(
			@RequestParam("memberPw") String memberPw, Model model,
			HttpSession session) {
		int i = memberDropService.execute(memberPw, session, model);
		if(i == 1)
			return "redirect:/login/logout"; //탈퇴하면 로그아웃하기
		else return "thymeleaf/memberShip/memberDrop"; // 현재 비밀번호가 틀리면 현재 페이지
	}
	@RequestMapping(value="memberUpdate", method =RequestMethod.GET)
	public String memberUpdate(HttpSession session, Model model) {
		memberInfoService.execute(session, model); //myModify에 내정보를 가지고 오기 위해서 myDetail에서 사용한 service를 쓴다.
		return "thymeleaf/memberShip/myModify";
	}
	
	@Autowired
	MemberInfoUpdateService memberInfoUpdateService;
	@RequestMapping(value="memberUpdate", method = RequestMethod.POST )
	public String memberUpdate(@Validated MemberCommand memberCommand, BindingResult result,
			HttpSession session) {
		memberInfoUpdateService.execute(memberCommand, session, result);
		if(result.hasErrors()) {
			return "thymeleaf/memberShip/myModify";
		}
		return "redirect:myDetail";
	}
}











