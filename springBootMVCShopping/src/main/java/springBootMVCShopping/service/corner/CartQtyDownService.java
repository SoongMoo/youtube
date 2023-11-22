package springBootMVCShopping.service.corner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.CartGoodsDTO;
import springBootMVCShopping.domain.MemberDTO;
import springBootMVCShopping.mapper.CartMapper;
import springBootMVCShopping.mapper.MemberMyMapper;

@Service
public class CartQtyDownService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	CartMapper cartMapper;
	public CartGoodsDTO execute(String goodsNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDTO memdto =  memberMyMapper.memberInfo(auth.getUserId());
		cartMapper.cartQtyDown(goodsNum, memdto.getMemberNum());
		// 꼭 이코드를 사용하지 않지 않아도 되지만 ObjectMapper를 사용하는 방법과 
		// 조인문 없이 조인 하는 방법을 설명하고자 한다.
		CartGoodsDTO dto =  cartMapper.goodsPriceMulCartQty(goodsNum, memdto.getMemberNum());
		return dto;
	}
}






