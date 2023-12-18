package com.busanit.controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.busanit.controller.DTO.JoinVO;

import util.DBManager;

public class MemberDAO {
	private MemberDAO(){
	}
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	// 회원 정보 수정
	public boolean updateMember(JoinVO member) {
		String sql = "UPDATE joinMember SET pw=?, name=?, nick=?, phone=?, gender=?, comment=? WHERE id=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getNick());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getComment());
			pstmt.setString(7, member.getId());

			int memeber = pstmt.executeUpdate();
			if (memeber > 0) {
				result = true; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return result;
	}
	
	// 한 회원 정보 불러오기
	public JoinVO selectMemberDAO(String id) {
		String sql = "SELECT * FROM joinMember WHERE id = ?";
		
		JoinVO mVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mVo = new JoinVO();
				
				mVo.setId(rs.getString("id"));
				mVo.setPw(rs.getString("pw"));
				mVo.setCheckPw(rs.getString("checkPw"));
				mVo.setName(rs.getString("name"));
				mVo.setNick(rs.getString("nick"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setGender(rs.getString("gender"));
				mVo.setComment(rs.getString("comment"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return mVo;
	}
	
	// 회원Id의 비밀번호 일치 확인
	public String checkMemberPw(String userId) {
		String sql = "SELECT pw FROM joinMember WHERE id = ?";
		String pw = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pw = rs.getString("pw");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return pw;
	}
		
}
