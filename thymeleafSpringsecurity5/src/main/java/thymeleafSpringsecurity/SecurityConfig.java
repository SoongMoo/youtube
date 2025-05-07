package thymeleafSpringsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import thymeleafSpringsecurity.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http
	     	// 접근 권한 설정: "/"와 "/login"은 모두 접근 허용, 나머지 요청은 인증 요구
	        .authorizeHttpRequests()
	        .requestMatchers("/", "/login").permitAll()
	        .anyRequest().authenticated()
	        .and() 
	        // 로그인 설정
	        .formLogin()
	        .loginPage("/login")       // 커스텀 로그인 페이지 URL
	        .defaultSuccessUrl("/", true)  // 로그인 성공 시 이동할 페이지
	        .permitAll()
	        .and() 
            // 로그아웃 설정
	        .logout()
	        .logoutSuccessUrl("/login?logout")  // 로그아웃 성공 시 이동할 URL
	        .permitAll();

		 return http.build();
	 }
	 @Bean
	 public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
	 }
	 @Autowired
	 CustomUserDetailsService customUserDetailsService;
	 @Bean
	 public AuthenticationManager authenticationManager(HttpSecurity http,
	                           PasswordEncoder passwordEncoder) throws Exception {
		 return http
				 	.getSharedObject(AuthenticationManagerBuilder.class)
				 	.userDetailsService(customUserDetailsService)
				 	.passwordEncoder(passwordEncoder)
				 	.and()
				 	.build();
	 }
}
