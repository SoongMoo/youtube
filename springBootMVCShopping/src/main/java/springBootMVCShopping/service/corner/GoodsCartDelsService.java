package springBootMVCShopping.service.corner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.MemberDTO;
import springBootMVCShopping.mapper.CartMapper;
import springBootMVCShopping.mapper.MemberMyMapper;

@Service
public class GoodsCartDelsService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	CartMapper cartMapper;
	public String execute(String[] goodsNums ,  HttpSession session) {
		//회원 정보 가져오기
		AuthInfoDTO auth  = (AuthInfoDTO)session.getAttribute("auth");
		MemberDTO memDto = memberMyMapper.memberInfo(auth.getUserId());
		// 이번에는 배열을 그냥 넘기는 것이 아니라 map에 저장해서 넘겨보겠습니다.
		List<String> cs  = new ArrayList<String>();
		// 배열에 있는 값을 리스트에 저장하겠습니다.
		for(String goodsNum : goodsNums) {
			cs.add(goodsNum);
		}
		//리스트에 저장한 값을 리스트에 넣겠습니다.
		Map<String, Object> condition = new HashMap<String, Object>(); 
		condition.put("goodsNums", cs);
		// map에 회원번호도 넣겠습니다.
		condition.put("memberNum", memDto.getMemberNum());
		int i = cartMapper.goodsNumsDelete(condition);
		if (i >= 1) return "200";
		else return "999";
	}
}





