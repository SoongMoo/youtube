package controller.item;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;
import model.dto.CartListDTO;

public class GoodsItemService {
	public void execute(HttpServletRequest request) {
		String goodsNums[] = request.getParameterValues("prodCk");
		
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDAO myDao = new MemberDAO();
		String memberNum = myDao.memberNumSelect(auth.getUserId());
		
		List<CartListDTO> list = new ArrayList<CartListDTO>();
		ItemDAO dao = new ItemDAO();
		Integer goodsTotalPrice = 0;
		Integer totalDeliveryCost = 0;
		String nums ="";
		for(String goodsNum : goodsNums) {
			CartListDTO dto = dao.ItemSelectOne(goodsNum, memberNum);
			goodsTotalPrice += dto.getTotalPrice();
			totalDeliveryCost += dto.getDeliveryCost();
			nums += dto.getGoodsNum() + "/";			
			list.add(dto);
		}
		request.setAttribute("list", list);
		request.setAttribute("goodsTotalPrice", goodsTotalPrice);
		request.setAttribute("goodsNums", nums);
		request.setAttribute("totalDeliveryCost", totalDeliveryCost);
	}
}
