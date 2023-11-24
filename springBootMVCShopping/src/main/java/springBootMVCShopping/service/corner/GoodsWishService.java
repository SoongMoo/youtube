package springBootMVCShopping.service.corner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.MemberDTO;
import springBootMVCShopping.mapper.MemberMyMapper;
import springBootMVCShopping.mapper.WishMapper;

@Service
public class GoodsWishService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	WishMapper wishMapper;
	public String execute(String goodsNum, HttpSession session) {
		//로그인 정보를 가지고 옵니다.
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		//회원번호(memberNum)이 필요한데 로그인 정보에는 memberId가 있습니다.
		// 그러므로 memberId를 이용해서 회원정보를 가지고 오겠습니다.
		MemberDTO memDto = memberMyMapper.memberInfo(auth.getUserId());
		// 로그인 아이디를 이용해서 회원정보를 가지고 왔습니다.
		
		// 관심상품에 등록하겠습니다, 단 관심상품은 한번만 등록하므로 다시 눌렀을 때는 취소가 되어야 하므로 
		// 먼저 괌심상품이 있는지 확인 하겠습니다.
		Integer i = wishMapper.wishGoodsSelect(goodsNum, memDto.getMemberNum());
		if(auth.getGrade().equals("mem")) { //mem은 회원, emp는 직원이다.
			if (i == null) {
				// null 이라는 것은 등록된 상품이 없다는 것이므로 등록을 해야 합니다.
				wishMapper.wishInsert(goodsNum, memDto.getMemberNum());
				return "1";
			}else if(i == 1) {
				// 1이 왔다는 것은 등록된것이 있으므로 취소를 해야 합니다.
				wishMapper.wishDelete(goodsNum, memDto.getMemberNum());
				return "0";
			}
		}else {
			return "999";
		}
		return null;
	}
}





