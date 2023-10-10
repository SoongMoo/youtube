package controller.memberMyPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.AuthInfoDTO;

public class MemberMyPageFrontController extends HttpServlet 
			implements Servlet{
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		if(command.equals("/memberMyPage.my")) {
			MemberInfoService action = new MemberInfoService();
			action.execute(request);
			
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("memberMyPage/mypage.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memberUpdate.my")) {
			MemberInfoService action = new MemberInfoService();
			action.execute(request);
			
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("memberMyPage/myModify.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memberModify.my")) {
			// 수정하기 전에 회원비밀번호가 맞으면 수정되고 비밀번호가 일치하지 않으면 수정페이지가 다시 열리게 해야한다.
			MemberInfoUpdateService action =
					new MemberInfoUpdateService();
			int i = action.execute(request);
			if(i == 1)
				response.sendRedirect("memberMyPage.my");
			else if(i == 0) {
				MemberInfoService action1 = new MemberInfoService();
				action1.execute(request);
				request.setAttribute("errPw", "비밀번호가 틀렸습니다.");
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher("memberMyPage/myModify.jsp");
				dispatcher.forward(request, response);
			}
		}else if(command.equals("/memberDrop.my")) {
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("memberMyPage/memberDrop.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memberDropOk.my")){
			// 비밀번호가 일치하면 탈퇴 후 로그 아웃 아니면 다시 탈퇴 페이지로
			MemberDropService action = new MemberDropService();
			int i = action.execute(request);
			if(i == 1) {
				response.sendRedirect("logout.login");
			}else if(i == 0) {
				RequestDispatcher dispatcher =
						request.getRequestDispatcher("memberMyPage/memberDrop.jsp");
				dispatcher.forward(request, response);
			}
		}else if(command.equals("/userPwModify.my")) {
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("memberMyPage/myPwCon.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memberPwModify.my")) {
			HttpSession session = request.getSession();
			AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
			String memberPw = request.getParameter("memberPw");
			String path ="";
			// session에 저장된 비밀번호와 myPwCon.jsp로 부터넘오온 비밀번호가 일치하는지 확인
			if(memberPw.equals(auth.getUserPw())) {
				//일치하면 비밀번호 변경 페이지로
				path = "memberMyPage/myNewPw.jsp";
			}else {
				// 일치하지 않으면 원래 페이지로
				path="memberMyPage/myPwCon.jsp";
				request.setAttribute("errPw", "비밀번호가 틀렸습니다.");
			}
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(path);
			dispatcher.forward(request, response);	
		}else if(command.equals("/memberPwPro.my")) {
			MemberPasswordService action = new MemberPasswordService();
			int i = action.execute(request);
			if(i == 1) {
				//원래 비밀번호가 일치해서 비밀번호가 변경되면 마이페이지로
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script type='text/javascript'>");
				out.print("alert('비밀번호가 변경되었습니다.');");
				out.print("location.href='memberMyPage.my';");
				out.print("</script>");
				out.close();
			}else if(i == 0) {
				// 원래비밀번호가 틀리면 변경페이지로
				RequestDispatcher dispatcher =
						request.getRequestDispatcher("memberMyPage/myPwCon.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp) 
					throws ServletException, IOException {
		doProcess(req,resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) 
					throws ServletException, IOException {
		doProcess(req,resp);
	}
}
