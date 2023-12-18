package com.busanit.controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.busanit.controller.DTO.CommentVO;

import util.DBManager;

public class CommentDAO {
	
	private static CommentDAO instance = new CommentDAO();
	
	public static CommentDAO getInstance() {
		return instance;
	}
	
	// 댓글 전체 리스트 조회
	public List<CommentVO> selectCommentsList(String num) {
		String sql = "SELECT * FROM COMMENT WHERE NUM = ? ORDER BY COMMENT_NUM DESC";
		
		List<CommentVO> list = new ArrayList<CommentVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentVO cVo = new CommentVO();
				
				cVo.setCommentNum(rs.getInt("comment_num"));
				cVo.setNum(rs.getInt("num"));
				cVo.setCommentName(rs.getString("comment_name"));
				cVo.setCommentPass(rs.getString("comment_pass"));
				cVo.setCommentDate(rs.getTimestamp("comment_date"));
				cVo.setCommentContent(rs.getString("comment_content"));
				
				list.add(cVo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	// 댓글 등록
	public void insertComment(CommentVO cVo) {
		String sql = "INSERT INTO COMMENT(NUM, COMMENT_NAME, COMMENT_PASS, COMMENT_CONTENT) "
				+ "VALUES(?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cVo.getNum());
			pstmt.setString(2, cVo.getCommentName());
			pstmt.setString(3, cVo.getCommentPass());
			pstmt.setString(4, cVo.getCommentContent());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 댓글 수정
	public void updateComment(CommentVO cVo) {
		String sql = "UPDATE COMMENT SET COMMENT_NAME = ?, COMMENT_PASS = ?, "
						+ "COMMENT_CONTENT = ? WHERE COMMENT_NUM = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cVo.getCommentName());
			pstmt.setString(2, cVo.getCommentPass());
			pstmt.setString(3, cVo.getCommentContent());
			pstmt.setInt(4, cVo.getCommentNum());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 댓글 비밀번호 check
	public boolean checkPassword(String commentPass, int commentNum) {
		String sql = "SELECT * FROM COMMENT WHERE COMMENT_PASS = ? AND COMMENT_NUM = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// DB에 저장된 password와 동일한지 여부 check(true : 패스워드 일치 / false : 패스워드 불일치)
		boolean isPasswordCheck = false;
				
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, commentPass);
			pstmt.setInt(2, commentNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	// 패스워드 일치
				isPasswordCheck = true;
			} else {		// 패스워드 불일치
				isPasswordCheck = false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return isPasswordCheck;
	}
	
	// 댓글 삭제
	public void deleteComment(String commentNum) {
		String sql = "DELETE FROM COMMENT WHERE COMMENT_NUM = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, commentNum);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 전체 댓글 수
	public int selectCommentCount(String num) {
		String sql = "SELECT COUNT(*) FROM COMMENT WHERE NUM = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int commentCnt = 0;
				
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	
				commentCnt = rs.getInt(1);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return commentCnt;
	}
}













