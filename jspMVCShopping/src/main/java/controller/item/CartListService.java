package controller.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;
import model.dto.CartListDTO;

public class CartListService {
	public void execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDAO memDao = new MemberDAO();
		String memberNum = memDao.memberNumSelect(auth.getUserId());
		
		ItemDAO dao = new ItemDAO();
		List<CartListDTO> list = dao.cartSelectList(memberNum);
		
		Integer totPri = 0;
		Integer totQty = 0;
		for (CartListDTO dto : list) {
			totPri = totPri + dto.getTotalPrice();
			totQty = totQty + dto.getCartQty();
		}
		request.setAttribute("dtos", list);
		request.setAttribute("totQty", totQty);
		request.setAttribute("totPri", totPri);
	}
}
