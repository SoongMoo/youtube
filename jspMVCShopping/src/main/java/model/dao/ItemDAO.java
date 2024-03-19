package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.CartDTO;
import model.dto.CartListDTO;
import model.dto.PaymentDTO;
import model.dto.PurchaseDTO;
import model.dto.PurchaseInfoDTO;
import model.dto.WishListDTO;

public class ItemDAO extends DataBaseInfo {
	public void wishItem(String goodsNum, String memberNum) {
		con = getConnection();
		sql = " merge into wish w "
			+ " using (select goods_num from goods where goods_num = ?) g "
			+ " on  (w.goods_num = g.goods_num  and member_num =? ) "
			+ " When MATCHED THEN "
			+ " 	update set WISH_DATE = sysdate"
			+ " 	delete where  member_num = ? and goods_num = ? "
			+ " When not MATCHED THEN "
			+ " 	insert (MEMBER_NUM,GOODS_NUM,WISH_DATE  ) "
			+ " 	values (?, ?, sysdate)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			pstmt.setString(2, memberNum);
			pstmt.setString(3, memberNum);
			pstmt.setString(4, goodsNum);
			pstmt.setString(5, memberNum);
			pstmt.setString(6, goodsNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 병합되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
	}
	
	public int wishSelectOne(String memberNum,String goodsNum){
		con = getConnection();
		sql = " select * from wish where member_num = ? and goods_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberNum);
			pstmt.setString(2, goodsNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
		return 0;
	}
	public  List<WishListDTO> wishSelectList(String memberNum){
		List<WishListDTO> list = new ArrayList<WishListDTO>();
		con = getConnection();
		sql = " select g.goods_num, goods_Main_Store, goods_name,goods_price "
			+ "       ,wish_date "
			+ " from goods g join wish w "
			+ " on g.goods_num = w.goods_num "
			+ " where member_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				WishListDTO dto = new WishListDTO();
				dto.setGoodsMainStore(rs.getString("goods_Main_Store"));
				dto.setGoodsName(rs.getString("goods_name"));
				dto.setGoodsNum(rs.getString("goods_num"));
				dto.setGoodsPrice(rs.getInt("goods_price"));
				dto.setWishDate(rs.getDate("wish_date"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
		return list;
	}
	public void wishGoodsDelete(String memberNum,String goodsNum) {
		con = getConnection();
		sql = " delete from wish where goods_num =? and member_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			pstmt.setString(2, memberNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
	}
	public void cartInsert(CartDTO dto) {
		con = getConnection();
		sql = " merge into cart c "
			+ " using (select goods_num from goods where goods_num = ?) g "
			+ " on (c.goods_num = g.goods_num and MEMBER_NUM = ? ) "
			+ " WHEN MATCHED THEN "
			+ " update set cart_qty =  cart_qty + ? "
			+ " WHEN NOT MATCHED THEN "
			+ " insert (MEMBER_NUM, GOODS_NUM, CART_DATE , CART_QTY) "
			+ " values (?, g.goods_num, sysdate, ? ) ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getGoodsNum());
			pstmt.setString(2, dto.getMemberNum());
			pstmt.setInt(3, dto.getCartQty());
			pstmt.setString(4, dto.getMemberNum());
			pstmt.setInt(5, dto.getCartQty());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 병합되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
	}
	public List<CartListDTO> cartSelectList(String memberNum) {
		List<CartListDTO> list = new ArrayList<CartListDTO>();
		con = getConnection();
		sql = " select g.goods_Num , goods_Name, goods_Price, goods_main_store, delivery_Cost "
			+ "   	  ,MEMBER_NUM, CART_QTY, CART_DATE"
			+ "       ,goods_Price * CART_QTY  total_price "
			+ " from goods g join cart c "
			+ " on g.goods_num = c.goods_num "
			+ " where MEMBER_NUM = ? "
			+ " order by g.goods_Num desc ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CartListDTO dto = new CartListDTO();
				dto.setCartDate(rs.getDate("CART_DATE"));
				dto.setCartQty(rs.getInt("CART_QTY"));
				dto.setGoodsName(rs.getString("goods_name"));
				dto.setGoodsNum(rs.getString("GOODS_NUM"));
				dto.setMemberNum(rs.getString("MEMBER_NUM"));
				dto.setTotalPrice(rs.getInt("total_price"));
				dto.setGoodsImage(rs.getString("goods_main_store"));
				dto.setGoodsPrice(rs.getInt("goods_price"));
				dto.setDeliveryCost(rs.getInt("delivery_Cost"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
		return list;
	}
	public void itemDelete(String goodsNum, String memberNum) {
		con = getConnection();
		sql = " delete from cart where goods_num = ? and member_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			pstmt.setString(2, memberNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void itemQtyDown(String goodsNum, String memberNum) {
		con = getConnection();
		sql = " update cart "
			+ " set CART_QTY = CART_QTY - 1 "
			+ " where goods_num = ? and  member_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			pstmt.setString(2, memberNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}		
	}
	public CartListDTO ItemSelectOne(String goodsNum, String memberNum) {
		CartListDTO dto = new CartListDTO();
		System.out.println(goodsNum);
		System.out.println(memberNum);
		con = getConnection();
		sql = " select g.goods_Num,goods_Name,goods_Price,goods_main_store,delivery_Cost"
			+ "        ,MEMBER_NUM, CART_QTY, CART_DATE"
			+ "        ,goods_Price * CART_QTY  total_price"
			+ " from goods g join cart c "
			+ " on g.goods_num = c.goods_num "
			+ " where MEMBER_NUM = ? and g.goods_num = ?   "
			+ " order by g.goods_Num desc";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberNum);
			pstmt.setString(2, goodsNum);
			rs = pstmt.executeQuery();
			rs.next();
			dto.setCartDate(rs.getDate("CART_DATE"));
			dto.setCartQty(rs.getInt("CART_QTY"));
			dto.setGoodsName(rs.getString("goods_name"));
			dto.setGoodsNum(rs.getString("GOODS_NUM"));
			dto.setMemberNum(rs.getString("MEMBER_NUM"));
			dto.setTotalPrice(rs.getInt("total_price"));
			dto.setGoodsImage(rs.getString("goods_main_store"));
			dto.setGoodsPrice(rs.getInt("goods_price"));  
			dto.setDeliveryCost(rs.getInt("delivery_Cost"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		return dto;
	}
	
	public void purchaseInsert(PurchaseDTO dto) {
		con = getConnection();
		sql = " insert into purchase(PURCHASE_NUM, PURCHASE_DATE, PURCHASE_PRICE "
				+ "					,DELIVERY_ADDR, DELIVERY_ADDR_DETAIL, DELIVERY_POST"
				+ "                 ,DELIVERY_PHONE, MESSAGE, PURCHASE_STATUS, MEMBER_NUM"
				+ "                 ,DELIVERY_NAME, PURCHASE_NAME)"
			+ " values(?, sysdate, ?, ?, ?, ?, ?, ?, '결제대기중', ?, ?, ?)  ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setLong(2, dto.getPurchasePrice());
			pstmt.setString(3, dto.getDeliveryAddr());
			pstmt.setString(4, dto.getDeliveryAddrDetail());
			pstmt.setString(5, dto.getDeliveryPost());
			pstmt.setString(6, dto.getDeliveryPhone());
			pstmt.setString(7, dto.getMessage());
			pstmt.setString(8, dto.getMemberNum());
			pstmt.setString(9, dto.getDeliveryName());
			pstmt.setString(10, dto.getPurchaseName());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개 행이(가) 삽입되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public int purchaseListInsert( String purchaseNum,String goodsNum, String memberNum) {
		int i = 0;
		con = getConnection();
		sql = " insert into purchase_list(PURCHASE_NUM,GOODS_NUM,PURCHASE_QTY,total_price ) "
			+ " select ?, c.GOODS_NUM , cart_qty, cart_qty * goods_price "
			+ " from cart c join goods g "
			+ " on c.goods_num = g.goods_num "
			+ " where member_num = ?  and c.goods_num =?  ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			pstmt.setString(2, memberNum);
			pstmt.setString(3, goodsNum);
			i = pstmt.executeUpdate();
			System.out.println(i + "개가 삽입되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
		return i;
	}
	
	public PurchaseDTO purchaseSelectOne(String purchaseNum) {
		System.out.println(purchaseNum);
		PurchaseDTO dto = new PurchaseDTO();
		con = getConnection();
		sql = " select purchase_price,delivery_name,delivery_Phone, purchase_Name"
				+ "	  ,member_num "
			+ " from purchase  "
			+ " where purchase_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			rs = pstmt.executeQuery();
			rs.next();
			dto.setDeliveryName(rs.getString("delivery_name"));
			dto.setPurchasePrice(rs.getLong("purchase_price"));
			dto.setDeliveryPhone(rs.getString("delivery_Phone"));
			dto.setPurchaseName(rs.getString("purchase_Name"));
			dto.setMemberNum(rs.getString("member_num"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
		return dto;
	}
	
	public List<PurchaseInfoDTO> purchaseItemSelect(String memberNum) {
		List<PurchaseInfoDTO> list = new ArrayList<PurchaseInfoDTO>();
		con = getConnection();
		sql = " select g.goods_num, goods_main_store, goods_name "
				+ "   ,pl.purchase_num, purchase_Status, member_Num, purchase_Price  "
				+ "   ,confirmNumber, APPLDATE"
				+ "	  ,DELIVERY_NUM , DELIVERY_STATE"
				+ "   ,review_num "
			+ " from goods g join purchase_list pl "
			+ " on g.goods_num = pl.goods_num join purchase p"
			+ " on pl.purchase_num = p.purchase_num left outer join payment pm "
			+ " on p.purchase_num = pm.purchase_num left outer join delivery d"
			+ " on p.purchase_num = d.purchase_num left outer join reviews r"
			+ " on pl.purchase_num = r.purchase_num and pl.goods_num = r.goods_num ";
		if(memberNum != null) {
			sql += " where member_num = ? ";
		}
		try {
			pstmt = con.prepareStatement(sql);
			if(memberNum != null) {
				pstmt.setString(1, memberNum);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PurchaseInfoDTO dto = new PurchaseInfoDTO();
				dto.setPurchaseNum(rs.getLong("purchase_num"));
				dto.setConfirmNum(rs.getString("confirmNumber"));
				dto.setPurchaseStatus(rs.getString("purchase_Status"));
				dto.setGoodsImage(rs.getString("goods_main_store"));
				dto.setGoodsName(rs.getString("goods_name"));
				dto.setGoodsNum(rs.getString("goods_num"));
				dto.setMemberNum(rs.getString("member_num"));
				dto.setApplDate(rs.getString("applDate"));
				dto.setPurchasePrice(rs.getLong("purchase_Price"));
				dto.setDeliveryNum(rs.getLong("DELIVERY_NUM"));
				dto.setDeliveryState(rs.getString("DELIVERY_STATE"));
				dto.setReviewNum(rs.getInt("review_num"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
		return list;
	}
	public void paymentInsert(PaymentDTO dto) {
		con = getConnection();
		sql = " insert into payment(purchase_Num,confirmNumber,cardNum,TID"
				+ "                ,totalPrice,resultMessage,payMethod,applDate"
				+ "                ,applTime,purchaseName )"
			+ " values(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getConfirmNumber());
			pstmt.setString(3, dto.getCardNum());
			pstmt.setString(4, dto.getTid());
			pstmt.setString(5, dto.getTotalPrice());
			pstmt.setString(6, dto.getResultMessage());
			pstmt.setString(7, dto.getPayMethod());
			pstmt.setString(8, dto.getApplDate());
			pstmt.setString(9, dto.getApplTime());
			pstmt.setString(10, dto.getPurchaseName());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삽입되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
	}
	public void purchaseStatusUpdate(String purchaseNum) {
		con = getConnection();
		sql = " update purchase "
			+ " set purchase_status = '구매확정' "
			+ " where purchase_Num = ? " ;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
	}
	public void paymentDelete(String purchaseNum) {
		con = getConnection();
		sql = " delete from payment where purchase_Num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}









