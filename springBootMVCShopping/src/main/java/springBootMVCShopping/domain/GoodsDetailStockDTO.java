package springBootMVCShopping.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("goodsDetailStock")
public class GoodsDetailStockDTO {
	// stock을 GoodsDTO에 추가해도 되지만 마이바티스에서 생성자를 사용하고 있으므로 잘 못하면 수정해야할 것이 많을 수 있습니다.
	GoodsDTO goodsDTO;   // 상품정보를 가지고 오는 객체
	Integer stock;// 재고 수량을 가지고 오는 
}
