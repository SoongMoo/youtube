package controller;

import javax.servlet.http.HttpServletRequest;

import model.BoardDAO;
import model.BoardDTO;

public class BoardUpdateSrvice {
	public void execute(HttpServletRequest request) {
		//  수정코드나 insert 코드는 같다.
		try {		
			request.setCharacterEncoding("utf-8");
		}catch(Exception e) {}
		String boardWriter = request.getParameter("boardWriter");
		String boardSubject = request.getParameter("boardSubject");
		String boardContent = request.getParameter("boardContent");
		String boardNum = request.getParameter("boardNum");
		// request가 받아온 값은 dto에게 저장
		BoardDTO dto = new BoardDTO();
		dto.setBoardContent(boardContent);
		dto.setBoardSubject(boardSubject);
		dto.setBoardWriter(boardWriter);
		//추가
		dto.setBoardNum(Integer.parseInt(boardNum));
		
		BoardDAO dao = new BoardDAO();
		// 수정할 메서드가 필요하다.
		dao.boardUpdate(dto);
	}
}
