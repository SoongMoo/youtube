package controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;

public class CartQtyDownService {
	public void execute(HttpServletRequest request) {
		String goodsNum = request.getParameter("goodsNum");
		
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDAO memDao = new MemberDAO();
		String memberNum = memDao.memberNumSelect(auth.getUserId());
		
		ItemDAO dao = new ItemDAO();
		dao.itemQtyDown(goodsNum, memberNum);
	}
}
