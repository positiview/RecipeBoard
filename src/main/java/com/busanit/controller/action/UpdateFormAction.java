package com.busanit.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.busanit.controller.DAO.MemberDAO;
import com.busanit.controller.DTO.JoinVO;


public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
    	String name = request.getParameter("name");
       String nick = request.getParameter("nick");
       String phone = request.getParameter("phone");
       String gender = request.getParameter("gender");
       String comment = request.getParameter("comment");
        
        MemberDAO mDao = MemberDAO.getInstance();
        
        JoinVO member = new JoinVO();
        member.setId(id);
        member.setPw(pw);
        member.setName(name);
        member.setNick(nick);
        member.setPhone(phone);
        member.setGender(gender);
        member.setComment(comment);
        
//        member = mDao.selectMemberDAO(userId);
        
        mDao.updateMember(member);
        HttpSession session = request.getSession();
        session.setAttribute("nick",nick);
        
        String resultUrl = "BoardServlet?command=board_list";
        RequestDispatcher dispatcher = request.getRequestDispatcher(resultUrl);
        dispatcher.forward(request, response);
    
	}

}
