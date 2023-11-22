package springBootMVCShopping.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("orderList")
public class OrderListDTO {
	// 구매정보 마다 결제정보 하나를 가지고 옵니다.
	PurchaseDTO purchaseDTO; // 1
	PaymentDTO paymentDTO;   //1
	//배송정보를 추가해줍니다.
	DeliveryDTO DeliveryDTO;  //이제 mapper에도 추가합니다.
	// 구매정보에 따른 하나 이상의 구매정보와 상품정보를 가져와야 하므로 list로 받아옵니다.
	List<PurchaseListGoodsDTO> purchaseListGoodsDTOs; // n
	// 그래서 1 대 다 입니다. 즉 1 대 n이라고 합니다.
}
