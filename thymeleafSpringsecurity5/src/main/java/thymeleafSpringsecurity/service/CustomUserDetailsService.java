package thymeleafSpringsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import thymeleafSpringsecurity.domain.AuthInfo;
import thymeleafSpringsecurity.mapper.LoginMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
    private LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("userId : " + username);
        AuthInfo auth = loginMapper.loginSelectOne(username);

        if (auth == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
        }
        return User.builder()
                .username(auth.getUserid())
                .password(auth.getPassword())
                .roles(auth.getRole())
                .build();
    }
}
