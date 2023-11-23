package springBootMVCShopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.domain.CartGoodsDTO;
import springBootMVCShopping.service.corner.CartInsertService;
import springBootMVCShopping.service.corner.CartListService;
import springBootMVCShopping.service.corner.CartQtyDownService;
import springBootMVCShopping.service.corner.GoodsCartDelService;
import springBootMVCShopping.service.corner.GoodsCartDelsService;
import springBootMVCShopping.service.corner.GoodsWishListService;
import springBootMVCShopping.service.corner.GoodsWishService;
import springBootMVCShopping.service.corner.WishDelService;
import springBootMVCShopping.service.corner.WishGoodsDelsService;
import springBootMVCShopping.service.goods.GoodsDetailService;
import springBootMVCShopping.service.goods.GoodsDetailViewService;

@Controller
@RequestMapping("corner")
public class CornerController {
	@Autowired
	GoodsDetailViewService goodsDetailViewService;
	@Autowired
	GoodsWishService goodsWishService;
	@Autowired
	GoodsWishListService goodsWishListService;
	@Autowired
	WishGoodsDelsService wishGoodsDelsService;
	@Autowired
	WishDelService wishDelService;
	@Autowired
	CartInsertService cartInsertService;
	@Autowired
	CartListService cartListService;
	@Autowired
	GoodsCartDelsService goodsCartDelsService;
	@Autowired
	GoodsCartDelService goodsCartDelService;
	@Autowired
	CartQtyDownService cartQtyDownService;
	@Autowired
	GoodsDetailService goodsDetailService;
	
	@RequestMapping("goodsDescript")
	public String goodsDescript(
			@RequestParam(value="goodsNum") String goodsNum,
			Model model) {
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/corner/goodsDescript";
	}
	
	@GetMapping("buyItem")
	public String buyItem( // 바로구매할 상품을 장바구니에 넣고 결제정보 페이지로 이동하면 바로구매가 된다.
			@RequestParam(value="goodsNum") String goodsNum,
			@RequestParam(value="qty") Integer qty,
			HttpSession session,HttpServletResponse response) {
		// 먼저 장바구니에 넣는다. 
		String result = cartInsertService.execute(goodsNum, qty, session);
		if(result == "999") {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				String str = "<script>"
						+ "alert('관리자는 구매할 수 없습니다.');"
						+ "location.href='/corner/detailView/"+goodsNum+"';" //장바구니에 안들어 갔으면 상품페이지
						+ "</script>";
				out.print(str);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(result == "000") {
			return "redirect:/"; //아니면 홈으로 
		}
		//정산적으로 처리 되었다면 결제정보 입력페이지로 이동하면 됩니다
		return "redirect:/purchase/goodsBuy?prodCk="+goodsNum;
	}
	
	@GetMapping("cartQtyDown")
	public void cartQtyDown(
			@RequestParam(value="goodsNum") String goodsNum,
			HttpSession session,HttpServletResponse response) {
		CartGoodsDTO dto = cartQtyDownService.execute(goodsNum, session);
		// 이번에는 DTO를  model로 전달하지 않고 ObjectMapper를 사용해 보겠습니다.
		ObjectMapper mapper = new ObjectMapper();
		response.setCharacterEncoding("utf-8");
		// response를 통해 ObjectMapper를 ajax에 전달하면 됩니다.
		try {
			response.getWriter().print(mapper.writeValueAsString(dto));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("cartDel")
	public String cartDel(
			@RequestParam("goodsNum") String goodsNum,
			HttpSession session) {
		goodsCartDelService.execute(goodsNum, session);
		return "redirect:cartList";
	}
	
	
	@PostMapping(value = "cartDels")
	@ResponseBody
	public String cartdel(// javascript 배열을 받을 이름에 배열 표시를 해줘야합니다.
			@RequestParam("goodsNums[]") String goodsNums[], //배열이므로 배열로 받아오겠습니다.
			HttpSession session) {
		return goodsCartDelsService.execute(goodsNums, session);
	}
	
	
	@GetMapping("cartList")
	public String cartList(Model model, HttpSession session) {
		cartListService.execute(model, session);
		return "thymeleaf/corner/cartList";
	}
	
	@GetMapping("cartAdd")
	// 비동기식이므로 ajax에 값을 전달하기 위해서는 ResTAPI 또는 @ResponseBody를 사용해야한다.
	@ResponseBody
	public String cartAdd(
			@RequestParam(value="goodsNum") String goodsNum,
			@RequestParam(value="qty") Integer qty,
			HttpSession session) {
		return cartInsertService.execute(goodsNum, qty, session);
	}
	@GetMapping("wishDel")
	public String wishDel(@RequestParam("goodsNum")String goodsNum,
			HttpSession session) {
		wishDelService.execute(goodsNum, session);
		return "redirect:wishList";
	}
	
	@PostMapping("goodsWishDels")
	public String goodsWishDels(
			@RequestParam("wishGoodsDel") String wishGoodsDels [],
			HttpSession session) {
		wishGoodsDelsService.execute(wishGoodsDels, session);
		return "redirect:wishList";
	}
	
	@GetMapping("wishList")
	public String wishList(HttpSession session, Model model) {
		goodsWishListService.execute(session, model);
		return "thymeleaf/corner/wishList";
	}
	
	@RequestMapping(value="goodsWishAdd", method=RequestMethod.POST)
	public @ResponseBody String goodsWishAdd( //ajax에 1또는 0을 전달하려면 RestAPI나  @ResponseBody 씀니다. 
			@RequestParam("goodsNum") String goodsNum,
			HttpSession session) { // 누구의 관심상품인지 알기 위해서는 로그인정보가 필요합니다.
		return goodsWishService.execute(goodsNum, session);
	}
	
	//index.html에서 주소를 pathValue로 전달했습니다.
	@GetMapping("detailView/{goodsNum}")
	public String prodInfo(@PathVariable("goodsNum") String goodsNum,
			Model model, HttpSession session) { //회원정보를 알아야 한다.
		goodsDetailViewService.execute(goodsNum, model, session);
		return "thymeleaf/corner/detailView";
	}
	
	
}
