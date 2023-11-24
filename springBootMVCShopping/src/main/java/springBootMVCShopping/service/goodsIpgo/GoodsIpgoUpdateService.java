package springBootMVCShopping.service.goodsIpgo;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCShopping.command.GoodsIpgoCommand;
import springBootMVCShopping.domain.GoodsIpgoDTO;
import springBootMVCShopping.mapper.GoodsIpgoMapper;

@Service
public class GoodsIpgoUpdateService { 
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	public void execute(GoodsIpgoCommand goodsIpgoCommand) {
		GoodsIpgoDTO dto = new GoodsIpgoDTO();
		// 잘 생각해보면 insert나 update코드가 같다. 그래서 insert에 있는 코드를 가져다 써도 무관하다.
		// 필요없는 것만 지운다.
		dto.setGoodsNum(goodsIpgoCommand.getGoodsNum());
		dto.setIpgoDate(goodsIpgoCommand.getIpgoDate());
		dto.setIpgoNum(goodsIpgoCommand.getGoodsIpgoNum());
		dto.setIpgoPrice(goodsIpgoCommand.getIpgoPrice());
		dto.setIpgoQty(goodsIpgoCommand.getIpgoQty());
		dto.setMadeDate(Timestamp.valueOf(goodsIpgoCommand.getMadeDate()));
		goodsIpgoMapper.goodsIpgoUpdate(dto);
	}
}
