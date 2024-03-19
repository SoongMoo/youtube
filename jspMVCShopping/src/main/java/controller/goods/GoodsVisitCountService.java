package controller.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.GoodsDAO;

public class GoodsVisitCountService {
	public void execute(HttpServletRequest request) {
		String goodsNum = request.getParameter("goodsNum");
		//HttpSession session = request.getSession();
		GoodsDAO dao = new GoodsDAO();
		//if(session.isNew()) {
			dao.visitCount(goodsNum);
		//}
	}
}
