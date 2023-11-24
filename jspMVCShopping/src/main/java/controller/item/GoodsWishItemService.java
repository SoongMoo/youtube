package controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberMyDAO;
import model.dto.AuthInfoDTO;
import model.dto.MemberDTO;

public class GoodsWishItemService {
	public void execute(HttpServletRequest request) {
		String goodsNum = request.getParameter("goodsNum");
		
		HttpSession session = request.getSession();
		AuthInfoDTO dto = (AuthInfoDTO)session.getAttribute("auth");
		
		MemberMyDAO myDao = new MemberMyDAO();
		MemberDTO memDto = myDao.memberInfo(dto.getUserId());
		
		ItemDAO dao = new ItemDAO();
		int i = dao.goodsSelect(goodsNum, memDto.getMemberNum()); // 먼저 관심 상품이 등록 되었는지 확인
		if(i == 1) { // 관심상품이 등록 되어 있다면 삭제
			dao.wishGoodsDelete(goodsNum, memDto.getMemberNum());
		}else { // 관심상품이 등록 되어 있지 않다면 삭제
			dao.wishGoodsInsert(goodsNum, memDto.getMemberNum());
		}
		request.setAttribute("isTrue", i);
	}
}
