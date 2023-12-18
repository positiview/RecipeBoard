package com.busanit.controller.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.busanit.controller.DAO.MemberDAO;

import util.DBManager;

public class DeleteIdFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		System.out.println("userId : " +userId+ " userPw : "+userPw);
		MemberDAO mDao = MemberDAO.getInstance();
		int resultCount = 0;
		
		if(userPw.equals(mDao.checkMemberPw(userId))){
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DBManager.getConnection();
				String sql = "delete from joinMember where id = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				resultCount = pstmt.executeUpdate();
				
				HttpSession session = request.getSession();
				if(resultCount > 0) {
					session.invalidate();
				}
				
			} catch (Exception e) {
				e.getMessage();
			}finally {
				DBManager.close(conn, pstmt);
			}
		}
		
		System.out.println("resultcount : " + resultCount);
		String url = "/board/deleteId_process.jsp";
		request.setAttribute("resultCount", resultCount);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
