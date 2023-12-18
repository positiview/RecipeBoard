package com.busanit.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.BoardDAO;
import com.busanit.controller.DTO.BoardVO;
import com.busanit.controller.DTO.FilterVO;
import com.busanit.controller.DTO.SearchVO;

import util.PageHandler;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardList.jsp";
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		// 현재 페이지번호 가져옴
		int currPage = 0;
		String currPageStr = request.getParameter("currPage");
		if(currPageStr == null || currPageStr.equals("")) {
			currPage = 1;
		} else {
			currPage = Integer.parseInt(currPageStr);					
		}
		
		// 화면에 보여줄 게시물 갯수 가져옴
		int pageSize = 0;
		String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr == null) {
			pageSize = 10;
		} else {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		// 검색 파라미터 설정
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		SearchVO searchVO = new SearchVO();
		searchVO.setSearchType(searchType);
		searchVO.setSearchText(searchText);
		
		// 검색 필터 설정
		String filterType = request.getParameter("filterType");
		FilterVO filterVO = new FilterVO();
		filterVO.setFilterType(filterType);
		
		// 전체 게시글 수
		int totalCnt = bDao.selectAllBoardsCount(searchType, searchText);
		
		// 페이징 관련값 자동 계산
		PageHandler pageHandler = new PageHandler(totalCnt, currPage);
		
		// 페이지 시작값 계산
		int offset = (currPage - 1) * pageSize;
		
		// 페이징 리스트 가져옴
		List<BoardVO> boardList = bDao.selectBoardsPaging(offset, pageSize, searchType, searchText, filterType);
		
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("filterVO", filterVO);
		request.setAttribute("pageHandler", pageHandler);
		request.setAttribute("searchVO", searchVO);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
