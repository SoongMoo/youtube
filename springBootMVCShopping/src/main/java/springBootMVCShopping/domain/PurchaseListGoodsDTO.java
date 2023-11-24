package springBootMVCShopping.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("purchaseListGoods")
public class PurchaseListGoodsDTO {
	//구매한 각 상품마다 하나의 상품정보 : 구매한 각 상품마다 상품 정보를 가져와야 합니다.
	PurchaseListDTO purchaseList;
	GoodsDTO goods;
	// 상품당 후기가 하나이므로 여기에 후기 추가
	ReviewDTO review;
}
