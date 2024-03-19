package controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;

public class WishDeleteService {
	public void execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDAO dao = new MemberDAO();
		String memberNum = dao.memberNumSelect(auth.getUserId());
		
		String goodsNum = request.getParameter("goodsNum");
		
		ItemDAO idao = new ItemDAO();
		idao.wishGoodsDelete(memberNum, goodsNum);
	}
}
