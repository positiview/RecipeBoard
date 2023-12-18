package com.busanit.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DTO.JoinVO;

import util.DBManager;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/joinMemberDB")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JoinVO joinMember = new JoinVO();
		joinMember.setId(request.getParameter("id"));
		joinMember.setPw(request.getParameter("pw"));
		joinMember.setCheckPw(request.getParameter("checkPw"));
		joinMember.setName(request.getParameter("name"));
		joinMember.setNick(request.getParameter("nick"));
		joinMember.setPhone(request.getParameter("phone"));
		joinMember.setGender(request.getParameter("gender"));
		joinMember.setComment(request.getParameter("comment"));
		
		Connection conn = null;
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		JoinVO selectMember = new JoinVO();
		
		try{
			conn = DBManager.getConnection();
			
			String sql = "INSERT INTO joinMember VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, joinMember.getId());
			pstmt.setString(2, joinMember.getPw());
			pstmt.setString(3, joinMember.getCheckPw());
			pstmt.setString(4, joinMember.getName());
			pstmt.setString(5, joinMember.getNick());
			pstmt.setString(6, joinMember.getPhone());
			pstmt.setString(7, joinMember.getGender());
			pstmt.setString(8, joinMember.getComment());
			
			pstmt.executeUpdate();
			System.out.println("Member 테이블 삽입이 성공했습니다.");
			
			String selectSql = "select * from joinmember where id = ?";
			pstmt = conn.prepareStatement(selectSql);
			pstmt.setString(1, joinMember.getId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				selectMember.setId(rs.getString("id"));
				selectMember.setPw(rs.getString("pw"));
				selectMember.setCheckPw(rs.getString("checkPw"));
				selectMember.setName(rs.getString("name"));
				selectMember.setNick(rs.getString("nick"));
				selectMember.setPhone(rs.getString("phone"));
				selectMember.setGender(rs.getString("gender"));
				selectMember.setComment(rs.getString("comment"));
			}
			
		}catch(SQLException e){
			System.out.println("SQLException : " + e.getMessage());
		}catch(Exception e){
			System.out.println("Exception : " + e.getMessage());
		}finally{
			
			DBManager.close(conn, pstmt, rs);
		}
		
		request.setAttribute("selectMember", selectMember);
		
		String resultUrl = "/board/joinForm_process.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(resultUrl);
		
		dispatcher.forward(request, response);
	}
}
		
		
	
		
		
		

