package springBootMVCShopping.service.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.CartGoodsDTO;
import springBootMVCShopping.domain.MemberDTO;
import springBootMVCShopping.mapper.CartMapper;
import springBootMVCShopping.mapper.MemberMyMapper;

@Service
public class GoodsBuyService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	CartMapper cartMapper;
	public void execute(String [] prodCk,HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");	
		MemberDTO memDto = memberMyMapper.memberInfo(auth.getUserId());
		//카트로 부터 구매정보를 가지고와야 합니다. // 그런데 문제는 prodCk네 있는 goodsNum만 가지고 와야 합니다.
		List<CartGoodsDTO> list = cartMapper.cartList(memDto.getMemberNum(), prodCk);
		Integer sumPrice = 0; // 상품수량 합계금액
		Integer sumTotalPrice = 0; // 결제 금액
		Integer sumDeliveryCost = 0; // 배송비합계금액
		String goodsNums = ""; // 상품번호들 저장
		for(CartGoodsDTO dto : list) {
			sumTotalPrice += dto.getTotalPrice();
			sumDeliveryCost += dto.getGoodsDTO().getDeliveryCost();
			goodsNums += dto.getGoodsDTO().getGoodsNum() + "-";
		}
		sumPrice = sumTotalPrice + sumDeliveryCost;
		model.addAttribute("list", list);
		model.addAttribute("sumPrice", sumPrice);
		model.addAttribute("sumTotalPrice", sumTotalPrice);
		model.addAttribute("sumDeliveryCost", sumDeliveryCost);
		model.addAttribute("goodsNums", goodsNums);
	}
}
