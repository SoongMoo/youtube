package login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import login.domain.MemberDTO;
import login.service.MemberJoinService;

@RestController
@RequestMapping("register") //공통주소 처리
public class MemberJoinController {
	@Autowired
	MemberJoinService memberJoinService;
	@PostMapping("userWrite")
	// @Validated:유효성검사
	public String userWrite1(@Validated @RequestBody MemberDTO memberDTO
			,BindingResult result) {
		if(result.hasErrors()) {
			System.out.println(result.getFieldError());
			return "400";
		}
		memberJoinService.execute(memberDTO);
		System.out.println("회원 삽입");
		return "200";
	}
}
