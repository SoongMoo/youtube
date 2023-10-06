package controller.memberMyPage;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.MemberMyDAO;
import model.dto.AuthInfoDTO;
import model.dto.MemberDTO;

public class MemberInfoUpdateService {
	public int execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		String memberName = request.getParameter("memberName");
		String memberId = request.getParameter("memberId");
		String memberAddr = request.getParameter("memberAddr");
		String memberAddrDetail = request.getParameter("memberAddrDetail");
		String memberPost = request.getParameter("memberPost");
		String memberPhone1 = request.getParameter("memberPhone1");
		String memberPhone2 = request.getParameter("memberPhone2");
		String memberGender = request.getParameter("memberGender");
		String memberBirth = request.getParameter("memberBirth");
		String memberEmail = request.getParameter("memberEmail");
		String memberPw = request.getParameter("memberPw");
		
		HttpSession session = request.getSession();
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		// 넘어온 비밀번호와 로그인 할때 저장된비밀번호가 일치하면 수정
		if (!auth.getUserPw().equals(memberPw)) {
			// 일치하지 않으면 0값 전달
			return 0;
		}else {
			//일치하면 1 값전달
			MemberDTO dto = new MemberDTO();
			dto.setMemberAddr(memberAddr);
			dto.setMemberAddrDetail(memberAddrDetail);
			dto.setMemberEmail(memberEmail);
			dto.setMemberGender(memberGender);
			dto.setMemberId(memberId);
			dto.setMemberName(memberName);
			dto.setMemberPhone1(memberPhone1);
			dto.setMemberPhone2(memberPhone2);
			dto.setMemberPost(memberPost);
			dto.setMemberPw(memberPw);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse(memberBirth);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			dto.setMemberBirth(date);
			MemberMyDAO dao = new MemberMyDAO();
			dao.memberInfoUpdate(dto);
			return 1;
		}
		
	}
}









