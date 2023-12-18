package com.busanit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.BoardDAO;
import com.busanit.controller.DTO.BoardVO;
import com.busanit.controller.action.Action;
import com.busanit.controller.action.BoardListAction;
import com.busanit.controller.action.BoardUpdateAction;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/upload")
public class FileUploadActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FileUploadActionServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest multi = 
				new MultipartRequest(request,"C:\\shm\\workspaceJSP\\recipeBoard2\\src\\main\\webapp\\board\\images\\thumbs",5*1024*1024,"utf-8", new DefaultFileRenamePolicy());
		String command = multi.getParameter("command");
		String image = multi.getParameter("image");
		System.out.println("FileUploadActionServlet에서 요청을 받음을 확인 : " + command);
		
		BoardVO bVo = new BoardVO();
		
		PrintWriter out = response.getWriter();
		
		String msg = "";
		BoardDAO bDao = BoardDAO.getInstance();
		System.out.println("파일올리기 checking... command : "+command+" image : "+image);
		
//				Enumeration params = multi.getParameterNames();
//				
//				while(params.hasMoreElements()){
//					String name = (String) params.nextElement();
//					String value = multi.getParameter(name);
//					out.println(name+ " : " + value + "<br>");
//				}
//				out.println("-----------------------------------------<br>");
		if(image == null || image.equals("")) {
			Enumeration files = multi.getFileNames();
			
			while(files.hasMoreElements()){
				String name = (String) files.nextElement();
				image = multi.getFilesystemName(name);
				
			}
			
		}
		bVo.setUserId(multi.getParameter("userId"));
		bVo.setFood(multi.getParameter("food"));
		bVo.setNick(multi.getParameter("nick"));
		bVo.setLevel(multi.getParameter("level"));
		bVo.setImage(image);
		bVo.setTitle(multi.getParameter("title"));
		bVo.setContent(multi.getParameter("content"));
		
		if(command.equals("board_update")) {
			bVo.setNum(Integer.parseInt(multi.getParameter("num")));
			bDao.updateBoard(bVo);
			msg = "게시글 업로드 완료!!";
		}else if(command.equals("board_write")){
			bDao.insertBoard(bVo);
			msg = "게시글 등록 완료!!";
		}
		
		// 게시글 등록 후 게시글 목록 화면으로 이동
	
		out.write("<script>alert('"+msg+"');</script>");
		new BoardListAction().execute(request, response);
//		response.sendRedirect("/BoardServlet?command=board_list");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		doGet(request, response);
	}

}
