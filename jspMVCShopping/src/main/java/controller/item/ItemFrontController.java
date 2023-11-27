package controller.item;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.goods.GoodsDetailService;
import model.dto.PurchaseDTO;

public class ItemFrontController  extends HttpServlet implements Servlet {
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		if(command.equals("/detailView.item")) {
			GoodsDetailService action = new GoodsDetailService();
			action.execute(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("item/detailView.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/wishItem.item")) {
			GoodsWishItemService action = new GoodsWishItemService();
			action.execute(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("item/wishResult.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/wishList.item")) {
			WishListService action = new WishListService();
			action.execute(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("item/wishList.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/wishDelete.item")) {
			WishDeleteService action = new WishDeleteService();
			action.execute(request);
			response.sendRedirect("wishList.item");
		}else if(command.equals("/cart.item")) {
			CartInsertService action = new CartInsertService();
			action.execute(request);
		}else if(command.equals("/cartList.item")) { 
			CartListService action = new CartListService();
			action.execute(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("item/cartList.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/cartItemDel.item")) {
			CartItemDeleteService action = new CartItemDeleteService();
			action.execute(request);
			response.sendRedirect("cartList.item");
		}else if(command.equals("/cartItemsDel.item")) {
			CartItemsDeleteService action = new CartItemsDeleteService();
			action.execute(request);
			response.sendRedirect("cartList.item");
		}else if(command.equals("/cartQtyDown.item")) {
			CartQtyDownService action = new CartQtyDownService();
			action.execute(request);
		}else if(command.equals("/itemBuy.item")) {
			GoodsItemService action = new GoodsItemService();
			action.execute(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("item/goodsOrder.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/goodsOrder.item")) {
			GoodsOrderService action = new GoodsOrderService();
			PurchaseDTO dto = action.execute(request); 
													// 구매번호
			response.sendRedirect("paymentOk.item?orderNumber=" + dto.getPurchaseNum()); 
			// 구매번호를 가지고 결제 페이지로 이동합니다.
		}else if(command.equals("/paymentOk.item")) {
			// 이니시스로 부터 받은 코드 payment.jsp에 전달될 값들이 정의 되어 있다.
			IniPayReqService action = new IniPayReqService();
			try {
				action.execute(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("item/payment.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/INIstdpay_pc_return.item")) {
			// 결제가 완료되면 이니시스로 부터 정보를 받아옵니다.
			INIstdpayPcReturn action = new INIstdpayPcReturn();
			action.execute(request);
			// 그리고 결제 완료 페이지로 이동
			RequestDispatcher dispatcher = request.getRequestDispatcher("item/buyfinished.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/close.item")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("item/close.jsp");
			dispatcher.forward(request, response);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
}
