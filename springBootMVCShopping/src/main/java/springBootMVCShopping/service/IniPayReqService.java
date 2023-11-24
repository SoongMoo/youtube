package springBootMVCShopping.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.inicis.std.util.SignatureUtil;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.PurchaseDTO;
import springBootMVCShopping.mapper.PurchaseMapper;

@Service
public class IniPayReqService {
	@Autowired
	PurchaseMapper purchaseMapper; // 세개의 메서드를 만듭니다.
	public void execute(String purchaseNum, Model model,HttpSession session) throws Exception {
		// 먼저 구매 정보를 가져와야 합니다.
		PurchaseDTO dto = purchaseMapper.purchaseSelect(purchaseNum);
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("auth");
		// 이니시스에서 메일정보를 필요로 합니다.
		String buyeremail = authInfo.getUserEmail();
		// 구매 상품 갯수를  가지고 오겠습니다. 구매 이름에 활용
		int cnt = purchaseMapper.purchaseGoodsCount(purchaseNum);
		// 구매한 상품중 하나의 이름만 가져와서 구매이름으로 사용합니다.
		String goodsNum = purchaseMapper.firstGoods(purchaseNum);
		// 두개를 이용해서 구매이름을 만듭니다.
		String purchaseName = goodsNum + "외 " + String.valueOf(cnt-1);
		
		// 아래 코드는 이니시스로 부터 받아온 코드이고 필요한 부분만 추가 또는 수정하였습니다.
		String mid					= "INIpayTest";		                    // 상점아이디					
		String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey
		
		String mKey = SignatureUtil.hash(signKey, "SHA-256");

		String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
		String orderNumber			= purchaseNum;	// 가맹점 주문번호(가맹점에서 직접 설정)
		String price				= dto.getPurchasePrice().toString();								// 상품가격(특수기호 제외, 가맹점에서 직접 설정)

		Map<String, String> signParam = new HashMap<String, String>();

		signParam.put("oid", orderNumber);
		signParam.put("price", price);
		signParam.put("timestamp", timestamp);
		//SignatureUtil 를 사용하려면 gradle에서 불러와야 합니다.
		String signature = SignatureUtil.makeSignature(signParam);
		// 결제시 필요한 내용입니다. // 이니스에서 준 변수명을 그대로 사용했습니다. html에서 사용
		model.addAttribute("timestamp", timestamp);
		model.addAttribute("mid", mid);
		model.addAttribute("signature", signature);
		model.addAttribute("orderNumber", orderNumber);
		model.addAttribute("mKey", mKey);
		model.addAttribute("purchaseName", purchaseName);
		model.addAttribute("price", price);
		model.addAttribute("buyername", dto.getDeliveryName());
		model.addAttribute("buyertel", dto.getDeliveryPhone());
		model.addAttribute("buyeremail", buyeremail );
	}
}
