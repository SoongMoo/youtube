package login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import login.domain.UserDTO;
import login.service.EmailCheckService;
import login.service.IdcheckService;


@RestController
public class CheckRestController {
	@Autowired
	EmailCheckService emailCheckService;
	@Autowired
	IdcheckService idcheckService;
	@PostMapping("/checkRest/userEmailCheck")
	public Integer emailCheck(@RequestBody UserDTO userDTO) {
		return emailCheckService.execute(userDTO.getUserEmail());
	}
	@PostMapping("/checkRest/userIdCheck")
	public Integer userIdCheck(@RequestBody UserDTO userDTO) {
		return idcheckService.execute(userDTO.getUserId());	
	}
}
