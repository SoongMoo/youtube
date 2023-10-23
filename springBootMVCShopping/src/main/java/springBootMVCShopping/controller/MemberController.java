package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springBootMVCShopping.command.MemberCommand;
import springBootMVCShopping.service.member.MemberAutoNumService;
import springBootMVCShopping.service.member.MemberInsertService;

@Controller
@RequestMapping("member") //공통주소 처리
public class MemberController {
	@Autowired
	MemberInsertService memberInsertService;
	@Autowired
	MemberAutoNumService memberAutoNumService;
	
	@RequestMapping(value="memberList")
	public String list() {
		return "thymeleaf/member/memberList";
	}
	@RequestMapping(value="memberRegist", method = RequestMethod.GET)
	public String form(Model model) {
		// 회원번호를 불러오도록 한다.
		memberAutoNumService.execute(model);
		return "thymeleaf/member/memberForm";
	}
	@RequestMapping(value="memberRegist", method = RequestMethod.POST)
	public String form(@Validated MemberCommand memberCommand, BindingResult result) {
		// 오류가 있으면 오류 메세지를 html에 전달
		if(result.hasErrors()) {
			return "thymeleaf/member/memberForm";
		}
		if(!memberCommand.isMemberPwEqualsMemberPwCon()) {
			//비밀번호와 비밀번호확인이 다른 경우에도 메세지를 보내기
			// result.rejectValue(필드명, 에러코드, 메세지)
			result.rejectValue("memberPwCon", "memberCommand.memberPwCon" 
					, "비밀번호 확인이 틀렸습니다.");
			return "thymeleaf/member/memberForm";
		}else {
			memberInsertService.execute(memberCommand);
			return "redirect:memberList";
		}
	}
	
}
