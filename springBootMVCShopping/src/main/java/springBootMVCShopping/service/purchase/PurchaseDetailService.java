package springBootMVCShopping.service.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCShopping.domain.OrderListDTO;
import springBootMVCShopping.mapper.PurchaseMapper;

@Service
public class PurchaseDetailService {
	@Autowired
	PurchaseMapper purchaseMapper;
	public void execute(String purchaseNum, Model model) {
		//쿼리문을 새로만들기 보다는 구매목록 을 그대로 활용해보자
		List<OrderListDTO> list = purchaseMapper.orderList(null, purchaseNum);
		model.addAttribute("dto", list.get(0)); // list중 한개만 가져오므로 list.get(0)를 해줍니다.
	}
}
