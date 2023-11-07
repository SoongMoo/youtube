package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.GoodsCommand;
import springBootMVCShopping.service.goods.GoodsAutoNumService;
import springBootMVCShopping.service.goods.GoodsListService;
import springBootMVCShopping.service.goods.GoodsWriteService;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	GoodsAutoNumService goodsAutoNumService;
	@Autowired
	GoodsListService goodsListService;
	@RequestMapping(value="goodsList" , method=RequestMethod.GET)
	public String  goodsList(
			@RequestParam(value="searchWord" , required = false) String searchWord,
			@RequestParam(value = "page" , required = false , defaultValue = "1") int page,
			Model model) {
		goodsListService.execute(searchWord, model, page);
		return "thymeleaf/goods/goodsList";
	}
	@GetMapping("goodsForm")
	public String goodsForm() {
		return "thymeleaf/goods/goodsWrite";
	}
	@GetMapping("goodsWrite")
	public String goodsWrite(Model model) {
		goodsAutoNumService.execute(model);
		return "thymeleaf/goods/goodsForm";
	}
	@Autowired
	GoodsWriteService goodsWriteService;
	@RequestMapping(value="goodsWrite" , method=RequestMethod.POST)
	public String goodsWrite(@Validated GoodsCommand goodsCommand,BindingResult result
			,HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/goods/goodsForm";
		}
		goodsWriteService.execute(goodsCommand, session);
		//오류가 없는 경우 goodsForm.html을 넘겨주고 정상이면 goodsRedirect.html을 넘겨준다.
		return "thymeleaf/goods/goodsRedirect";
	}
}
























