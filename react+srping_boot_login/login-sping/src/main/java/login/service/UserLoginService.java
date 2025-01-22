package login.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import login.JwtUtil;
import login.domain.AuthInfoDTO;
import login.mapper.LoginMapper;

@Service
public class UserLoginService {
	@Autowired
    JwtUtil jwtUtil;
	@Autowired
	LoginMapper loginMapper;
	@Autowired 
	PasswordEncoder passwordEncoder;
	public ResponseEntity<?> execute(AuthInfoDTO authInfoDTO,HttpSession session) {
		AuthInfoDTO auth = loginMapper.loginSelectOne(authInfoDTO.getUserId());
		if(auth != null) {
			System.out.println("아이디가 존재합니다.");
			if(passwordEncoder.matches(authInfoDTO.getUserPw(), auth.getUserPw())) {
				System.out.println("비밀번호가 일치합니다.");
				session.setAttribute("auth", auth);
				String token = jwtUtil.generateToken(auth);
				return ResponseEntity.ok(Collections.singletonMap("token", token));
			}else {
				System.out.println("비밀번호가 일치하지 않는다.");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않는다.");//401코드
			}
		}else {
			System.out.println("아이디가 존재하지 않습니다.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디가 존재하지 않습니다.");
		}
	}
	
}
