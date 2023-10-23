package springBootMVCShopping.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberCommand {
	// html에 전달할 오류 메세지를 command에서 설정해준다.
	// String자료형에서는 @NotEmpty, @NotBlank를 사용한다.
	String memberNum;
	@NotEmpty(message = "아이디를 입력해주세요")
	String memberId;
	// 비밀번호는 패턴을 사용해야 한다.영문자와 특수문자 그리고 숫자를 사용하고 8글자 이상
	@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#!@$%^&*-+?~]).{8,}$",
			message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String memberPw;
	@NotBlank(message = "비밀번호 확인을 입력해주세요.")
	String memberPwCon;
	String memberName;
	@NotBlank(message = "주소를 입력하여 주세요.")
	String memberAddr;
	String memberAddr2;
	String memberPost;
	String memberGender;
	@NotBlank(message = "연락처을 입력하여 주세요.")
	String memberPhone1;
	String memberPhone2;
	@NotEmpty(message = "이메일을 입력하여 주세요.")
	String memberEmail;
	
	//문자형 날짜를 자동형변환하기 위한 패턴이 필요..	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	// String이 아닌 모든 자료형은 @NotNull을 사용한다.
	@NotNull(message="생년월일을 입력해주세요.")	
	Date memberBirth;
	// 나중에 사용할 멤버필드 추가
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memberRegiDate;
	
	Integer point;
	//비밀번호와 비밀번호확인이 같은지 확인 하기 위한 메서드
	public boolean isMemberPwEqualsMemberPwCon() {
		return memberPw.equals(memberPwCon);
	}
}
