package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.CartDTO;
import model.dto.CartListDTO;
import model.dto.PaymentDTO;
import model.dto.PurchaseDTO;
import model.dto.PurchaseListDTO;
import model.dto.WishListDTO;

public class ItemDAO extends DataBaseInfo{
	public int goodsSelect(String goodsNum, String memberNum) {
		int i = 0;
		con = getConnection();
		sql = "select * from wish where goods_num=? and member_num =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			pstmt.setString(2, memberNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				i = 1; // 출력결과가 있다면 i에 1을 대입한다.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return i;
	}
	public void wishGoodsDelete(String goodsNum, String memberNum) {
		con = getConnection();
		sql = " delete from wish"
			+ " where goods_num=? and member_num =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			pstmt.setString(2, memberNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public void wishGoodsInsert(String goodsNum, String memberNum) {
		con = getConnection();
		sql = " insert into wish (goods_num, member_num , wish_date )"
			+ " values(?,?,now())";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			pstmt.setString(2, memberNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	public List<WishListDTO> wishListSelect(String memberNum){
		List<WishListDTO> list = new ArrayList<WishListDTO>();
		con = getConnection();
		sql = " select g.goods_num, goods_Main_Store, goods_name, goods_price "
		    + "	      , wish_date"	
		    + " from goods g join wish w"
		    + " on g.goods_num = w.goods_num "
		    + " where member_num = ?"; 
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
		}finally {
			close();
		}
		return list;
	}
	public void cartInsert(CartDTO dto) {
		con = getConnection();
		//merge를 사용해서 장바구니에 있으면 추가를하고 없으면 삽입하도록 하겠습니다.
		sql  = " merge into cart c "; // 상품번호에 해당하는 정보를 상품테이블에서 가져옵니다.  
		sql += " using (select goods_num from goods where goods_num = ? ) g" ;
		sql += " on (c.goods_num = g.goods_num and c.member_num = ? ) "; //두개를 비교합니다.
		sql += " WHEN MATCHED THEN "; // 두 테이블에서 같은게 있다면 
		sql += " update set cart_qty = cart_qty + ? "; // 있는 수량에 가지고온 수량을 더합니다.
		sql += " WHEN NOT MATCHED THEN"; // 일치하는게 없다면...
		sql += " insert ( MEMBER_NUM, GOODS_NUM, CART_DATE, CART_QTY) "; // 삽입합니다.
		sql += " values ( ?, ?, now() , ?) ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getGoodsNum());
			pstmt.setString(2, dto.getMemberNum());
			pstmt.setInt(3, dto.getCartQty());
			pstmt.setString(4, dto.getMemberNum());
			pstmt.setString(5, dto.getGoodsNum());
			pstmt.setInt(6, dto.getCartQty());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삽입되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public List<CartListDTO> cartList(String memberNum) {
		List<CartListDTO> list = new ArrayList<CartListDTO>();
		con = getConnection();
		sql  = "select MEMBER_NUM, c.GOODS_NUM, CART_QTY, CART_DATE";
		sql += "    ,goods_name, goods_price * CART_QTY total_price ,goods_main_store";
		sql += " ,goods_price"; //추가해준다
		sql += " from cart c, goods g";
		sql += " where c.GOODS_NUM = g.GOODS_NUM ";
		sql += " and MEMBER_NUM = ?";
		sql += " order by c.GOODS_NUM desc";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CartListDTO dto = new CartListDTO();
				dto.setCartDate(rs.getDate("CART_DATE"));
				dto.setCartQty(rs.getInt("CART_QTY"));
				dto.setGoodsName(rs.getString("goods_name"));
				dto.setGoodsNum(rs.getString("GOODS_NUM"));
				dto.setMemberNum(rs.getString("MEMBER_NUM"));
				dto.setTotalPrice(rs.getInt("total_price"));
				dto.setGoodsImage(rs.getString("goods_main_store"));
				dto.setGoodsPrice(rs.getInt("goods_price"));  //추가해준다
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}	
		return list;
	}
	public void itemDelete(String memberNum, String goodNum) {
		con = getConnection();
		sql = " delete from cart where member_Num=? and goods_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberNum);
			pstmt.setString(2, goodNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삭되었습니다.");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public void itemQtyDown(String goodsNum, String memberNum) {
		con = getConnection();
		sql = " update cart set cart_qty = cart_qty - 1 where  MEMBER_NUM = ? and GOODS_NUM = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberNum);
			pstmt.setString(2, goodsNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	public CartListDTO selectOne(String memberNum, String goodsNum) {
		CartListDTO dto = new CartListDTO();
		con = getConnection();
		sql  = "select MEMBER_NUM, c.GOODS_NUM, CART_QTY, CART_DATE";
		sql += "    ,goods_name, goods_price * CART_QTY total_price ,goods_main_store";
		sql += " ,goods_price , delivery_Cost"; //추가
		sql += " from cart c, goods g";
		sql += " where c.GOODS_NUM = g.GOODS_NUM ";
		sql += " and MEMBER_NUM = ?";
		sql += " and g.goods_num = ?";
		sql += " order by c.GOODS_NUM desc";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberNum);
			pstmt.setString(2, goodsNum); //추가
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setCartDate(rs.getDate("CART_DATE"));
				dto.setCartQty(rs.getInt("CART_QTY"));
				dto.setGoodsName(rs.getString("goods_name"));
				dto.setGoodsNum(rs.getString("GOODS_NUM"));
				dto.setMemberNum(rs.getString("MEMBER_NUM"));
				dto.setTotalPrice(rs.getInt("total_price"));
				dto.setGoodsImage(rs.getString("goods_main_store"));
				dto.setGoodsPrice(rs.getInt("goods_price"));  
				dto.setDeliveryCost(rs.getInt("delivery_Cost")); //추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}	
		return dto;
	}
	public void purchaseInsert(PurchaseDTO dto) {
		con = getConnection();
		sql = " insert into purchase(purchase_Num, purchase_Date, delivery_Name, delivery_Phone"
				+ "                     ,delivery_Addr, delivery_Addr_Detail, delivery_Post"
				+ "                     ,message, purchase_Status, member_Num,  PURCHASE_PRICE )"
				+ " values              (?, now(), ?, ?, ?, ?, ?, ?, '결제대기중', ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getDeliveryName());
			pstmt.setString(3, dto.getDeliveryPhone());
			pstmt.setString(4, dto.getDeliveryAddr());
			pstmt.setString(5, dto.getDeliveryAddrDetail());
			pstmt.setString(6, dto.getDeliveryPost());
			pstmt.setString(7, dto.getMessage());
			pstmt.setString(8, dto.getMemberNum());
			pstmt.setLong(9, dto.getPurchasePrice());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개 행이(가) 삽입되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public int purchaseListInsert(PurchaseListDTO dto, String memberNum) {
		int i = 0;
		con = getConnection();
		sql = " insert into purchase_list(PURCHASE_NUM, GOODS_NUM, PURCHASE_QTY, total_price) "
				+ " select ?, c.GOODS_NUM, cart_qty, cart_qty * goods_price " + " from cart c join goods g "
				+ " on c.goods_num=g.goods_num " + " where c.goods_num = ? and member_num = ? ";
		// 구매 상품 정보가 장바구니에 있어서 장바구니로 부터 구매상품 정보를 가져 왔습니다.
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum()); // 구매번호
			pstmt.setString(2, dto.getGoodsNum()); // 성품번호
			pstmt.setString(3, memberNum); // 회원 번호
			i = pstmt.executeUpdate();
			System.out.println(i + "개가 삽입되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return i;
	}
	public void cartItemDelete(String goodsNum, String memberNum) {
		con = getConnection();
		sql = " delete from cart where MEMBER_NUM = ? and GOODS_NUM = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberNum);
			pstmt.setString(2, goodsNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public PurchaseDTO purchaseSelect(String purchaseNum) {
		PurchaseDTO dto = new PurchaseDTO();
		con = getConnection();
		sql = " select purchase_num,purchase_price, delivery_name  from purchase  where purchase_num = ?";
		             //구매번호         구매금액         받는 사람
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			rs = pstmt.executeQuery();
			rs.next();
			dto.setDeliveryName(rs.getString("delivery_name"));
			dto.setPurchasePrice(rs.getLong("purchase_price"));
			dto.setPurchaseNum(rs.getString("purchase_num"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
	}
	public void paymentInsert(PaymentDTO dto) {
		con = getConnection();
		sql = " insert into payment(purchase_Num,confirmNumber, cardNum, TID"
				+ "					  , totalPrice, resultMessage, payMethod, applDate"
				+ "					  , applTime, purchaseName)" + "	values(?,?,?,?,?,?,?,?,?,?)";
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
		} catch (SQLException e) {e.printStackTrace();
		}finally {
			close();
		}
	}
	
}

















