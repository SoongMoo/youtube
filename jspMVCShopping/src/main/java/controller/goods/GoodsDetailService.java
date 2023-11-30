package controller.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.GoodsDAO;
import model.dao.ItemDAO;
import model.dao.MemberMyDAO;
import model.dto.AuthInfoDTO;
import model.dto.GoodsDTO;
import model.dto.MemberDTO;

public class GoodsDetailService {
	public void execute(HttpServletRequest request) {
		String goodsNum = request.getParameter("goodsNum");
		GoodsDAO dao = new GoodsDAO();
		dao.visitCount(goodsNum); 
		// 방문자수를 먼저 update 한후에 정보 가져오기
		GoodsDTO dto = dao.selectOne(goodsNum);
		request.setAttribute("dto", dto);
		request.setAttribute("newLine", "\n");
		
		
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		
		int i = 0; // 상세페이지로 들어 왔을 때 로그인 되어 있는 지 확인하고 로그인 되어 있으면 해당 상품의 관심상품인지 확인
		if (auth != null) {
			MemberMyDAO memDao = new MemberMyDAO();
			MemberDTO memDTO = memDao.memberInfo(auth.getUserId());
			
			ItemDAO itemDao = new ItemDAO();
			i = itemDao.goodsSelect(goodsNum, memDTO.getMemberNum());
		}
		request.setAttribute("isTrue", i);
	}
}
