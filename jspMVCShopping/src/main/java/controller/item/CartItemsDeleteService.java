package controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberMyDAO;
import model.dto.AuthInfoDTO;
import model.dto.MemberDTO;

public class CartItemsDeleteService {
	public void execute(HttpServletRequest request) {
		String goodsNumbers = request.getParameter("goodsNums");
		String goodsNums [] = goodsNumbers.split("-"); //"-"로 묶은 상품명을 분리한다,'
		// 내 정보 가져오기
		HttpSession session = request.getSession();
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("auth");
		MemberMyDAO myDao = new MemberMyDAO();
		MemberDTO memDto = myDao.memberInfo(authInfo.getUserId());
		
		ItemDAO dao = new ItemDAO();
		for(String goodsNum : goodsNums) { // 배열을 반복해서 삭제한다.
			dao.itemDelete(memDto.getMemberNum(), goodsNum);
		}
	}
}
