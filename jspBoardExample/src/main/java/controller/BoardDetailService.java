package controller;

import javax.servlet.http.HttpServletRequest;

import model.BoardDAO;
import model.BoardDTO;

public class BoardDetailService {
	 public void execute(HttpServletRequest request) {
		 String boardNum = request.getParameter("num");
		 BoardDAO dao = new BoardDAO();
		 // 행을 dto 에 저장해서 가져오므로  dto로 받아야 한다.
		 BoardDTO dto = dao.boardSelectOne(boardNum);
		 request.setAttribute("dto", dto);
	 }
}
