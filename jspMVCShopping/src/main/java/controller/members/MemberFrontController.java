package controller.members;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet 
				implements Servlet{
	public void doProcess(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		if(command.equals("/memberList.mem")) {
			MemberListService action = new MemberListService();
			action.execute(request);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("member/memberList.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memberWrite.mem")) {
			// 회원번호 자동부여...
			MemberAutoNumService action = new MemberAutoNumService();
			action.execute(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("member/memberForm.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memberRegist.mem")) {
			// html에서 받아온 값저장
			MemberWriteService action = new MemberWriteService();
			action.execute(request);
			response.sendRedirect("memberList.mem");
		}else if(command.equals("/memberDetail.mem")) {
			// 내용 가져오기
			MemberDetailService action = new MemberDetailService();
			action.execute(request);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("member/memberInfo.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memberUpdate.mem")) {
			MemberDetailService action = new MemberDetailService();
			action.execute(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("member/memberModify.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memberModify.mem")) {
			MemberUpdateService action =	new MemberUpdateService();
			action.execute(request);
			response.sendRedirect("memberDetail.mem?memberNum="
					+ request.getParameter("memberNum"));
		}else if(command.equals("/memberdelete.mem")) {
			MemberDeleteService action = new MemberDeleteService();
			action.execute(request);
			response.sendRedirect("memberList.mem");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp) 
					throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
}
