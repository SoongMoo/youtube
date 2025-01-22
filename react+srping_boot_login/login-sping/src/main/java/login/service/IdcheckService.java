package login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import login.mapper.LoginMapper;

@Service
public class IdcheckService {
	@Autowired
	LoginMapper loginMapper;
	public Integer execute(String userId) {
		System.out.println("userId : " + userId);
		return loginMapper.idCheckSelectOne(userId);
	}
}
