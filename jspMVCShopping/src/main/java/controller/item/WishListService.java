package controller.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;
import model.dto.WishListDTO;

public class WishListService {
	public void execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDAO  dao = new MemberDAO();
		String memberNum = dao.memberNumSelect(auth.getUserId());
		
		ItemDAO idao = new ItemDAO();
		List<WishListDTO> list = idao.wishSelectList(memberNum);	
		request.setAttribute("list", list);
	}
}
