package com.busanit.controller.DTO;

import java.sql.Timestamp;

public class CommentVO {
	private int commentNum;
	private int num;
	private String commentName;
	private String commentPass;
	private Timestamp commentDate;
	private String commentContent;
	
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCommentName() {
		return commentName;
	}
	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}
	public String getCommentPass() {
		return commentPass;
	}
	public void setCommentPass(String commentPass) {
		this.commentPass = commentPass;
	}
	public Timestamp getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
}
