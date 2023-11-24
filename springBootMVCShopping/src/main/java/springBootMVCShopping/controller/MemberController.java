package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootMVCShopping.command.MemberCommand;
import springBootMVCShopping.service.member.MemberAutoNumService;
import springBootMVCShopping.service.member.MemberDeleteService;
import springBootMVCShopping.service.member.MemberDetailService;
import springBootMVCShopping.service.member.MemberInsertService;
import springBootMVCShopping.service.member.MemberListService;
import springBootMVCShopping.service.member.MemberUpdateService;
import springBootMVCShopping.service.member.MembersDeleteService;

@Controller
@RequestMapping("member") //공통주소 처리
public class MemberController {
	@Autowired
	MemberInsertService memberInsertService;
	@Autowired
	MemberAutoNumService memberAutoNumService;
	@Autowired
	MemberListService memberListService;
	@Autowired
	MembersDeleteService membersDeleteService;
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	MemberDeleteService memberDeleteService;
	@GetMapping("memberdelete/{memberNum}")
	public String memberdelete(
			@PathVariable(value="memberNum") String memberNum) {
		memberDeleteService.execute(memberNum);
		return "redirect:../memberList"; // 주소 전달 방식이라 ../를 해줘야 합니다. 그렇지 않으면
		// 이상 마치겠습니다.
	}
	@PostMapping("memberModify")
	public String memberModify(@Validated  MemberCommand memberCommand, BindingResult result) {
		//html에서 넘오온 값은 MemberCommand가 받는다. 이때 MemberCommand에 넘어오지 않은 경우 오류 검사를 하게 한다.
		if(result.hasErrors()) {
			//오류가 있다면 다시 memberModify페이지가 열리게 한다.
			// memberModify페이지에 MemberCommand가 가진 값을 전달 하게 한다.
			// MemberCommand는 값만 전달하는 것이 아니라 오류 메시지도 전달한다.
			return "thymeleaf/member/memberModify";
		}
		memberUpdateService.execute(memberCommand);
		return "redirect:memberDetail?memberNum="+memberCommand.getMemberNum();
	}
	
	@RequestMapping("memberUpdate")
	public String memberUpdate(
			@RequestParam(value="memberNum") String memberNum, Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberModify";
	}
	
	@GetMapping("memberDetail")
	public String memberDetail(@RequestParam(value="memberNum") String memberNum, 
			Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberInfo";
	}
	
	@PostMapping("membersDelete")
	public String dels(@RequestParam(value = "memDels") String memDels[]) {
		membersDeleteService.execute(memDels);
		return "redirect:memberList";
	}
	
	@RequestMapping(value="memberList")
	public String list(
			// 처음 페이지 열릴 때는 searchWord가 없으므로 페이지 오류가 생긴다 
			// 오류를 방지 하기 위해서 필수가 아니라고 해주어야 한다.
			@RequestParam(value = "searchWord", required = false) String searchWord,
			// 처음 페이지가 열릴 때는 page가 없으므로 page에 기본값 1을 준다.
			@RequestParam(value = "page" ,required = false, defaultValue = "1") int page,
			Model model) {
		//회원들의 정보를 담아 memberList.html에 보낼 수 있게 Model이 필요
		memberListService.execute(model, searchWord, page);
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
