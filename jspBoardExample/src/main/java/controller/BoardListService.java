package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.BoardDAO;
import model.BoardDTO;

public class BoardListService {
	public void execute(HttpServletRequest request) {
		BoardDAO dao = new BoardDAO();
		// list에  dto를 저장한 값을 반환받는다.
		List<BoardDTO> list = dao.boardSelectAll();
		request.setAttribute("list", list);
	}	
}
