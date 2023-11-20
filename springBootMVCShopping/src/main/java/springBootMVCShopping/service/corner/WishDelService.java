package springBootMVCShopping.service.corner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.MemberDTO;
import springBootMVCShopping.mapper.MemberMyMapper;
import springBootMVCShopping.mapper.WishMapper;

@Service
public class WishDelService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	WishMapper wishMapper;
	public void execute(String goodsNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDTO dto = memberMyMapper.memberInfo(auth.getUserId());
		// 관심상품 등록할 때 이미 만들었습니다.
		wishMapper.wishDelete(goodsNum, dto.getMemberNum());
	}
}
