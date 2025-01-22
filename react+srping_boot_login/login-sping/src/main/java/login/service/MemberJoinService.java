package login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import login.domain.MemberDTO;
import login.mapper.UserMapper;

@Service
public class MemberJoinService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserMapper userMapper;
	public void execute(MemberDTO dto) {
		dto.setMemberPw(passwordEncoder.encode(dto.getMemberPw()));
		int i = userMapper.userInsert(dto);
	}
}
