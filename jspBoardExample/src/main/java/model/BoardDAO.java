package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	String jdbcURL;
	String jdbcDriver;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;
	public BoardDAO() {
		jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		jdbcURL ="jdbc:oracle:thin:@localhost:1521:xe";
	}
	// 오라클 서버의 접속정보를 가져오기 위한 메서드
	public Connection getConnection() {
		Connection co = null;
		try {
			Class.forName(jdbcDriver);
			co = DriverManager.getConnection(jdbcURL, "rhee", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return co;
	}
	public void boardInsert(BoardDTO dto) {
		con = getConnection();
		sql = " insert into board( BOARD_NUM , BOARD_WRITER,BOARD_SUBJECT, BOARD_CONTENT) "
				+ " values((select nvl(max(board_num),0) + 1 from board),?,?,?) ";
		// 번호는 1씩 증가하는 값을 가져오기 위해서 서브쿼리를 사용했다.
		
		try {
			pstmt = con.prepareStatement(sql);
			// ?를 PreparedStatement 구문이라고 하고 ?에 값을 대입하수 있게 한다.
			pstmt.setString(1, dto.getBoardWriter());
			pstmt.setString(2, dto.getBoardSubject());
			pstmt.setString(3, dto.getBoardContent());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개행(이)가 삽입되었습니다.");
			//insert를 하려면 테이블을 만들어야 한다.
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public List<BoardDTO> boardSelectAll() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		con = getConnection();
		sql = " select BOARD_NUM, BOARD_WRITER, BOARD_SUBJECT, BOARD_CONTENT "
				+ " from board";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoardNum(rs.getInt("BOARD_NUM"));
				dto.setBoardWriter(rs.getString("BOARD_WRITER"));
				dto.setBoardSubject(rs.getString("BOARD_SUBJECT"));
				dto.setBoardContent(rs.getString("BOARD_CONTENT"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public BoardDTO boardSelectOne(String boardNum) {
		BoardDTO dto = null;
		con = getConnection();
		sql = " select BOARD_NUM, BOARD_WRITER, BOARD_SUBJECT, BOARD_CONTENT "
				+ " from board "
			+ " where BOARD_NUM = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setBoardNum(rs.getInt("BOARD_NUM"));
				dto.setBoardWriter(rs.getString("BOARD_WRITER"));
				dto.setBoardSubject(rs.getString("BOARD_SUBJECT"));
				dto.setBoardContent(rs.getString("BOARD_CONTENT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public void boardUpdate(BoardDTO dto) {
		con = getConnection();
		sql = " update board "
				+ " set board_writer = ? ,"
				+ "		board_subject = ? ,"
				+ "     board_content = ? "
				+ " where board_num = ? ";
		
		try {
			pstmt = con.prepareStatement(sql);
			// 여기도 수정 코드나 insert 코드는 같다.
			pstmt.setString(1, dto.getBoardWriter());
			pstmt.setString(2, dto.getBoardSubject());
			pstmt.setString(3, dto.getBoardContent());
			// 추가
			pstmt.setInt(4, dto.getBoardNum());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개행(이)가 수정되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void boardDelete(String boardNum) {
		con = getConnection();
		sql = "delete from board "
				+ " where  board_num = ? ";
		try {
			pstmt = con .prepareStatement(sql);
			pstmt.setString(1, boardNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개행이(가) 삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 게시판  CRUD를 마무리하겠습니다.
	
	
	
	
	
	
}
