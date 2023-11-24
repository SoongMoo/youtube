package springBootMVCShopping.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCShopping.command.GoodsCommand;
import springBootMVCShopping.mapper.GoodsMapper;

@Service
public class GoodsAutoNumService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(Model model) {
		//"prod"글자수에 따라 숫자의 변화를 줄 수도 있다. 
		// 번호를 gd100001와 같이 부여 하겠습니다.
		// 만약 먼저 설명한 것 처럼 "prod"를 주면 prod100001가 될 것이다.
		// 지금은 gd로 주어서 gd100001가 되었다.
		String goodsNum = goodsMapper.ipgoAndGoodsAutoNum("goods","goods_num","gd", 3);
		//나중에 command를 사용해야 하므로 command를 먼저 만들고 command에 상품번호를 저장합니다.
		GoodsCommand goodsCommand = new GoodsCommand();
		goodsCommand.setGoodsNum(goodsNum);
		model.addAttribute("goodsCommand", goodsCommand);
	}
}
