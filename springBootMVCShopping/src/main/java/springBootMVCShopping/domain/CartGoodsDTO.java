package springBootMVCShopping.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("cartGoods")
public class CartGoodsDTO {
	GoodsDTO goodsDTO; // 상품정보 
	CartDTO cartDTO; //장바구니 정보 // 상품정보에 따른 장바구니 정보를 가지고 와야 합니다.
	Integer totalPrice; // 총 상품금액
	public GoodsDTO getGoodsDTO() {
		return goodsDTO;
	}
	public void setGoodsDTO(GoodsDTO goodsDTO) {
		this.goodsDTO = goodsDTO;
	}
	public CartDTO getCartDTO() {
		return cartDTO;
	}
	public void setCartDTO(CartDTO cartDTO) {
		this.cartDTO = cartDTO;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
}
