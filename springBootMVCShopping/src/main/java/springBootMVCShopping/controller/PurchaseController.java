package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.PurchaseCommand;
import springBootMVCShopping.service.IniPayReqService;
import springBootMVCShopping.service.purchase.GoodsBuyService;
import springBootMVCShopping.service.purchase.GoodsOrderService;
import springBootMVCShopping.service.purchase.IniPayReturnService;
import springBootMVCShopping.service.purchase.OrderProcessListService;
import springBootMVCShopping.service.purchase.PaymentDeleteService;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	GoodsBuyService goodsBuyService;
	@Autowired
	GoodsOrderService goodsOrderService;
	@Autowired
	IniPayReqService iniPayReqService;
	@Autowired
	IniPayReturnService iniPayReturnService; 
	@Autowired
	OrderProcessListService orderProcessListService;
	@Autowired
	PaymentDeleteService paymentDeleteService;
	@RequestMapping("paymentDel")
	public String paymentDel(
			@RequestParam("purchaseNum") String purchaseNum) {
		paymentDeleteService.execute(purchaseNum);
		return "redirect:orderList";
	}
	
	@RequestMapping("orderList")
	public String orderList(HttpSession session, Model model) {
		orderProcessListService.execute(session, model);
		return "thymeleaf/purchase/orderList";
	}
	
	@PostMapping("INIstdpay_pc_return")
	public String payReturn(HttpServletRequest request,HttpSession session, Model model) {
		iniPayReturnService.execute(request, session, model);
		return "thymeleaf/purchase/buyfinished";
	}
	@GetMapping("close")
	public String close() {
		return "thymeleaf/purchase/close";
	}
	@GetMapping("paymentOk")
	public String paymentOk(
			@RequestParam(value="purchaseNum") String purchaseNum
			,HttpSession session
			,Model model) {
		try {
			iniPayReqService.execute(purchaseNum,model,session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 이제 결제 페이지로 이동하겠습니다.
		return "thymeleaf/purchase/payment";
	}
	@PostMapping("goodsOrder")
	public String goodsOrder(PurchaseCommand purchaseCommand,HttpSession session, Model model,
			HttpServletResponse response) {
		String purchaseNum = goodsOrderService.execute(purchaseCommand,session, model);
		// paymentOk를 만들어 주겠습니다.
		return "redirect:paymentOk?purchaseNum="+purchaseNum;
	}
	
	
	@RequestMapping(value="goodsBuy")
	public String goodsBuy(
			@RequestParam(value="prodCk") String [] prodCk,// check박스가 배열로 전송된다.
			HttpSession session, Model model) {
		goodsBuyService.execute(prodCk,session,model);
		return "thymeleaf/purchase/goodsOrder";
	}
}
