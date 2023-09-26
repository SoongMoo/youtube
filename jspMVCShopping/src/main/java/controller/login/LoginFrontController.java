package controller.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFrontController  extends HttpServlet 
			implements Servlet{
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		if(command.equals("/login.login")) {
			UserLoginService action = new UserLoginService();
			int i = action.execute(request);
			System.out.println(i);
			// 로그인 후 다시 첫 페이지로 
			if(i == 1) {
				response.sendRedirect(request.getContextPath()+ "/");
			}else {
				RequestDispatcher dispatcher =
						request.getRequestDispatcher("main.jsp");
				dispatcher.forward(request, response);
			}
		}else if(command.equals("/logout.login")) {
			// session을 모두 삭제 후 첫 페이지로
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}
}







