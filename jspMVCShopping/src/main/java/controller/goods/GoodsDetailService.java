package controller.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.GoodsDAO;
import model.dao.ItemDAO;
import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;
import model.dto.GoodsDTO;

public class GoodsDetailService {
	public void execute(HttpServletRequest request) {
		String goodsNum = request.getParameter("goodsNum");
		GoodsDAO dao = new GoodsDAO();
		GoodsDTO dto = dao.goodsSelectOne(goodsNum);
		
		request.setAttribute("dto", dto);
		request.setAttribute("newLine", "\n");
		
		// 제품 페이지에서 관심상품 등록되었는지 확인
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		
		if(auth != null) {
			MemberDAO memDao = new MemberDAO();
			String memberNum =  memDao.memberNumSelect(auth.getUserId());
			ItemDAO itemDao = new ItemDAO();
			int i = itemDao.wishSelectOne(memberNum,goodsNum);
			if(i == 1) {
				request.setAttribute("isTrue", i);
			}
		}
		
	}
}
