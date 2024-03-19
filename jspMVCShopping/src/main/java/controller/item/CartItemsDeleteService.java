package controller.item;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;

public class CartItemsDeleteService {
	public void execute(HttpServletRequest request) {
		String goodsNums = request.getParameter("goodsNums");
		String [] goodsNumbers = goodsNums.split("-");
		
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDAO myDao = new MemberDAO();
		String memberNum = myDao.memberNumSelect(auth.getUserId());
		
		ItemDAO dao = new ItemDAO();
		for(String goodsNum : goodsNumbers) {
			dao.itemDelete(goodsNum, memberNum);
		}
	}
}
