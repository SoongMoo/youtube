package springBootMVCShopping;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SpingBootConfig {
	//  암호화하기 위한 객체생성
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//security에 의한 로그인 화면을 사용하지 않게 설정
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) 
			throws Exception {
		httpSecurity.csrf().disable() //csrf방지
					.formLogin().disable(); 
		return httpSecurity.build();
	}
}
