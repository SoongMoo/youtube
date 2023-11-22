package springBootMVCShopping.service.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.PurchaseCommand;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.CartDTO;
import springBootMVCShopping.domain.MemberDTO;
import springBootMVCShopping.domain.PurchaseDTO;
import springBootMVCShopping.domain.PurchaseListDTO;
import springBootMVCShopping.mapper.CartMapper;
import springBootMVCShopping.mapper.MemberMyMapper;
import springBootMVCShopping.mapper.PurchaseMapper;

@Service
public class GoodsOrderService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	PurchaseMapper purchaseMapper;
	@Autowired
	CartMapper cartMapper;
	public String execute(PurchaseCommand purchaseCommand, HttpSession session, Model model) {
		String purchaseNum = "";
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDTO memDto = memberMyMapper.memberInfo(auth.getUserId());
		// 구매 번호를 먼저 구해 오겠습니다.
		purchaseNum = purchaseMapper.selectNum();
		//  command 에 있는 상품번호를 split()해서 배열로 받아온다.
		String [] goodsNums = purchaseCommand.getGoodsNums().split("-");
		// 각각의 정보들을 구매dto에 저장한다.
		PurchaseDTO dto = new PurchaseDTO();
		dto.setPurchaseNum(purchaseNum);
		dto.setDeliveryAddr(purchaseCommand.getDeliveryAddr());
		dto.setDeliveryAddrDetail(purchaseCommand.getDeliveryAddrDetail());
		dto.setDeliveryName(purchaseCommand.getDeliverName());
		dto.setDeliveryPhone(purchaseCommand.getDeliveryPhone());
		dto.setDeliveryPost(purchaseCommand.getDeliveryPost());
		dto.setMemberNum(memDto.getMemberNum());
		dto.setMessage(purchaseCommand.getMessage());
		dto.setPurchasePrice(purchaseCommand.getSumPrice());
		dto.setPurchaseStatus("입급대기중");
		// 구매정보를 데이블에 저장합니다.
		purchaseMapper.purchaseInsert(dto);
		// 이제 구매한 상품정보들을 입력할 차례입니다.
		PurchaseListDTO plDto = new PurchaseListDTO();
		plDto.setGoodsNums(goodsNums);
		plDto.setPurchaseNum(purchaseNum);	
		plDto.setMemberNum(memDto.getMemberNum());
		purchaseMapper.purchaseListInsert(plDto);
		// cart에 있는 정보를 삭제해야 합니다.
		CartDTO cartDto = new CartDTO();
		cartDto.setMemberNum(memDto.getMemberNum());
		cartDto.setGoodsNums(goodsNums);
		cartMapper.cartGoodsDeletes(cartDto);
		return purchaseNum;
	}
}
