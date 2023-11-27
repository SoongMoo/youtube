package controller.item;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberMyDAO;
import model.dto.AuthInfoDTO;
import model.dto.CartListDTO;
import model.dto.MemberDTO;

public class GoodsItemService {
	public void execute(HttpServletRequest request) {
		String goodsNums[] = request.getParameterValues("prodCk");
		// 내정보 가져오기 
		HttpSession session = request.getSession();
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("auth");
		MemberMyDAO myDao = new MemberMyDAO();
		MemberDTO memDto = myDao.memberInfo(authInfo.getUserId());
		
		List<CartListDTO> list = new ArrayList<CartListDTO>();
		ItemDAO dao = new ItemDAO();
		Integer goodsTotalPrice = 0; // 전체 상품 전체 구매 금액
		Integer totalDeliveryCost = 0; // 배송비
		String nums ="";
		for(String goodsNum : goodsNums) {
			CartListDTO dto = dao.selectOne(memDto.getMemberNum(), goodsNum);
			goodsTotalPrice += dto.getTotalPrice();
			totalDeliveryCost += dto.getDeliveryCost();
			nums += goodsNum+"-";
			list.add(dto);
		}
		request.setAttribute("list", list);
		request.setAttribute("goodsTotalPrice", goodsTotalPrice);
		request.setAttribute("goodsNums", nums);
		request.setAttribute("totalDeliveryCost", totalDeliveryCost);
	}
}









