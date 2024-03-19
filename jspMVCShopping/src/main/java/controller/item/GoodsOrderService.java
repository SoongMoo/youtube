package controller.item;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ItemDAO;
import model.dao.MemberDAO;
import model.dto.AuthInfoDTO;
import model.dto.PurchaseDTO;

public class GoodsOrderService {
	public String execute(HttpServletRequest request) {
		try { //한글 깨짐 방지
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String purchaseName = request.getParameter("purchaseName");
		String totalPaymentPrice = request.getParameter("totalPaymentPrice");
		String deliveryName = request.getParameter("deliveryName");
		String deliveryAddr = request.getParameter("deliveryAddr");
		String deliveryAddrDetail = request.getParameter("deliveryAddrDetail");
		String deliveryPost = request.getParameter("deliveryPost");
		String deliveryPhone = request.getParameter("deliveryPhone");
		String message = request.getParameter("message");
		
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDAO myDao = new MemberDAO();
		String memberNum = myDao.memberNumSelect(auth.getUserId());
		
		PurchaseDTO dto = new PurchaseDTO(); // 구매정보DTO
		dto.setDeliveryAddr(deliveryAddr);
		dto.setDeliveryAddrDetail(deliveryAddrDetail);
		dto.setDeliveryPhone(deliveryPhone);
		dto.setDeliveryPost(deliveryPost);
		dto.setMessage(message);
		dto.setPurchasePrice(Long.parseLong(totalPaymentPrice));
		dto.setDeliveryName(deliveryName);
		dto.setMemberNum(memberNum);
		dto.setPurchaseName(purchaseName);
		// 날자를 구매번호로 생성
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String purchaseNum = sdf.format(new Date());
		dto.setPurchaseNum(purchaseNum);
		
		ItemDAO dao = new ItemDAO();
		dao.purchaseInsert(dto); // 구매정보 입력
		
		// 구매리스트
		String nums = request.getParameter("goodsNums");
		String goodsNums[] = nums.split("/");
		for(String goodsNum : goodsNums) {
			int i = dao.purchaseListInsert(purchaseNum, goodsNum, memberNum);
			System.out.println(i);
			if(i >= 1) dao.itemDelete(goodsNum, memberNum);
		}
		return purchaseNum;
	}
}
