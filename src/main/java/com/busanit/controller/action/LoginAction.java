package com.busanit.controller.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.LogDAO;

import util.DBManager;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramId =request.getParameter("id");
		String paramPw =request.getParameter("pw");
		String nick=null;
		
		boolean isLogin = false; //로그인 성공여부 체크
		
		//Member selectMember = new Member();
		LogDAO ldao = LogDAO.getInstance();
		
		isLogin= ldao.checkIdPw(paramId, paramPw);
		nick=ldao.getNick(paramId);
		/*
		 * try { conn = DBManager.getConnection();
		 * 
		 * String Sql = "SELECT id,pw,nick FROM joinMember WHERE id = ?"; pstmt =
		 * conn.prepareStatement(Sql); pstmt.setString(1, paramId); rs =
		 * pstmt.executeQuery();
		 * 
		 * 
		 * if(rs.next()) { String dbId = rs.getString("id"); String dbPw =
		 * rs.getString("pw"); nick=rs.getString("nick");
		 * if(paramId.equals(dbId)&&paramPw.equals(dbPw)) { isLogin = true; }else {
		 * isLogin = false; } }else { isLogin = false; } } catch(Exception e) {
		 * System.out.println("Exception : " + e.getMessage()); } finally {
		 * DBManager.close(conn, pstmt, rs); }
		 */
		
		request.setAttribute("isLogin", isLogin);
		request.setAttribute("userId", paramId);
		request.setAttribute("nick", nick);
		
		String forwardUrl = "/board/Login_process.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardUrl);
		dispatcher.forward(request, response);
	}
	

}



