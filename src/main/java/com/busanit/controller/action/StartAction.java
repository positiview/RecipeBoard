package com.busanit.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.BoardDAO;
import com.busanit.controller.DTO.BoardVO;

public class StartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/index.jsp";
		String count = "8";
		BoardDAO bDao = BoardDAO.getInstance();
		
		
		
		List<BoardVO> imageList  = bDao.imageFileNameFinder(count);;
		
		
		
		request.setAttribute("imageList", imageList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		
		dispatcher.forward(request, response);
		
		
	}

}
