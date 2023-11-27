package controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberMyDAO;
import model.dto.AuthInfoDTO;
import model.dto.CartDTO;
import model.dto.MemberDTO;

public class CartInsertService {
	public void execute(HttpServletRequest request) {
		 String goodsNum = request.getParameter("goodsNum");
		 String qty = request.getParameter("cartQty");
		 
		 HttpSession session = request.getSession();
		 AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		 MemberMyDAO myDao = new MemberMyDAO(); // id로 내정보 가져오기
		 MemberDTO memDto = myDao.memberInfo(auth.getUserId());
		 
		 CartDTO dto = new CartDTO();
		 dto.setCartQty(Integer.parseInt(qty));
		 dto.setGoodsNum(goodsNum);
		 dto.setMemberNum(memDto.getMemberNum());
		 ItemDAO dao = new ItemDAO();
		 dao.cartInsert(dto);
	}
}
