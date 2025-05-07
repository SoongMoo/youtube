package thymeleafSpringsecurity.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("user")
public class AuthInfo {
	private String userid;
	private String password;
	private String role;
}
