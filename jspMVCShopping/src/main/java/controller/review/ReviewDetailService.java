package controller.review;

import javax.servlet.http.HttpServletRequest;

import model.dao.ReviewDAO;
import model.dto.ReviewDTO;

public class ReviewDetailService {
	public void execute(HttpServletRequest request) {
		String purchaseNum = request.getParameter("purchaseNum");
		String goodsNum = request.getParameter("goodsNum");
		ReviewDAO dao = new ReviewDAO();
		ReviewDTO dto = dao.reviewSelectOne(purchaseNum, goodsNum);
		if(dto != null) {
			request.setAttribute("dto", dto);
		}
	}
}
