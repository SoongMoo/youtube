package springBootMVCShopping.service.memberMyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.mapper.MemberMyMapper;

@Service
public class MyPassConfirmService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberMyMapper memberMyMapper;
	public boolean execute(String newPw,String oldPw, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(passwordEncoder.matches(oldPw, auth.getUserPw())) {
			String  userPw = passwordEncoder.encode(newPw);// 새 비밀번호 암호화하기
			// 암호화된 비밀번호를 디비에저장
			memberMyMapper.memberPwUpdate(userPw, auth.getUserId());
			// 새 비밀번호를 session에 저장
			auth.setUserPw(userPw);
			return true;
		}else return false;
	}
}
