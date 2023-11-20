package springBootMVCShopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCShopping.domain.GoodsDTO;
import springBootMVCShopping.mapper.CartMapper;

@Service
public class MainGoodsListService {
	@Autowired
	CartMapper cartMapper;
	public void execute( Model model) {
		List<GoodsDTO> list = cartMapper.goodsSelectAll();
		model.addAttribute("list", list);
	}
}
