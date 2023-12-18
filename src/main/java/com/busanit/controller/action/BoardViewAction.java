package com.busanit.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.BoardDAO;
import com.busanit.controller.DAO.CommentDAO;
import com.busanit.controller.DTO.BoardVO;
import com.busanit.controller.DTO.CommentVO;
import com.busanit.controller.DTO.SearchVO;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardView.jsp";
	
		// 게시글 글번호
		String num = request.getParameter("num");
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		// 게시글 조회수 증가
		bDao.updateReadCount(num);
		
		// 해당 게시글의 상세 정보 조회
		BoardVO bVo = bDao.selectOneBoardByNum(num);
		
		// 현재 페이지번호 가져옴
		String currPage = request.getParameter("currPage");
		
		// 검색 파라미터
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		SearchVO searchVO = new SearchVO();
		searchVO.setSearchType(searchType);
		searchVO.setSearchText(searchText);
		
		// 게시글의 댓글 
		// 해당 게시글의 댓글 리스트 조회
		CommentDAO cDao = CommentDAO.getInstance();
		List<CommentVO> commentList = cDao.selectCommentsList(num);
		
		// 해당 게시글의 댓글 갯수 조회
		int commentCnt = cDao.selectCommentCount(num);
		
		request.setAttribute("board", bVo);
		request.setAttribute("currPage", currPage);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("commentList", commentList);
		request.setAttribute("commentCnt", commentCnt);
		request.setAttribute("command", "comment_write");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}





