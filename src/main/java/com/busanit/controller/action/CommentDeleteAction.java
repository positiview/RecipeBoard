package com.busanit.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.CommentDAO;

public class CommentDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시판 페이징, 검색 정보 저장
		String currPage = request.getParameter("currPage");
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		
		String commentNum = request.getParameter("commentNum");
		String deletePass = request.getParameter("deletePass");
		
		CommentDAO cDao = CommentDAO.getInstance();
		boolean isPasswordCheck = cDao.checkPassword(deletePass, Integer.parseInt(commentNum));
		
		if(isPasswordCheck) {	// 패스워드 일치 시 댓글 삭제 진행
			// DB에서 댓글 삭제
			cDao.deleteComment(commentNum);
		}
		
		// 댓글 패스워드 일치 여부 request에 설정
		request.setAttribute("isPasswordCheck", isPasswordCheck);
		
		// 게시판 페이징, 검색 정보 request에 설정
		request.setAttribute("currPage", currPage);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchText", searchText);
		
		// 삭제 후 게시글 상세 페이지로 이동
		new BoardViewAction().execute(request, response);
	}
}












