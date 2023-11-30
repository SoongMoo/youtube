package controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberMyDAO;
import model.dto.AuthInfoDTO;
import model.dto.MemberDTO;

public class CartItemDeleteService {
	public void execute(HttpServletRequest request) {
		String goodsNum = request.getParameter("goodsNum");
		// 누구의 장바구니인지 알기 위해서는 로그인 정보를 가져와서 id로 memberNum을 가져온다. 
		HttpSession session = request.getSession();
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("auth");
		MemberMyDAO myDao = new MemberMyDAO();
		MemberDTO memDto = myDao.memberInfo(authInfo.getUserId());
		
		ItemDAO dao = new ItemDAO();
		dao.itemDelete(memDto.getMemberNum(), goodsNum);
	}
}
