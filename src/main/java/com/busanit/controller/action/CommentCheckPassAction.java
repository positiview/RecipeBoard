package com.busanit.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.CommentDAO;

public class CommentCheckPassAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentNum = request.getParameter("commentNum");
		String deletePass = request.getParameter("deletePass");
		
		CommentDAO cDao = CommentDAO.getInstance();
		Boolean isPasswordCheck = cDao.checkPassword(deletePass, Integer.parseInt(commentNum));
		
		// 댓글 패스워드 일치 여부 출력
		PrintWriter out = response.getWriter();
		out.println(isPasswordCheck);
	}
}







