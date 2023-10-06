package controller.help;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import model.dao.FindDAO;
import model.dto.AuthInfoDTO;

public class FindPwService {
	public void execute(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String userPhone = request.getParameter("userPhone");
		FindDAO dao = new FindDAO();
		AuthInfoDTO dto = dao.userEmail(userId, userPhone);
		if (dto != null) {// null이 아니라는 것은 사용자가 있다는 의미
			//사용자가 있을때만 8자리 비밀번호를 UUID로 랜덤값으로 받아온다. 
			String newPw = UUID.randomUUID().toString().substring(0, 8);
			dto.setUserId(userId);
			dto.setUserPw(newPw);
			// 바뀐 비밀번호를 디비에 update
			dao.pwUpdate(dto);
			request.setAttribute("dto", dto);
		}
	}
}
