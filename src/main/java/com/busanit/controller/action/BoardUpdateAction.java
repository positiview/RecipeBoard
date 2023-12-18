package com.busanit.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.BoardDAO;
import com.busanit.controller.DTO.BoardVO;

public class BoardUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVO bVo = new BoardVO();
		
		bVo.setNum(Integer.parseInt(request.getParameter("num")));
		bVo.setUserId(request.getParameter("id"));
		bVo.setFood(request.getParameter("food"));
		bVo.setNick(request.getParameter("nick"));
		bVo.setLevel(request.getParameter("level"));
		bVo.setImage(request.getParameter("image"));
		bVo.setTitle(request.getParameter("title"));
		bVo.setContent(request.getParameter("content"));
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.updateBoard(bVo);
		
		// boardList.jsp로 이동
		new BoardListAction().execute(request, response);
	}
}
