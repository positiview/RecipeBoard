package com.busanit.controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.busanit.controller.DTO.JoinVO;

import util.DBManager;

public class LogDAO {
	
	
	private LogDAO() {
	}
	
	public static LogDAO instance = new LogDAO();
	
	public static LogDAO getInstance() {
		return instance;	
	}
	
	public List<JoinVO> selectIdBoards() {
	      String sql = "SELECT id,pw FROM joinMember ORDER BY NUM DESC";
	      
	      List<JoinVO> list = new ArrayList<JoinVO>();
	      Connection conn = null;
	      Statement stmt = null;
	      ResultSet rs = null;
	      
	      try {
	         conn = DBManager.getConnection();
	         stmt = conn.createStatement();
	         
	         rs = stmt.executeQuery(sql);
	         
	         while(rs.next()) {
	            JoinVO jVo = new JoinVO();
	            
	            jVo.setId(rs.getString("id"));
	            jVo.setPw(rs.getString("pw"));
	            
	            
	            
	            list.add(jVo);
	         }
	      }catch(SQLException | ClassNotFoundException e) {
	    	  e.printStackTrace();
	      }finally {
	    	  DBManager.close(conn, stmt, rs);
	      }
	      return list;
	}
	
	public boolean checkIdPw(String paramId,String paramPw) {
		boolean match = false;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			
			String Sql = "SELECT id,pw FROM joinMember WHERE id = ?";
			pstmt = conn.prepareStatement(Sql);
			pstmt.setString(1, paramId);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				String dbId = rs.getString("id");
				String dbPw = rs.getString("pw");
				if(paramId.equals(dbId)&&paramPw.equals(dbPw)) {
					match = true;
				}else {
					match = false;
				}
			}else {
				match = false;
			}
		} catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return match;
	}
	public String getNick(String paramId) {
		String nick=null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			
			String Sql = "SELECT nick FROM joinMember WHERE id = ?";
			pstmt = conn.prepareStatement(Sql);
			pstmt.setString(1, paramId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				nick = rs.getString("nick");
				
			}
		} catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nick;
	}
}
