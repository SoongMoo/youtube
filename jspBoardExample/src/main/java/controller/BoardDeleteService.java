package controller;

import javax.servlet.http.HttpServletRequest;

import model.BoardDAO;

public class BoardDeleteService {
	public void execute(HttpServletRequest request) {
		String boardNum = request.getParameter("num");
		BoardDAO dao = new BoardDAO();
		dao.boardDelete(boardNum);
	}
}	
