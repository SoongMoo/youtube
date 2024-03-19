package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.GoodsDTO;

public class GoodsDAO extends DataBaseInfo {
	public void goodsInsert(GoodsDTO dto) {
		con = getConnection();
		sql = " insert into goods (goods_Num, goods_name, goods_price"
			+ "                   ,goods_content,delivery_cost,emp_num"
			+ "					  ,goods_regist, visit_count"
			+ " 				  ,goods_main_store, goods_main_store_img "
			+ " 				  ,goods_images, goods_images_img "
			+ "                   ) "
			+ " values(?,?,?,?,?,?,sysdate,0,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getGoodsNum());
			pstmt.setString(2, dto.getGoodsName());
			pstmt.setInt(3, dto.getGoodsPrice());
			pstmt.setString(4, dto.getGoodsContent());
			pstmt.setInt(5, dto.getDeliveryCost());
			pstmt.setString(6, dto.getEmpNum());
			pstmt.setString(7, dto.getGoodsMainStore());
			pstmt.setString(8, dto.getGoodsMainStoreImg());
			pstmt.setString(9, dto.getGoodsImages());
			pstmt.setString(10, dto.getGoodsImagesImg());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개행이(가) 삽입되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
	}
	public String goodsAutoNum() {
		String goodsNum = null;
		con = getConnection();
		sql = " select concat('hk' , nvl(max(substr(goods_num,3)),100000) + 1) from goods ";
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
	
	public List<GoodsDTO> goodsSelectList(String goodsWord) {
		List<GoodsDTO> list = new ArrayList<GoodsDTO>();
		String search = "";
		if(goodsWord != null) {
			search = " and goods_name like '%" + goodsWord + "%'";
		}
		con = getConnection();
		sql = " select goods_num, goods_name, goods_price, goods_content "
			+ "       ,delivery_cost,visit_count, emp_num, goods_regist "
			+ "       ,update_emp_num, goods_update_date "
			+ "       ,goods_main_store,goods_main_store_img "
			+ "       ,goods_images,goods_images_img  "
			+ " from goods "
			+ " where 1=1 " + search;
		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				GoodsDTO dto = new GoodsDTO();
				dto.setGoodsNum(rs.getString(1));
				dto.setGoodsName(rs.getString(2));
				dto.setGoodsPrice(rs.getInt(3));
				dto.setGoodsContent(rs.getString(4));
				dto.setDeliveryCost(rs.getInt(5));
				dto.setEmpNum(rs.getString(7));
				dto.setGoodsMainStore(rs.getString("goods_main_store"));
				dto.setGoodsMainStoreImg(rs.getString("goods_main_store_img"));
				dto.setGoodsImages(rs.getString("goods_images"));
				dto.setGoodsImagesImg(rs.getString("goods_images_img"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}
	
	
	public GoodsDTO goodsSelectOne(String goodsNum) {
		GoodsDTO dto = null;
		con = getConnection();
		sql = " select goods_num, goods_name, goods_price, goods_content "
				+ "       ,delivery_cost,visit_count, emp_num, goods_regist "
				+ "       ,update_emp_num, goods_update_date "
				+ "       ,goods_main_store,goods_main_store_img "
				+ "       ,goods_images,goods_images_img  "
				+ " from goods "
				+ " where goods_num = ? " ;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new GoodsDTO();
				dto.setGoodsNum(rs.getString(1));
				dto.setGoodsName(rs.getString(2));
				dto.setGoodsPrice(rs.getInt(3));
				dto.setGoodsContent(rs.getString(4));
				dto.setDeliveryCost(rs.getInt(5));
				dto.setEmpNum(rs.getString(7));
				dto.setVisitCount(rs.getInt(6));
				dto.setGoodsRegist(rs.getDate(8));
				dto.setUpdateEmpNum(rs.getString(9));
				dto.setGoodsUpdateDate(rs.getDate(10)); 
				dto.setGoodsMainStore(rs.getString("goods_main_store"));
				dto.setGoodsMainStoreImg(rs.getString("goods_main_store_img"));
				dto.setGoodsImages(rs.getString("goods_images"));
				dto.setGoodsImagesImg(rs.getString("goods_images_img"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public void goodsUpdate(GoodsDTO dto) {
		con = getConnection();
		sql = " update goods "
			+ " set GOODS_NAME = ?, GOODS_PRICE = ? "
			+ "		,delivery_cost = ?,GOODS_CONTENT = ?"
			+ "     ,update_emp_num = ?, goods_update_date = sysdate"
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
	public int goodsDelete(String goodsNum) {
		con = getConnection();
		sql = " delete from goods "
			+ " where  goods_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, goodsNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개 행이(가) 수정되었습니다.");
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {close();}		
	}
	public void visitCount(String goodsNum) {
		con = getConnection();
		sql = " update goods "
			+ " set VISIT_COUNT = VISIT_COUNT + 1 "
			+ " where GOODS_NUM = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			int i = pstmt.executeUpdate();
			System.out.println("조회수가 1증가했습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}		
		
	}
}








