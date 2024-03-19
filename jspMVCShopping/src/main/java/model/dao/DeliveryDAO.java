package model.dao;

import java.sql.SQLException;

import model.dto.DeliveryDTO;

public class DeliveryDAO extends DataBaseInfo{
	public void deliveryInsert(String purchaseNum,String deliveryNum) {
		con = getConnection();
		sql = " merge into DELIVERY d"
			+ " using ( select purchase_num from purchase where purchase_num = ?) p" 
			+ " on (d.purchase_num = p.purchase_num)"
			+ " WHEN MATCHED THEN "
			+ " update set DELIVERY_NUM = ? "
			+ " WHEN NOT MATCHED THEN "
			+ " insert (PURCHASE_NUM, DELIVERY_NUM, DELIVERY_DATE, DELIVERY_STATE )"
			+ " values (p.purchase_num, ?, sysdate , '배송중') ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			pstmt.setString(2, deliveryNum);
			pstmt.setString(3, deliveryNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 병합되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
	}
	public void deliveryStateUpdate(String purchaseNum) {
		con = getConnection();
		sql = " update delivery "
			+ " set DELIVERY_STATE = '배송완료'"
			+ " where purchase_Num  = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
		
	}
	public DeliveryDTO  deliverySelectOne(String purchaseNum) {
		DeliveryDTO dto = null;
		con = getConnection();
		sql = " select PURCHASE_NUM,DELIVERY_NUM,DELIVERY_DATE,DELIVERY_STATE "
			+ " from delivery "
			+ " where PURCHASE_NUM = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new DeliveryDTO();
				dto.setDeliveryDate(rs.getDate("DELIVERY_DATE"));
				dto.setDeliveryNum(rs.getString("DELIVERY_NUM"));
				dto.setDeliveryState(rs.getString("DELIVERY_STATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
		return dto;
	}
}











