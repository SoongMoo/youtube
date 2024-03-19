package controller.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.goods.GoodsListService;
import model.dao.UserDAO;
import model.dto.AuthInfoDTO;

public class MainFrontController extends HttpServlet implements Servlet{
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException{
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		if(command.equals("/")) {
			GoodsListService action = new GoodsListService();
			action.execute(request);
			//System.out.println(request.getServletContext().getRealPath("/"));
			// 4. 사용자로 부터 쿠키를 가져옴
			Cookie [] cookies = request.getCookies();
			if(cookies != null && cookies.length > 0) {
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("storeId")) {
						// 5. 사용자에게 쿠기정보 전달
						request.setAttribute("sid",cookie.getValue());
					}
					if(cookie.getName().equals("keepLogin")) {
						UserDAO dao = new UserDAO(); 
						AuthInfoDTO auth = dao.loginSelect(cookie.getValue());
						HttpSession session = request.getSession();
						session.setAttribute("auth",auth);
					}
				}
			}
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);	
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
}
