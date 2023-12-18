<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="/board/assets/css/boardWrite.css">
<script type="text/javascript" src="../js/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="script/board.js"></script>
</head>
<script>
	function boardCheck(){						//유형(작성방법)
	 	var form = $("[name=frm]");
		var id = $("[name=nick]").val();			//1
		var pass = $("[name=pass]");			//2
		var title = $("[name=title]");
		
		if(title.val() == ""){
			alert("제목을 입력해주세요.");
			title.focus();
			return false;
		}else if(id.length == 0){
			alert("닉네임를 입력해주세요.");
			$("[name=name]").focus();
			return false;
	 	}else if(pass.val() == ""){
			alert("비밀번호를 입력해주세요.");
			pass.focus();
			return false;
	 	}

		// $("[name=member]").submit();
		form.submit();
	}
	</script>
<body>
			<div id="wrap" align="center">
		<span id="welcome"><%=session.getAttribute("nick") %>님 안녕하세요.</span>
		<span><a href="memberUpdate?command=viewForm&userId=${userId }" id="log">회원정보</a></span>
		<span><a href="board/logout.jsp" id="log">로그아웃</a></span>
			<h1>나만의 레시피 등록하기 </h1>
<!-- 			<a href="BoardServlet?command=board_list" id="list">한식</a> -->
<!-- 			<a href="BoardServlet?command=board_list" id="list">중식</a> -->
<!-- 			<a href="BoardServlet?command=board_list" id="list">일식</a> -->
<!-- 			<a href="BoardServlet?command=board_list" id="list">양식</a> -->
<!-- 			<a href="BoardServlet?command=board_list" id="list">기타</a> -->
			<form name="frm" method="post" action="/upload" 
			 enctype="multipart/form-data">
			<input type="hidden" name="command" value="board_write">
			<table>
				<tr>
					<th>제목 *</th>
					<td colspan="3"><input style = "width : 60%" type="text" size="70" name="title" id = "title"></td>
				</tr>
				<tr>
					<th>닉네임 *</th>
					<td><input type="text" name="nick" id = "nick" value= <%=session.getAttribute("nick") %>></td>
<!-- 					<th>비밀번호 *</th> -->
<!-- 					<td><input type="password" name="pass" id = "pass"></td> -->
					
				</tr>
				<p>* 항목은 필수 항목입니다.</p>
				<tr>
					<th>난이도</th>
					<td><input type="radio" id="good" name="level" value="상" id="level">
				  		<label for="good" name="levelName">상</label>&nbsp &nbsp
					<input type="radio" id="fair" name="level" value="중" id="level">
				  		<label for="fair" name="levelName">중</label>&nbsp &nbsp
					<input type="radio" id="poor" name="level" value="하" id="level">
				  		<label for="poor" name="levelName">하</label> </td>

					<th>음식 종류</th>
					<td>
					<select name ="food" id="food">
					<option value="한식">한식</option>
					<option value="중식">중식</option>
					<option value="일식">일식</option>
					<option value="양식">양식</option>
					<option value="기타">기타</option>
					</select>

					</td>
				</tr>
				<tr>
					<th>파일 첨부</th>
					<td colspan="3"><input type="file" name="file" id = "file"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3"><textarea cols="120" rows="15" name="content" id="writetext"></textarea></td>
				</tr>
			</table>
			<br>
			<input class = "button" type="submit" value="글쓰기" onclick="return boardCheck();" id="submit">
			<input class = "button" type="reset" value="다시쓰기" id="reset">
			<input type="button" value="목록" onclick="location.href='BoardServlet?command=board_list'" id="button">
		</form>
	</div>
		
</body>
</html>








