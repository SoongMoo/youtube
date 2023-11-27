package controller.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberMyDAO;
import model.dto.AuthInfoDTO;
import model.dto.CartListDTO;
import model.dto.MemberDTO;

public class CartListService {
	public void execute(HttpServletRequest request) {
		// session으로 내정보 가져오기
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberMyDAO memDao = new MemberMyDAO();
		MemberDTO mem = memDao.memberInfo(auth.getUserId());
		
		ItemDAO itemDao = new ItemDAO();
		List<CartListDTO> list = itemDao.cartList(mem.getMemberNum());
		
		Integer totPri = 0; // 전체 합계 금액
		Integer totQtyt = 0; // 상품 수량
		for (CartListDTO dto : list)  {
			totPri += dto.getTotalPrice();
			totQtyt += dto.getCartQty();
		}
		request.setAttribute("dtos", list);
		request.setAttribute("totPri", totPri);
		request.setAttribute("totQtyt", totQtyt);
	}
}
