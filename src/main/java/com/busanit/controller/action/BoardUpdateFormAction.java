package com.busanit.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.BoardDAO;
import com.busanit.controller.DTO.BoardVO;

public class BoardUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardUpdate.jsp";
		
		String num = request.getParameter("num");	// 게시글 번호
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.updateReadCount(num);	// 게시글 조회수 증가
		BoardVO bVo = bDao.selectOneBoardByNum(num);	// 해당 게시글 정보 조회 
		
		request.setAttribute("board", bVo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
