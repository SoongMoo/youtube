package login.domain;

import org.apache.ibatis.type.Alias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Alias("auth")
public class AuthInfoDTO {
	@NotBlank(message = "아이디를 입력해주세요")
	String userId; 
	@NotEmpty(message = "비밀번호를 입력해주세요") 
	@Size(min = 8, max = 20)
	String userPw;
	String userName;
	String userEmail;
	String grade;
}
