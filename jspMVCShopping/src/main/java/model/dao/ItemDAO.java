package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}

















