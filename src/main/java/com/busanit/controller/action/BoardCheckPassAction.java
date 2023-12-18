package com.busanit.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.BoardDAO;
import com.busanit.controller.DAO.LogDAO;
import com.busanit.controller.DTO.BoardVO;
import com.mysql.cj.log.Log;

public class BoardCheckPassAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = null;
		
		//게시글id랑 로그인 id가 같아야하며 pw도 같아야 수정 가능
		String num = request.getParameter("num");	// 게시글 번호
		String userId = request.getParameter("userId"); // 아이디 확인
		String userPw = request.getParameter("userPw");	// 비밀번호 확인 입력값
		
		BoardDAO bDao = BoardDAO.getInstance();
		// 게시글 번호로 해당 게시글의 정보 DB에서 조회 후 VO에 저장
		BoardVO bVo = bDao.selectOneBoardByNum(num);
		LogDAO ldao = LogDAO.getInstance();
		
		boolean checkIdPw = ldao.checkIdPw(userId, userPw);
		
		
		if(checkIdPw) {	// 비밀번호 일치
			url = "/board/checkSuccess.jsp";
		} else {	// 비밀번호 불일치 - 실패
			url = "/board/boardCheckPass.jsp";
			request.setAttribute("message", "비밀번호가 틀렸습니다");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}




