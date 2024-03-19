package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.ReviewDTO;

public class ReviewDAO extends DataBaseInfo{
	public void reviewInsert(ReviewDTO dto) {
		con = getConnection();
		sql = " merge into reviews r"
			+ " using (select purchase_num, goods_num from purchase_list"
			+ "		   where  purchase_num = ? and goods_num = ? ) p "
			+ " on (r.purchase_num = p.purchase_num and r.goods_num=p.goods_num) "
			+ " WHEN MATCHED THEN "
			+ " update set review_content = ? "
			+ " WHEN NOT MATCHED THEN  "
			+ " insert (purchase_num,goods_num,review_date,score"
			+ "			,review_content,member_id  )"
			+ " values(p.purchase_num, p.goods_num , sysdate, 5 , ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getGoodsNum());
			pstmt.setString(3, dto.getReviewContent());
			pstmt.setString(4, dto.getReviewContent());
			pstmt.setString(5, dto.getMemberId());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 병합되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public ReviewDTO reviewSelectOne(String purchaseNum,String goodsNum) {
		ReviewDTO dto = null;
		con = getConnection();
		sql = " select REVIEW_CONTENT from reviews "
			+ " where purchase_Num = ? and goods_Num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			pstmt.setString(2, goodsNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new ReviewDTO();
				dto.setReviewContent(rs.getString("REVIEW_CONTENT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	public void reviewDelete(String reviewNum) {
		con = getConnection();
		sql = " delete from reviews where review_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reviewNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삭제되었습니다.");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<ReviewDTO> reviewSelectAll(String goodsNum){
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();
		con = getConnection();
		sql = " select REVIEW_NUM, REVIEW_CONTENT , review_date,member_id"
			+ " from reviews"
			+ " where goods_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReviewNum(rs.getInt("REVIEW_NUM"));
				dto.setReviewContent(rs.getString("REVIEW_CONTENT"));
				dto.setMemberId(rs.getString("member_id"));
				dto.setReviewDate(rs.getDate("review_date"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
		
		return list;
	}
}







