package controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberMyDAO;
import model.dto.AuthInfoDTO;
import model.dto.MemberDTO;

public class WishDeleteService {
	public void execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberMyDAO myDao = new MemberMyDAO();
		MemberDTO memdto = myDao.memberInfo(auth.getUserId());
		
		String goodsNum = request.getParameter("goodsNum");
		ItemDAO dao = new ItemDAO();
		dao.wishGoodsDelete(goodsNum, memdto.getMemberNum());
	}
}
