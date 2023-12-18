<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/board/assets/css/login.css">
<script type="text/javascript" src="script/jquery-3.6.3.js"></script>
<script type="text/javascript" src="script/board.js"></script>
 <link rel="stylesheet" href=" https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
</head>
<body>
	<div id="wrap" align="center">
		<h2>로그인</h2>
		<form name="login" method="post" action="/loginServlet">
			<input type="hidden" name="command" value="foodRecipe_login">
			<table>
				<tr>
					<td><input class="in" type="text" name="id" placeholder="ID"></td>
				</tr>
				<tr>
					<td>
						<input class="in" type="password" name="pw" placeholder="PASSWORD"> 
					</td>
				</tr>
			</table>
			<br><br>
			<input type="submit" value="로그인"  id = "login">
			<br>
			<input type="button" value="회원가입"  id = "join"
				onclick="location.href='/loginServlet?command=joinForm'">
				<div>
				<p class="line"></p>
				<p class = "or">&nbsp or &nbsp
				<p class="line">
				</div>
				<div class= "box">
				<a  href="https://accounts.google.com/"><span class="fa-brands fa-google fa-2xl"></span></a>
				<a  href="https://www.instagram.com/"><span class="fa-brands fa-instagram fa-2xl"></span></a>
				<a  href="https://ko-kr.facebook.com/"><span class="fa-brands fa-facebook fa-2xl"></span></a>
		
				</div>
				</form>
				</div> 
			
		</form>
				</div> 
</body>
</html>