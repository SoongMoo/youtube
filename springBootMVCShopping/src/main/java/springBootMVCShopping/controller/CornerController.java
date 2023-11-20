package springBootMVCShopping.controller;

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

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.service.corner.GoodsWishListService;
import springBootMVCShopping.service.corner.GoodsWishService;
import springBootMVCShopping.service.corner.WishDelService;
import springBootMVCShopping.service.corner.WishGoodsDelsService;
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
