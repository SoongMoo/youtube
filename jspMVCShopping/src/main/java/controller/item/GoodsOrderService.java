package controller.item;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberMyDAO;
import model.dto.AuthInfoDTO;
import model.dto.MemberDTO;
import model.dto.PurchaseDTO;
import model.dto.PurchaseListDTO;

public class GoodsOrderService {
	public PurchaseDTO execute(HttpServletRequest request) {	
		PurchaseDTO dto = new PurchaseDTO(); // 구매정보DTO
		try { //한글 깨짐 방지
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//내 정보 가져오기
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberMyDAO myDao = new MemberMyDAO();
		MemberDTO memberDTO = myDao.memberInfo(auth.getUserId());
		
		String deliveryName = request.getParameter("deliveryName");
		String deliveryAddr = request.getParameter("deliveryAddr");
		String deliveryAddrDetail = request.getParameter("deliveryAddrDetail");
		String deliveryPost = request.getParameter("deliveryPost");
		String deliveryPhone = request.getParameter("deliveryPhone");
		Long purchasePrice = Long.parseLong(request.getParameter("goodsTotalPrice"));
		String message = request.getParameter("message");
		String memberNum  = memberDTO.getMemberNum();
		
		dto.setDeliveryAddr(deliveryAddr);
		dto.setDeliveryAddrDetail(deliveryAddrDetail);
		dto.setDeliveryName(deliveryName);
		dto.setDeliveryPhone(deliveryPhone);
		dto.setDeliveryPost(deliveryPost);
		dto.setMemberNum(memberNum);
		dto.setMessage(message);
		dto.setPurchasePrice(purchasePrice);
		
		// 현재날짜를 문자로 형변환 구매 번호만들기
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String purchaseNum = df.format(new Date());
		dto.setPurchaseNum(purchaseNum);
		ItemDAO dao = new ItemDAO();
		dao.purchaseInsert(dto); // 구매정보 입력
		// 구매 정보를 넣었으니 이제 구매 리스트에 상품정보를 넣어야 한다.
		String nums = request.getParameter("goodsNums");
		String goodsNums [] = nums.split("-");
		for (String goodsNum : goodsNums) {
			PurchaseListDTO dto1 = new PurchaseListDTO();
			dto1.setGoodsNum(goodsNum);
			dto1.setPurchaseNum(purchaseNum);
			int i = dao.purchaseListInsert(dto1, memberNum); // 구매 상품 정보 입력합니다.
			// 장바구니에 있는 정보를 구매리스트에 저장했으니 이제 장바구니에 있는 상품정보는 필요없게 되었다...
			if(i >= 0) dao.cartItemDelete(goodsNum, memberNum); // 장바구니에 있는 정보를 삭제 한다.
		}
		return dto;
	}
}
