package springBootMVCShopping.service.corner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.GoodsDTO;
import springBootMVCShopping.domain.MemberDTO;
import springBootMVCShopping.mapper.GoodsMapper;
import springBootMVCShopping.mapper.MemberMyMapper;
import springBootMVCShopping.mapper.WishMapper;

@Service
public class GoodsWishListService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(HttpSession session, Model model) {
		// 내 정보를 가져오기 위한 코드
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("auth");
		MemberDTO memDto = memberMyMapper.memberInfo(authInfo.getUserId());
		///관심상품 정보 가지고 오기
		List<GoodsDTO> list = goodsMapper.wishGoodsList(memDto.getMemberNum());
		model.addAttribute("dtos", list);		
	}
}
