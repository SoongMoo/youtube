package springBootMVCShopping.service.corner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.MemberDTO;
import springBootMVCShopping.mapper.MemberMyMapper;
import springBootMVCShopping.mapper.WishMapper;

@Service
public class WishGoodsDelsService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	WishMapper wishMapper;
	public void execute(String wishGoodsDels[], HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDTO dto = memberMyMapper.memberInfo(auth.getUserId());
		wishMapper.wishGoodsDeletes(wishGoodsDels, dto.getMemberNum());
	}
}
