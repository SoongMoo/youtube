package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet implements Servlet{
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI(); 
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		if(command.equals("/boardList.naver")) {
			BoardListService action = new BoardListService();
			action.execute(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/board/boardList.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/boardWrite.naver")) {
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/board/boardForm.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/boardDetail.naver")) {
			// 글 내용을 가져오기 위해서는 객체가 필요하다. 
			BoardDetailService action = new BoardDetailService();
			action.execute(request); // 디비에서 가져온 데이터를 request에 저장한다.
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/board/boardInfo.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/boardUpdate.naver")) {
			// 상세페이지나 수정페이지 내용이 같으므로 같은 객체를 사용한다.
			BoardDetailService action = new BoardDetailService();
			action.execute(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/board/boardModify.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/boardDelete.naver")) {
			// 삭제하기 위한 객체 필요
			BoardDeleteService action = new BoardDeleteService();
			action.execute(request);
			// 삭제 후 리스트로....
			response.sendRedirect("boardList.naver");
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// post 방식으로 전송된 데이터는  do Post에서 받는다.
		String requestURI = request.getRequestURI(); 
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		if(command.equals("/boardWrite.naver")) {
			// 전송된 값을 디비에 저장하기 위해 객체를 만들어 주어야 한다.
			BoardWriteService action = new BoardWriteService();
			action.execute(request);//  페이지에서 전달된갑ㅅ을 request로 넘겨준다. 
			response.sendRedirect("boardList.naver");
		}else if(command.equals("/boardUpdate.naver")) {
			// 수정하기 위해서는 객체가 있어야 한다.
			BoardUpdateSrvice action = new BoardUpdateSrvice();
			action.execute(request);
			// 수정한 후 상세 페이지로 이동
			response.sendRedirect("boardDetail.naver?num="+ request.getParameter("boardNum"));
		}
	}
}
