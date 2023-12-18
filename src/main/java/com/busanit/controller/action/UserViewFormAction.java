package com.busanit.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.MemberDAO;
import com.busanit.controller.DTO.JoinVO;

public class UserViewFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");
        
        MemberDAO mDao = MemberDAO.getInstance();
        
        JoinVO member = new JoinVO();
        member = mDao.selectMemberDAO(userId);
        
//        mDao.updateMember(member);
  
        
//        member.setId();
//        member.setPw(pw);
//        member.setName(name);
//        member.setNick(nick);
//        member.setPhone(phone);
//        member.setGender(gender);
//        member.setComment(comment);

        
//        boolean success = mDao.updateMember(member);
       
//        if (success) {
//            response.getWriter().write("회원 정보 수정 성공!");
//        } else {
//            response.getWriter().write("회원 정보 수정 실패...");
//        }
        request.setAttribute("joinMember", member);
        String url = "/board/updateForm.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	

}
