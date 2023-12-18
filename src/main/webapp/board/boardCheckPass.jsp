<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="css/board.css">
<script type="text/javascript" src="script/jquery-3.6.3.js"></script>
<script type="text/javascript" src="script/board.js"></script>
</head>
<body>
	<h1>비밀번호 확인</h1>
	<form action="BoardServlet" name="frm" method="get">
		<input type="hidden" name="command" value="board_check_pass">
		<input type="hidden" name="num" value="${param.num }">
		<table style="width: 80%">
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="userId" size="20" value="${userId }">
				</td>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="userPw" size="20">				
				</td>
			</tr>
		</table>
		<br>
		<input type="submit" value=" 확 인 " onclick="return passCheck()">
		<br><br>
		${message }
	</form>
</body>
</html>




