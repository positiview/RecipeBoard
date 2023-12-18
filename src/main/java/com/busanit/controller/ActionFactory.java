package com.busanit.controller;

import com.busanit.controller.action.Action;
import com.busanit.controller.action.BoardCheckPassAction;
import com.busanit.controller.action.BoardCheckPassFormAction;
import com.busanit.controller.action.BoardDeleteAction;
import com.busanit.controller.action.BoardListAction;
import com.busanit.controller.action.BoardUpdateAction;
import com.busanit.controller.action.BoardUpdateFormAction;
import com.busanit.controller.action.BoardViewAction;
import com.busanit.controller.action.BoardWriteFormAction;
import com.busanit.controller.action.CommentCheckPassAction;
import com.busanit.controller.action.CommentDeleteAction;
import com.busanit.controller.action.CommentUpdateAction;
import com.busanit.controller.action.CommentWriteAction;
import com.busanit.controller.action.DeleteIdFormAction;
import com.busanit.controller.action.GalleryFormAction;
import com.busanit.controller.action.JoinFormAction;
import com.busanit.controller.action.LoginAction;
import com.busanit.controller.action.LoginFormAction;
import com.busanit.controller.action.LogoutAction;
import com.busanit.controller.action.StartAction;
import com.busanit.controller.action.UpdateFormAction;
import com.busanit.controller.action.UserViewFormAction;

public class ActionFactory {
	// 싱글톤 형태로 한 개의 객체만 생성
	private static ActionFactory instance = new ActionFactory();
	
	private ActionFactory() {
		super();
	}
	
	// 생성된 객체를 getInstance로 호출
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory : " + command);
		
		if(command.equals("board_list")) {
			action = new BoardListAction();
		}else if(command.equals("foodRecipe_loginForm")) {
			action = new LoginFormAction();
		}else if(command.equals("foodRecipe_login")) {
			action = new LoginAction();
		}else if(command.equals("joinForm")) {
			action = new JoinFormAction();
		}else if(command.equals("board_write_form")) {
			action = new BoardWriteFormAction();
		} else if(command.equals("board_view")) {
			action = new BoardViewAction();
		} 
		 else if(command.equals("board_check_pass_form")) {
			action = new BoardCheckPassFormAction();
		} else if(command.equals("board_check_pass")) {
			action = new BoardCheckPassAction();
		} else if(command.equals("board_update_form")) {
			action = new BoardUpdateFormAction();
		} else if(command.equals("board_update")) {
			action = new BoardUpdateAction();
		} else if(command.equals("board_delete")) {
			action = new BoardDeleteAction();
		} 
		else if(command.equals("comment_write")) {
			action = new CommentWriteAction();
		} 
		else if(command.equals("comment_update")) {
			action = new CommentUpdateAction();
		} else if(command.equals("comment_delete")) {
			action = new CommentDeleteAction();
		} else if(command.equals("comment_check_pass")) {
			action = new CommentCheckPassAction();
		} else if(command.equals("updateForm")) {
			action = new UpdateFormAction();
		} else if(command.equals("viewForm")) {
			action = new UserViewFormAction();
		} else if(command.equals("index")) {
			action = new StartAction();
		} else if(command.equals("gallery")) {
			action = new GalleryFormAction();
		} else if(command.equals("logout")) {
	         action = new LogoutAction();
	    } else if(command.equals("deleteId")) {
	    	action = new DeleteIdFormAction();
	    }
		
		return action;
	}
}







