package springBootMVCShopping.service.goodsIpgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCShopping.mapper.GoodsMapper;

@Service
public class GoodsIpgoAutoNumservice {
	@Autowired
	GoodsMapper goodsMapper; //입고번호 받아오는 것을 goodsAutoNum을 사용해서 받아 오겠습니다.
	public void execute(Model model) {
		String num = goodsMapper.ipgoAndGoodsAutoNum("goodsipgo", "ipgo_num", "hk", 3);
		model.addAttribute("ipgoNum", num);
	}
}
