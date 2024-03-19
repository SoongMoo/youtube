package controller.inquire;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.InquireDAO;
import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;
import model.dto.InquireDTO;

public class InquireWriteProService {
	public void execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String goodsNum = request.getParameter("goodsNum");
		String queryType = request.getParameter("queryType");
		String inquireSubject = request.getParameter("inquireSubject");
		String inquireContent = request.getParameter("inquireContent");
		
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDAO memDao = new MemberDAO();
		String memberNum = memDao.memberNumSelect(auth.getUserId());
		
		InquireDTO dto =  new InquireDTO();	
		dto.setGoodsNum(goodsNum);
		dto.setInquireContent(inquireContent);
		dto.setInquireKind(queryType);
		dto.setInquireSubject(inquireSubject);
		dto.setMemberNum(memberNum);
		
		InquireDAO dao = new InquireDAO();
		dao.inquireInsert(dto);
	}
}
