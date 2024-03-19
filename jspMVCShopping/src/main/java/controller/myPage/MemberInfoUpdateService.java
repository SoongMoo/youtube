package controller.myPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;
import model.dto.MemberDTO;

public class MemberInfoUpdateService {
	public int execute(HttpServletRequest request) {
		try {		
			request.setCharacterEncoding("utf-8");
		}catch(Exception e) {}
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		String memberPhone1 = request.getParameter("memberPhone1");
		String memberPhone2 = request.getParameter("memberPhone2");
		String memberAddr = request.getParameter("memberAddr");
		String memberAddrDetail = request.getParameter("memberAddrDetail");
		String memberPost = request.getParameter("memberPost");
		String memberGender = request.getParameter("memberGender");
		String memberEmail = request.getParameter("memberEmail");
		String memberBirth = request.getParameter("memberBirth");
		/// 자바에서 문자열을 날짜로 형변환
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(memberBirth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(!auth.getUserPw().equals(memberPw)) {
			return 0;
		}else {		
			MemberDTO dto = new MemberDTO();
			dto.setMemberAddr(memberAddr);
			dto.setMemberAddrDetail(memberAddrDetail);
			dto.setMemberBirth(date);
			dto.setMemberEmail(memberEmail);
			dto.setMemberGender(memberGender);
			dto.setMemberId(memberId);
			dto.setMemberName(memberName);
			dto.setMemberPhone1(memberPhone1);
			dto.setMemberPhone2(memberPhone2);
			dto.setMemberPost(memberPost);
			dto.setMemberPw(memberPw);
			MemberDAO dao = new MemberDAO();
			dao.memberInfoUpdate(dto);
			return 1;
		}
	}
}
