package springBootMVCShopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.InquireCommand;
import springBootMVCShopping.domain.InquireDTO;
import springBootMVCShopping.repository.InquireRepository;
import springBootMVCShopping.service.inquire.GoodsInquireDetailService;
import springBootMVCShopping.service.inquire.InquireAnswerWriteService;
import springBootMVCShopping.service.inquire.InquireListService;
import springBootMVCShopping.service.inquire.InquireModifyService;
import springBootMVCShopping.service.inquire.InquireWriteService;

@Controller
@RequestMapping("inquire")
public class InquireController {
	@Autowired
	InquireWriteService inquireWriteService;
	@Autowired
	InquireListService inquireListService;
	@Autowired // service없이 직접 repository를 직접 불어오겠습니다.
	InquireRepository inquireRepository;
	@Autowired
	InquireAnswerWriteService inquireAnswerWriteService;
	@Autowired
	GoodsInquireDetailService goodsInquireDetailService;
	@Autowired
	InquireModifyService inquireModifyService;
	
	@RequestMapping("inquireDelete")
	public @ResponseBody Integer inquireDelete( //비동기식이므로  @ResponseBody
			@RequestParam(value="inquireNum") Integer inquireNum) {
		int i  = inquireRepository.inquireDelete(inquireNum);
		return i;
	}
	
	@PostMapping("inquireUpdate")
	public void inquireUpdate(InquireCommand inquireCommand , HttpServletResponse response) {
		inquireModifyService.execute(inquireCommand);		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			String str = "<script>"
					+ "opener.parent.inquire();"  //수정하고 부모에 있는 함수 불러오고 
					+ "window.self.close()" // 자기 자신은 닫기
					+ "</script>";
			out.print(str);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@GetMapping("inquireUpdate")
	public String inquireUpdate(
			@RequestParam(value="inquireNum") Integer inquireNum, Model model) {
		goodsInquireDetailService.execute(inquireNum,model);
		return "thymeleaf/inquire/goodsInquireUpdate"; 
	}
	
	@PostMapping("answerWrite")
	public String answerWrite(InquireCommand inquireCommand, HttpSession  session) {
		inquireAnswerWriteService.execute(inquireCommand, session);
		return "redirect:goodsQuestion";
	}
	
	@RequestMapping("goodsInquireDetail/{num}")
	public String goodsInquireDetail(
			@PathVariable(value="num") Integer inquireNum, Model model) {
		List<InquireDTO> list = inquireRepository.inquireList(null, inquireNum);
		model.addAttribute("list", list);
		model.addAttribute("newLineChar", "\n");
		return "thymeleaf/inquire/goodsInquireDetail"; 
	}
	
	@RequestMapping("goodsQuestion")
	public String goodsQuestion(Model model) { // 상품번호 상관없이 모두 불러 오는 것이므로 null을 줍니다.
		List<InquireDTO> list = inquireRepository.inquireList(null, null);
		model.addAttribute("list", list);
		return "thymeleaf/inquire/goodsQuestion";
	}
	
	@RequestMapping("inquireList")
	public String inquireList(
			@ModelAttribute("goodsNum") String goodsNum, Model model
			) {
		inquireListService.execute(goodsNum, model);
		model.addAttribute("newLineChar", "\n");
		return "thymeleaf/inquire/inquireList";
	}
	@GetMapping("inquireWrite")
	public String  inquireWrite(
			@ModelAttribute(value="goodsNum") String goodsNum, 
			//유효성검사를 하깅 위해서는  inquireCommand가 있어야 합니다.
			@ModelAttribute(value="inquireCommand") InquireCommand inquireCommand) {
		return "thymeleaf/inquire/inquireWrite";
	}
	
	@PostMapping("inquireWrite")
	public String  inquireWrite(InquireCommand inquireCommand, BindingResult result,
			HttpSession session, HttpServletResponse response) {
		inquireWriteService.execute(inquireCommand, session, result);
		if(result.hasErrors()) {
			return "thymeleaf/inquire/inquireWrite";
		}
		// 정상적으로 처리되면 popup창은 닫히고 상품상세 보기에는 등록된 상품문의가 다시 뜨도록해야 한다. 
		// 그러기 위해 자바스크립트를 사용한다.
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			String str = "<script>"
					+ "opener.parent.inquire();" // 부모창에 있는 inquire()를 실행시키기
					+ "window.self.close()" // 자신의 페이지 닫기
					+ "</script>";
			out.print(str);
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}
	
}






