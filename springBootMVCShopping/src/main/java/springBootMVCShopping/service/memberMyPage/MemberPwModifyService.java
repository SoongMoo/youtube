package springBootMVCShopping.service.memberMyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.domain.AuthInfoDTO;

@Service
public class MemberPwModifyService {
	@Autowired
	PasswordEncoder passwordEncoder; //암호화된 비밀번호 비교
	public String execute(HttpSession session, Model model,	String memberPw) {
		// session을 통해 내정보 가져오기 
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		// session에 저장한 내비밀번호와 입력한 비밀번호가 같은지 비교합니다.
		//                         입력한비밀번호 , 암호화된비밀번호
		if(passwordEncoder.matches(memberPw,auth.getUserPw() )) {
			return "thymeleaf/memberShip/myNewPw"; // 맞으면 비밀번호 변경 페이지를
		}else {
			//오류메세지 보내기
			model.addAttribute("errPw", "비밀번호가 틀렸습니다");
			return "thymeleaf/memberShip/myPwCon"; //틀리면 현재 페이지를 
		}
	}
}
