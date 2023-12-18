<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 확인</title>
</head>
<body>
	<%
	 	String userId = (String)request.getAttribute("userId");
		String nick = (String)request.getAttribute("nick");
		session.setAttribute("userId",userId);
		session.setAttribute("nick",nick);
	%>
	<c:choose>
		<c:when test="${isLogin }">
			<alert>로그인이 성공했습니다.</alert>
			<% response.sendRedirect("BoardServlet?command=index"); %>
			
		</c:when>
		<c:otherwise>
			<p>로그인이 실패했습니다.</p>
			<p><button onclick="location.href='loginServlet?command=foodRecipe_loginForm'">로그인</button></p>
		</c:otherwise>
	</c:choose>
</body>
</html>