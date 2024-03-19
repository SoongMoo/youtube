package controller.myPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.employees.EmployeeDetailService;
import controller.members.MemberDetailService;
import model.dto.AuthInfoDTO;

public class MyPageFrontController extends HttpServlet implements Servlet{
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response)
					throws  ServletException, IOException{
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		if(command.equals("/memberMyPage.my")) {
			MemberDetailService action = new MemberDetailService();
			action.execute(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("myPage/memberMyPage.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memberUpdate.my")) {
			MemberDetailService action = new MemberDetailService();
			action.execute(request);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("myPage/myModify.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memberModify.my")) {
			MemberInfoUpdateService action = 
					new MemberInfoUpdateService();
			int i = action.execute(request);
			if(i == 1) {
				response.sendRedirect("memberMyPage.my");
			}else {
				MemberDetailService action1 = new MemberDetailService();
				action1.execute(request);
				request.setAttribute("errPw", "비밀번호가 맞지 않습니다.");
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher("myPage/myModify.jsp");
				dispatcher.forward(request, response);
			}
		}else if(command.equals("/userPwModify.my")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("myPage/myPwCon.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memberPwModify.my")) {
			HttpSession session = request.getSession();
			AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
			String pw = request.getParameter("memberPw");
			String path = "";
			if(!auth.getUserPw().equals(pw)) {
				request.setAttribute("errPw", "비밀번호가 틀렸습니다.");
				path = "myPage/myPwCon.jsp";
			}else {
				path = "myPage/myNewPw.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}else if(command.equals("/memberPwPro.my")) {
			MemberPasswordService action = new MemberPasswordService();
			int i = action.execute(request);
			if(i == 1) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("안녕하세요.");
				out.print("<script type='text/javascript'>");
				out.print("alert('비밀번호가 수정되었습니다.');");
				out.print("location.href='memberMyPage.my';");
				out.print("</script>");
				out.close();
				//response.sendRedirect("memberMyPage.my");
			}else {
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher("myPage/myPwCon.jsp");
				dispatcher.forward(request, response);
			}
		}else if(command.equals("/memberDrop.my")) {
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("myPage/memberDrop.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memberDropOk.my")) {
			MemberDropService action = new MemberDropService();
			int i = action.execute(request);
			if(i == 1) {
				response.sendRedirect("logout.login");
			}else {
				RequestDispatcher dispatcher =
						request.getRequestDispatcher("myPage/memberDrop.jsp");
				dispatcher.forward(request, response);
			}
		}else if(command.equals("/empMyPage.my")) {
			EmployeeDetailService action = 
					new EmployeeDetailService();
			action.execute(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("myPage/employeeMyPage.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empUpdate.my")) {
			EmployeeDetailService action = 
					new EmployeeDetailService();
			action.execute(request);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("myPage/empModify.jsp");
			dispatcher.forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doProcess(req, resp);
	}
}
