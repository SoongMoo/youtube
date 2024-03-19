package controller.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;
import model.dto.PurchaseInfoDTO;

public class PurchaseListService {
	public void execute(HttpServletRequest request) {
		String memberNum=null;
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(auth.getGrade().equals("mem")) {
			MemberDAO memDao = new MemberDAO();
			memberNum = memDao.memberNumSelect(auth.getUserId());
		}
		ItemDAO dao = new ItemDAO();
		List<PurchaseInfoDTO> list =  dao.purchaseItemSelect(memberNum);
		request.setAttribute("list", list);
	}
}
