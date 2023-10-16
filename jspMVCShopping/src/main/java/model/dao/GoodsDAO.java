package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.GoodsDTO;

public class GoodsDAO extends DataBaseInfo{
	public void goodsDelete(String goodsNum) {
		con = getConnection();
		sql = " delete from goods where goods_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개 행이(가) 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
	}
	public void visitCount(String goodsNum) {
		con = getConnection();
		sql = " update goods "
			+ " set visit_count = visit_count + 1"
			+ " where goods_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개 행이(가) 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
	}
	public void goodsUpdate(GoodsDTO dto) {
		con = getConnection();
		sql = " update goods "
				+ " set GOODS_NAME = ?, GOODS_PRICE = ?"
				+ "    ,delivery_cost =?, GOODS_CONTENT = ?"
				+ "    ,update_emp_num = ?, goods_update_date = now()"
				+ " where goods_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getGoodsName());
			pstmt.setInt(2, dto.getGoodsPrice());
			pstmt.setInt(3,dto.getDeliveryCost());
			pstmt.setString(4, dto.getGoodsContent());
			pstmt.setString(5, dto.getUpdateEmpNum());
			pstmt.setString(6, dto.getGoodsNum());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개 행이(가) 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
	}
	public GoodsDTO selectOne(String goodsNum) {
		GoodsDTO dto = null;
		con = getConnection();
		sql = " select goods_num, goods_name, goods_price"
				+ "       ,goods_content, goods_main_store"
				+ "       ,goods_main_store_img,goods_images"
				+ "       ,goods_images_img, delivery_cost"
				+ "       ,visit_count, emp_num, goods_regist"
				+ "       ,update_emp_num, goods_update_date"
				+ " from goods "
				+ " where goods_num = ? ";
		// 이미지는 아직 사용 안하지만 일단 모든 내용을 받아오도록 하자
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			rs = pstmt.executeQuery();
			rs.next();
			dto = new GoodsDTO();
			dto.setGoodsNum(rs.getString(1));
			dto.setGoodsName(rs.getString(2));
			dto.setGoodsPrice(rs.getInt(3));
			dto.setGoodsContent(rs.getString(4));
			dto.setGoodsMainStore(rs.getString(5));
			dto.setGoodsMainStoreImg(rs.getString(6));
			dto.setGoodsImages(rs.getString(7));
			dto.setGoodsImagesImg(rs.getString(8));
			dto.setDeliveryCost(rs.getInt(9));
			dto.setVisitCount(rs.getInt(10));
			dto.setEmployeeNum(rs.getString(11));
			dto.setGoodsRegist(rs.getDate(12));
			dto.setUpdateEmpNum(rs.getString(13));
			dto.setGoodsUpdateDate(rs.getDate(14)); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
	}
	public List<GoodsDTO> allSelect(){
		List<GoodsDTO> list = new ArrayList<GoodsDTO>();
		con = getConnection();
		sql = " select goods_num, goods_name, goods_price"
				+ "       ,goods_content, goods_main_store"
				+ "       ,goods_main_store_img,goods_images"
				+ "       ,goods_images_img, delivery_cost"
				+ "       ,visit_count, emp_num, goods_regist"
				+ "       ,update_emp_num, goods_update_date"
				+ " from goods ";	
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				GoodsDTO dto = new GoodsDTO();
				dto.setGoodsNum(rs.getString(1));
				dto.setGoodsName(rs.getString(2));
				dto.setGoodsPrice(rs.getInt(3));
				dto.setGoodsContent(rs.getString(4));
				dto.setGoodsMainStore(rs.getString(5));
				dto.setGoodsMainStoreImg(rs.getString(6));
				dto.setGoodsImages(rs.getString(7));
				dto.setGoodsImagesImg(rs.getString(8));
				dto.setDeliveryCost(rs.getInt(9));
				dto.setVisitCount(rs.getInt(10));
				dto.setEmployeeNum(rs.getString(11));
				dto.setGoodsRegist(rs.getDate(12));
				dto.setUpdateEmpNum(rs.getString(13));
				dto.setGoodsUpdateDate(rs.getDate(14));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}
	public String goodsAutoNum() {
		String goodsNum = "";
		con = getConnection();
		sql = " select "
			+ " concat('gd' , coalesce(max(substr(goods_num,3))::integer,1000000) + 1)"
			+ " from goods";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			goodsNum = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
		return goodsNum;
	}
	
	public void goodsInsert(GoodsDTO dto) {
		con = getConnection();
		sql = " insert into goods ( "
				+ " goods_Num, goods_name, goods_price, goods_content,"
				+ " delivery_cost, emp_num, goods_regist , "
				+ " goods_main_store, goods_main_store_img, "
				+ " goods_images, goods_images_img, visit_count "
				+ " ) "
				+ " values(?, ?, ?, ?, ?, ?, now(), "
				+ "        '1','1','1','1',0)";
		//아직 이미지를 저장하는 것이 아니므로 임의의 값을 저장하기로 한다.
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getGoodsNum());
			pstmt.setString(2, dto.getGoodsName());
			pstmt.setInt(3, dto.getGoodsPrice());
			pstmt.setString(4, dto.getGoodsContent());
			pstmt.setInt(5, dto.getDeliveryCost());
			pstmt.setString(6, dto.getEmployeeNum());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개행이(가) 삽입되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
}
