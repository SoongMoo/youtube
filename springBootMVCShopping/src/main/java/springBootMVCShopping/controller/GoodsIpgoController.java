package springBootMVCShopping.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.GoodsIpgoCommand;
import springBootMVCShopping.domain.GoodsIpgoDTO;
import springBootMVCShopping.service.goodsIpgo.GoodsIpgoAutoNumservice;
import springBootMVCShopping.service.goodsIpgo.GoodsIpgoDeleteService;
import springBootMVCShopping.service.goodsIpgo.GoodsIpgoDetailService;
import springBootMVCShopping.service.goodsIpgo.GoodsIpgoListService;
import springBootMVCShopping.service.goodsIpgo.GoodsIpgoService;
import springBootMVCShopping.service.goodsIpgo.GoodsIpgoUpdateService;
import springBootMVCShopping.service.goodsIpgo.GoodsItemService;

@Controller
@RequestMapping("goods")
public class GoodsIpgoController {
	@Autowired
	GoodsItemService goodsItemService;
	@Autowired
	GoodsIpgoAutoNumservice goodsIpgoAutoNumservice;
	@Autowired
	GoodsIpgoService goodsIpgoService;
	@Autowired
	GoodsIpgoListService goodsIpgoListService;
	@Autowired
	GoodsIpgoDetailService goodsIpgoDetailService;
	@Autowired
	GoodsIpgoUpdateService goodsIpgoUpdateService;
	@Autowired
	GoodsIpgoDeleteService goodsIpgoDeleteService;
	
	@GetMapping("goodsIpgoDelete")
	public String goodsIpgoDelete(
			@RequestParam("ipgoNum") String ipgoNum,
			@RequestParam("num") String goodsNum) {
		goodsIpgoDeleteService.execute(ipgoNum, goodsNum);
		return "redirect:goodsIpgoList";
	}
	@PostMapping("goodsIpgoModify")
	public String goodsIpgoModify(GoodsIpgoCommand goodsIpgoCommand) {
		goodsIpgoUpdateService.execute(goodsIpgoCommand);
		return "redirect:goodsIpgoList";
	}
	
	@RequestMapping(value="goodsIpgoUpdate" ,method=RequestMethod.GET)
	public String goodsIpgoUpdate(
			@RequestParam("ipgoNum") String ipgoNum,
			@RequestParam("num") String goodsNum,
			Model model) {// 상세보기나 수정페이지에서 보는 내용이 같다.
		GoodsIpgoDTO dto= goodsIpgoDetailService.execute(ipgoNum,goodsNum);
		model.addAttribute("dto", dto); // dto를 전달하는 것이 아니므로 model에 저장
		return "thymeleaf/goodsIpgo/goodsIpgoUpdate";
	}
	
	@PostMapping("goodsIpgoDetail")
	// @ResponseBody를 밖에 적어도 무관하다.
	public @ResponseBody GoodsIpgoDTO detail(
			@RequestParam("ipgoNum") String ipgoNum,
			@RequestParam("goodsNum") String goodsNum) {
		GoodsIpgoDTO dto= goodsIpgoDetailService.execute(ipgoNum,goodsNum);
		return dto;
	}
	
	@PostMapping("goodsIpgoList")
	public ModelAndView goodsIpgoList(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");
		goodsIpgoListService.execute(model);
		return mav;
	}
	@GetMapping("goodsIpgoList")
	public String goodsIpgoList() {
		return "thymeleaf/goodsIpgo/goodsIpgoList";
	}
	@RequestMapping(value="ipgoRegist",method = RequestMethod.POST )
	public String ipgoRegist(GoodsIpgoCommand goodsIpgoCommand, HttpSession session) {
		goodsIpgoService.execute(goodsIpgoCommand, session); //로그를 확인하면 정상으로 저장된 걸 알 수 있습니다.
		return "redirect:goodsIpgoList"; //이제 리스트페이지를 만들겠습니다.
	}	
	@RequestMapping(value="goodsIpgo",method = RequestMethod.GET )
	public String goodsIpgo(Model model) {
		goodsIpgoAutoNumservice.execute(model);
		return "thymeleaf/goodsIpgo/goodsIpgo";
	}
	@GetMapping(value="goodsItem")
	public String goodsItem() {
		return "thymeleaf/goodsIpgo/goodsItem";
	}
	@PostMapping(value="goodsItemList")
	@ResponseBody // Map을 이용해 데이터를 html에 보내려면 RestAPI를 사용하지만 @ResponseBody를 이용하겠습니다.
	public Map<String, Object> goodsItem( //searchWord를 가져오기 위해 html에 검색 부분 추가
			@RequestParam(value = "searchWord", required = false) String searchWord,
			@RequestParam(value = "page" , required = false, defaultValue = "1" ) int page
			){ //함수 중복은 일단 넘어가겠습니다.
		Map<String, Object> map = goodsItemService.execute(searchWord, page);
		return map;
	}
}
