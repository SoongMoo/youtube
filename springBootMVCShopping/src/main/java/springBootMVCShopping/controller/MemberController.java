package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springBootMVCShopping.command.MemberCommand;
import springBootMVCShopping.service.member.MemberInsertService;

@Controller
@RequestMapping("member") //공통주소 처리
public class MemberController {
	@Autowired
	MemberInsertService memberInsertService;
	
	@RequestMapping(value="memberList")
	public String list() {
		return "thymeleaf/member/memberList";
	}
	@RequestMapping(value="memberRegist", method = RequestMethod.GET)
	public String form() {
		return "thymeleaf/member/memberForm";
	}
	@RequestMapping(value="memberRegist", method = RequestMethod.POST)
	public String form(MemberCommand memberCommand) {
		memberInsertService.execute(memberCommand);
		return "redirect:memberList";
	}
	
}
