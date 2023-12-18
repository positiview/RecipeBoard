package com.busanit.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.MemberDAO;
import com.busanit.controller.DTO.JoinVO;


public class MemberUpdateFormaction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDAO mDao = MemberDAO.getInstance();
		String userId = request.getParameter("userId");
        JoinVO memberVO = mDao.selectMemberDAO(userId);
        
		
		
		
		String url = "/board/updateForm.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
