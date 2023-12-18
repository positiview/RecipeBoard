package com.busanit.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.CommentDAO;
import com.busanit.controller.DTO.CommentVO;

public class CommentWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시판 페이징, 검색 정보 저장
		String currPage = request.getParameter("currPage");
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		
		// 댓글 정보 VO에 저장
		CommentVO cVo = new CommentVO();
		cVo.setNum(Integer.parseInt(request.getParameter("num")));
		cVo.setCommentName(request.getParameter("commentName"));
		cVo.setCommentPass(request.getParameter("commentPass"));
		cVo.setCommentContent(request.getParameter("commentContent"));
		
		// DB에 댓글 등록
		CommentDAO cDao = CommentDAO.getInstance();
		cDao.insertComment(cVo);
		
		// 게시판 페이징, 검색 정보 request에 설정
		request.setAttribute("currPage", currPage);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchText", searchText);
				
		// insert 후 게시글 상세 페이지로 이동
		new BoardViewAction().execute(request, response);
		}
}






