package controller.goods;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.EmployeeDAO;
import model.dao.GoodsDAO;
import model.dto.AuthInfoDTO;
import model.dto.GoodsDTO;

public class GoodsUpdateService {
	public void execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String goodsPrice = request.getParameter("goodsPrice");
		String deliveryCost = request.getParameter("deliveryCost");
		
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsContent(request.getParameter("goodsContent"));
		dto.setGoodsName(request.getParameter("goodsName"));
		dto.setGoodsNum(request.getParameter("goodsNum"));
		dto.setGoodsPrice(Integer.parseInt(goodsPrice));
		dto.setDeliveryCost(Integer.parseInt(deliveryCost));
		
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		EmployeeDAO empDao = new EmployeeDAO();
		String empNum = empDao.getEmpNum(auth.getUserId());
		
		dto.setUpdateEmpNum(empNum);
		
		GoodsDAO dao = new GoodsDAO();
		dao.goodsUpdate(dto);
		
	}
}



