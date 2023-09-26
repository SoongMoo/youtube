package model.dao;

import java.sql.SQLException;

import model.dto.AuthInfoDTO;
import model.dto.MemberDTO;

public class UserDAO extends DataBaseInfo{
	public void userInsert(MemberDTO dto) {
		con = getConnection();
		// 회원번호를 자동으로 부여 받기
		String memNum = "select concat('mem', coalesce(max(substr(member_num, 4))::integer,100000) + 1)"
					  + " from members";
		sql = " insert into members(member_num,MEMBER_ID,MEMBER_PW"
				+ "                    ,MEMBER_NAME, MEMBER_PHONE1"
				+ "                    ,MEMBER_PHONE2,MEMBER_ADDR "
				+ "					   ,member_addr_detail,member_post"
				+ "                    ,GENDER,MEMBER_EMAIL"
				+ "                    ,MEMBER_BIRTH,Member_regist"
				+ "					   ,point) "
				+ " values((" + memNum + ")"
				+ ",?,?,?,?,?,?,?,?,?,?,?,now(),0)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMemberId());
			pstmt.setString(2, dto.getMemberPw());
			pstmt.setString(3, dto.getMemberName());
			pstmt.setString(4, dto.getMemberPhone1());
			pstmt.setString(5, dto.getMemberPhone2());
			pstmt.setString(6, dto.getMemberAddr());
			pstmt.setString(7, dto.getMemberAddrDetail());
			pstmt.setString(8, dto.getMemberPost());
			pstmt.setString(9, dto.getMemberGender());
			pstmt.setString(10, dto.getMemberEmail());
			pstmt.setDate(11, new java.sql.Date(dto.getMemberBirth().getTime()));
			int i = pstmt.executeUpdate();
			System.out.println(i + "개행이(가) 삽입되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public AuthInfoDTO loginSelect(String userId) {
		AuthInfoDTO dto = null;
		con = getConnection();
		sql = " select member_id user_id, member_pw user_pw, member_name user_name, 'mem' grade"
				+ " from members"
				+ " where member_id = ? "
				+ " union "
				+ " select emp_id, emp_pw, emp_name, 'emp'"
				+ " from employees"
				+ " where emp_id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new  AuthInfoDTO();
				dto.setGrade(rs.getString("grade"));
				dto.setUserId(rs.getString("user_id"));
				dto.setUserName(rs.getString("user_name"));
				dto.setUserPw(rs.getString("user_pw"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
	}
}








