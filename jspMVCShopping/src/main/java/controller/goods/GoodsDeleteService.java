package controller.goods;

import javax.servlet.http.HttpServletRequest;

import model.dao.GoodsDAO;

public class GoodsDeleteService {
	public void execute(HttpServletRequest request) {
		String goodsNum = request.getParameter("goodsNum");
		GoodsDAO dao = new GoodsDAO();
		dao.goodsDelete(goodsNum);
	}
}