package springBootMVCShopping.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginCommand {
	@NotBlank(message = "아이디를 입력해주세요")
	String userId;
	// String에서는 @NotBlank와 @NotEmpty를 사용할 수 있다.
	@NotEmpty(message = "비밀번호를 입력해주세요") 
	@Size(min = 8, max = 20)
	String userPw;
}
