package controller.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberMyDAO;
import model.dto.AuthInfoDTO;
import model.dto.MemberDTO;
import model.dto.WishListDTO;

public class WishListService {
	public void execute(HttpServletRequest request) {
		// 로그인 정보로 회원정보 가져오기
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberMyDAO myDao = new MemberMyDAO();
		MemberDTO memDto = myDao.memberInfo(auth.getUserId());
		
		ItemDAO dao = new ItemDAO();
		List<WishListDTO> list = dao.wishListSelect(memDto.getMemberNum()) ;
		request.setAttribute("dtos", list);
	}
}
