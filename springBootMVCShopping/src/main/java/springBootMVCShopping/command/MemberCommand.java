package springBootMVCShopping.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberCommand {
	String memberNum;
	String memberId;
	String memberPw;
	String memberPwCon;
	String memberName;
	String memberAddr;
	String memberAddr2;
	String memberPost;
	String memberGender;
	String memberPhone1;
	String memberPhone2;
	String memberEmail;
	//문자형 날짜를 자동형변환하기 위한 패턴이 필요..
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memberBirth;
}
