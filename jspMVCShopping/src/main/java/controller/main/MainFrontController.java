package controller.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.goods.GoodsListService;

public class MainFrontController extends HttpServlet 
		implements Servlet{
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		if(command.equals("/main.main")) {
			//상품들을 가지고 와야 합니다.
			GoodsListService action = new GoodsListService();
			action.execute(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
