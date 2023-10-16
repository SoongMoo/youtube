package controller.goods;

import javax.servlet.http.HttpServletRequest;

import model.dao.GoodsDAO;
import model.dto.GoodsDTO;

public class GoodsDetailService {
	public void execute(HttpServletRequest request) {
		String goodsNum = request.getParameter("goodsNum");
		GoodsDAO dao = new GoodsDAO();
		dao.visitCount(goodsNum); 
		// 방문자수를 먼저 update 한후에 정보 가져오기
		GoodsDTO dto = dao.selectOne(goodsNum);
		request.setAttribute("dto", dto);
		request.setAttribute("newLine", "\n");
		
	}
}
