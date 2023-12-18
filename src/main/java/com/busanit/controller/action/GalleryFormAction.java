package com.busanit.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.BoardDAO;
import com.busanit.controller.DTO.BoardVO;

public class GalleryFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/gallery.jsp";
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		String count = "1000";
		
		List<BoardVO> imageList  = bDao.imageFileNameFinder(count);;
		
		
		
		request.setAttribute("imageList", imageList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		
		dispatcher.forward(request, response);

	}

}
