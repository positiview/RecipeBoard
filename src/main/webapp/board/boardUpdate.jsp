<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="board\assets\css\updateWrite.css">
<script type="text/javascript" src="script/jquery-3.6.3.js"></script>
<script type="text/javascript" src="script/board.js"></script>
</head>
<body>
	<div id="wrap" align="center">
		<h1>게시글 수정</h1>
		<form name="frm" method="post" action="/upload" enctype="multipart/form-data" accept-charset="UTF-8">
		
			<input type="hidden" name="command" value="board_update">
			<input type="hidden" name="image" value="${board.image }">
			<input type="hidden" name="num"	value="${board.num }">
			<table>
				<tr>
					<th>id</th>
					<td>
						<input type="text" size="12" name="userId" value="${userId }" readonly>
					</td>
				</tr>
				<tr>
					<th>음식 종류</th>
					<td>
						<input type="radio" id="ko" name="food" value="한식" <c:if test="${board.food eq '한식'}">checked</c:if>>
						<label for="ko">한식</label>
						<input type="radio" id="ch" name="food" value="중식" <c:if test="${board.food eq '중식'}">checked</c:if>>
						<label for="ch">중식</label>
						<input type="radio" id="ja" name="food" value="일식" <c:if test="${board.food eq '일식'}">checked</c:if>>
						<label for="ja">일식</label>
						<input type="radio" id="we" name="food" value="양식" <c:if test="${board.food eq '양식'}">checked</c:if>>
						<label for="we">양식</label>
						<input type="radio" id="et" name="food" value="기타" <c:if test="${board.food eq '기타'}">checked</c:if>>
						<label for="et">기타</label>
						
					</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td>
						<input type="text" size="40" maxlength="50" name="nick" value="${board.nick }">
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" size="70" name="title" value="${board.title }"> * 필수
					</td>
				</tr>
				<tr>
					<th>난이도</th>
					<td><input type="radio" id="good" name="level" value="상" <c:if test="${board.level eq '상'}">checked</c:if>>
				  		<label for="good">상</label>
					<input type="radio" id="fair" name="level" value="중" <c:if test="${board.level eq '중'}">checked</c:if>>
				  		<label for="fair">중</label>
					<input type="radio" id="poor" name="level" value="하" <c:if test="${board.level eq '하'}">checked</c:if>>
				  		<label for="poor">하</label> </td>
				</tr>
				<tr>
					<th>파일 첨부</th>
					<td><input type="file" name="file">${board.image }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea cols="70" rows="15" name="content">${board.content }</textarea>
					</td>
				</tr>
			</table>
			<br><br>
			<input type="submit" value="등록" onclick="return boardCheck()">
			<input type="reset"	value="다시 작성">
			<input type="button" value="목록" onclick="location.href='BoardServlet?command=board_list'">
		</form>
	</div>
</body>
</html>





