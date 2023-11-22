package springBootMVCShopping.service.corner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.CartDTO;
import springBootMVCShopping.domain.MemberDTO;
import springBootMVCShopping.mapper.CartMapper;
import springBootMVCShopping.mapper.MemberMyMapper;

@Service
public class CartInsertService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	CartMapper cartMapper;
	public String execute(String goodsNum, Integer qty, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		// 로그인 정보가 있는지 확인 합니다. 로그인 오류가 발생할 수 있습니다.
		if(auth != null) {
			// 직원은 장바구니를 사용할 수 없으므로 오류가 발생할 수 있습니다.
			if(auth.getGrade().equals("mem")) {
				// 회원번호를 가져오겠습니다.
				MemberDTO  memDto = memberMyMapper.memberInfo(auth.getUserId());
				//장바구니 dto에 정보를 저장합니다.
				CartDTO dto = new CartDTO();
				dto.setCartQty(qty);
				dto.setGoodsNum(goodsNum);
				dto.setMemberNum(memDto.getMemberNum());
				cartMapper.cartInsert(dto);
				return "200"; //정상처리됨
			}else {
				System.out.println("직원은 직원전용페이지를 사용하세요..");
				return "999";
			}
		}else {
			System.out.println("로그인을 해야합니다.");
			return "000";
		}
	}
}
