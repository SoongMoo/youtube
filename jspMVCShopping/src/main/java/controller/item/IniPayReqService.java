package controller.item;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.inicis.std.util.SignatureUtil;

import model.dao.ItemDAO;
import model.dto.PurchaseDTO;

public class IniPayReqService {
	public void execute(HttpServletRequest request) throws Exception {
		// 이니시스 모듈을 WEB-INF/lib밑에 복사해 줍니다.
		// 아래 코드는 니니시스로 부터 받은 코드 입니다.
		String mid					= "INIpayTest";		                    // 상점아이디					
		String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey
		
		String mKey = SignatureUtil.hash(signKey, "SHA-256");

		String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
		String orderNumber			= request.getParameter("orderNumber"); // 가맹점 주문번호(가맹점에서 직접 설정)
		
		ItemDAO  dao = new ItemDAO();
		PurchaseDTO dto = dao.purchaseSelect(orderNumber); 
		String price = dto.getPurchasePrice().toString(); // 결제 금액을 가져옵니다.
		
		Map<String, String> signParam = new HashMap<String, String>();
		signParam.put("oid", orderNumber);
		signParam.put("price", price);
		signParam.put("timestamp", timestamp);

		String signature = SignatureUtil.makeSignature(signParam);
		
		// 위 정보를 jsp로 넘겨주겠습니다.  키 이름은 바꾸지 않는 것이 좋습니다.
		request.setAttribute("timestamp", timestamp);
		request.setAttribute("mid", mid);
		request.setAttribute("signature", signature);
		request.setAttribute("mKey", mKey);
				
		request.setAttribute("goodsName",dto.getDeliveryName());
		request.setAttribute("orderNumber", dto.getPurchaseNum());
		request.setAttribute("price", dto.getPurchasePrice());
		
		
		
		
	}
}
