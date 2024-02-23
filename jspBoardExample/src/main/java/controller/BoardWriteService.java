package controller;

import javax.servlet.http.HttpServletRequest;

import model.BoardDAO;
import model.BoardDTO;

public class BoardWriteService {
	// 페이지로 부터 전송된 값은  request가 받아온다.
	public void execute(HttpServletRequest request) {
		try {		
			request.setCharacterEncoding("utf-8");
		}catch(Exception e) {}
		String boardWriter = request.getParameter("boardWriter");
		String boardSubject = request.getParameter("boardSubject");
		String boardContent = request.getParameter("boardContent");
		// request가 받아온 값은 dto에게 저장
		BoardDTO dto = new BoardDTO();
		dto.setBoardContent(boardContent);
		dto.setBoardSubject(boardSubject);
		dto.setBoardWriter(boardWriter);
		//dto를 dao에게 전달
		BoardDAO dao = new BoardDAO();
		dao.boardInsert(dto);
	}
}
