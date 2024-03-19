package model.dto;

import java.util.Date;

public class WishListDTO {
	String goodsNum;
	String goodsMainStore;
	String goodsName;
	Integer goodsPrice;
	Date wishDate;
	public String getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getGoodsMainStore() {
		return goodsMainStore;
	}
	public void setGoodsMainStore(String goodsMainStore) {
		this.goodsMainStore = goodsMainStore;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Integer goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Date getWishDate() {
		return wishDate;
	}
	public void setWishDate(Date wishDate) {
		this.wishDate = wishDate;
	}
}
