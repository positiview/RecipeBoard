package com.busanit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBManager;

/**
 * Servlet implementation class CheckedIdServlet
 */
@WebServlet("/checkNick")
public class CheckcNickServlet extends HttpServlet {
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
		
		String inputNick = request.getParameter("inputNick");
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			conn = DBManager.getConnection();
			String sql = "select nick from joinmember where nick = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputNick);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				 if(inputNick.equals(rs.getString("nick"))) {
					out.println("이미 존재하는 닉네임 입니다.");
				}
			}else {
				out.println("해당 닉네임은 사용가능 합니다.");
			}
			
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}finally {
			DBManager.close(conn, pstmt , rs);
		}
		
		
	}

}
