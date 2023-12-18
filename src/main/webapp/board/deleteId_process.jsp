<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/delete.css">
</head>
<body>
	<fieldset id="wrap">
	<c:choose>
		<c:when test="${resultCount > 0}">
			<p>아이디가 삭제 되었습니다
			<%response.sendRedirect("BoardServlet?command=board_list"); %>
		</c:when>
		<c:otherwise>
			<h3 align="center" margin-bottom="10px">아이디 삭제에 실패 했습니다.</h3>
			<p align="center">비번을 다시 확인해주세요.
			<p><button class="submit2" onclick="window.history.back()">돌아가기</button>
		</c:otherwise>
	</c:choose>
	</fieldset>
</body>
</html>