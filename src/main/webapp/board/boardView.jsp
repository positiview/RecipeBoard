<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="/board/assets/css/boardView.css">
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script type="text/javascript" src="/board/script/board.js"></script>
<script>
	// 댓글 수정
	function fnCommentUpdate(commentNum) {
		var $tr = $(".comment_list").find(".commentTr" + commentNum);
		var name = $tr.children("td").eq(0).text().trim();
		var content = $tr.children("td").eq(1).text().trim();
		
		$form = $("form[name=commentFrm]");
		$form.find("#commentName").val(name);
		$form.find("[name=commentContent]").val(content);
		$form.find("[name=command]").val("comment_update");
		$form.find("[name=commentNum]").val(commentNum);
		$form.find("#commentBtn").text("[댓글 수정]");
	}
	
	// 삭제 버튼 클릭 시 비밀번호 입력 창 toggle
	function showPassword(commentNum) {
		$("#inputPassword" + commentNum).toggle();
		$("#deletePass" + commentNum).focus();
	}
	
	// 댓글 삭제
	function fnCommentDelete(commentNum) {
		if($("deletePass" + commentNum) == "" || $("#deletePass" + commentNum).val().length <= 0) {
			alert("비밀번호를 입력해주세요.");
			$("#deletePass" + commentNum).focus();
			return false;
		}
		
		if(confirm("정말 삭제하시겠습니까?")) {
			var $form = $("form[name=commentDeleteFrm" + commentNum + "]");
			$form.find("[name=command]").val("comment_delete");
			
			$.ajax({
				type: "post",
				url: "BoardServlet?command=comment_check_pass",
				data: {"commentNum": $form.find("[name=commentNum]").val(),
					   "deletePass": $form.find("#deletePass" + commentNum).val()	
				},
				success: function(data) {
					var retData = data.trim();
					if(retData == "true") {		// 비밀번호가 맞는 경우
						// 삭제 진행
						$form.submit();
					} else {					// 비밀번호가 맞지 않는 경우
						alert("비밀번호가 맞지 않습니다.");
					}
				},
				error: function() {
					console.log("서버 요청실패");
				}
			});
		} else {
			return false;
		}
	}
	
	// 댓글 등록
	function fnRegComment() {
		if($("#commentName").val() == "" || $("#commentName").val().length <= 0) {
			alert("이름을 입력해주세요.");
			$("#commentName").focus();
			return false;
		}
		
		if($("#commentPass").val() == "" || $("#commentPass").val().length <= 0) {
			alert("비밀번호를 입력해주세요.");
			$("#commentPass").focus();
			return false;
		}
		
		var command = $("form[name=commentFrm]").find("[name=command]").val();
		if(command == "comment_write") {
			return true;
		} else if (command == "comment_update") {
			var commentNum = $("form[name=commentFrm]").find("[name=commentNum]").val();
			$.ajax({
				type: "post",
				url: "BoardServlet?command=comment_check_pass",
				data: {"commentNum": commentNum,
					   "deletePass": $("#commentPass").val()	
				},
				success: function(data) {
					var retData = data.trim();
					if(retData == "true") {		// 비밀번호가 맞는 경우
						// 수정 진행
						return true;
					} else {					// 비밀번호가 맞지 않는 경우
						alert("비밀번호가 맞지 않습니다.");
						return false;
					}
				},
				error: function() {
					console.log("서버 요청실패");
				}
			});
		} else {
			return false;
		}		
	}
</script>
</head>
<body >
	<div id="wrap" align="center">
		<div class="user" align="right">
				 <c:choose>
				<c:when test="${not empty userId}">
						<span><a href="memberUpdate?command=viewForm&userId=${userId }">회원정보</a></span>
						<span><a href="board/logout.jsp">로그아웃</a></span>
				</c:when>
				<c:otherwise>
						<a href="board/Login.jsp">로그인/회원가입</a>
				</c:otherwise>
			 
			 </c:choose>
		</div>
				<h1>${board.title }</h1>
		<table>
<%-- 			<tr>
				<th>파일</th>
				<td colspan="5"><pre>${board.file }</pre></td>
			</tr> --%>
			<tr>
				<th>작성자</th>
				<td>${board.nick }</td>		
				<th>작성일</th>
				<td><fmt:formatDate value="${board.writedate }"/></td>		
			</tr>
			<tr>
				<th>종류 및 난이도</th>
				<td>${board.food} / ${board.level }</td>		
				<th>조회수</th>
				<td>${board.readcount }</td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td colspan="3"><c:if test="${not empty board.image}"><img src="board/images/thumbs/${board.image }"></c:if><pre>${board.content }</pre></td>
			</tr>
		</table>
		<br><br>
		 	<c:choose>
					<c:when test="${not empty userId}">
						<input class="button" type="button" value="게시글 수정" onclick= 
							"open_win('BoardServlet?command=board_check_pass_form&num=${board.num}', 
							'update')">
						<input class="button" type="button" value="게시글 삭제" onclick= 
							"open_win('BoardServlet?command=board_check_pass_form&num=${board.num}', 
							'delete')">
						<input class="button" type="button" value="게시글 등록" 
							onclick="location.href='BoardServlet?command=board_write_form'">
					</c:when>
			</c:choose>
		<input class="submit" type="submit" value="게시글 리스트" onclick="location.href='BoardServlet?command=board_list&currPage=${currPage}&searchType=${searchVO.searchType }&searchText=${searchVO.searchText }'">
	</div>
	<br><br>
	<div id="comment">
	 		
		<table class="comment_list">
		<c:if test="${commentCnt > 0 }">
			<c:forEach var="cList" items="${commentList }">
			<tr class="commentTr${cList.commentNum }">
				<td width="20%">
					${cList.commentName }
				</td>
				<td width="20%">
					${cList.commentContent }
				</td>
				<td width="20%">
					<c:choose>
					<c:when test="${not empty userId}">
					<a href="#" onclick="fnCommentUpdate(${cList.commentNum})">수정</a> |
					<a href="#" onclick="showPassword(${cList.commentNum})">삭제</a>
					</c:when>
			 </c:choose>
					<form action="BoardServlet" name="commentDeleteFrm${cList.commentNum }" method="post">
						<input type="hidden" name="command" value="comment_delete">
						<input type="hidden" name="commentNum" value="${cList.commentNum }">
						<input type="hidden" name="num" value="${cList.num }">
						<input type="hidden" name="currPage" value="${currPage }">
						<input type="hidden" name="searchType" value="${searchVO.searchType }">
						<input type="hidden" name="searchText" value="${searchVO.searchText }">
						<p id="inputPassword${cList.commentNum }" style="display:none;">
							<input type="password" name="deletePass" id="deletePass${cList.commentNum }" 
								placeholder="비밀번호를 입력해주세요.">
							<button onclick="return fnCommentDelete(${cList.commentNum})">실행</button>						
						</p>
					</form>
				</td>
			</tr>
			</c:forEach>
		</c:if>
		</table>
		<c:choose>
				<c:when test="${not empty userId}">
					<table class="comment_write">
						<tr>
							<td>
								<form action="BoardServlet" name="commentFrm" method="post">
									<input type="hidden" name="command" value="${command }">
									<input type="hidden" name="num" value="${board.num }">
									<input type="hidden" name="commentNum">
									<input type="hidden" name="currPage" value="${currPage }">
									<input type="hidden" name="searchType" value="${searchVO.searchType }">
									<input type="hidden" name="searchText" value="${searchVO.searchText }">
									<c:if test="${!isPasswordCheck }">
										<script>
											//alert("패스워드가 일치하지 않습니다. 다시 확인해주세요.");
										</script>
									</c:if>
									<div class="comment-txt">
										<label for="commentName">이름 : </label>
										<input type="text" name="commentName" id="commentName">&nbsp;&nbsp;&nbsp;&nbsp;
										<label for="commentPass">비밀번호 : </label>
										<input type="password" name="commentPass" id="commentPass">
										<br><br>
										<p>
											<textarea name="commentContent" rows="4" cols="70" id="textarea"
												placeholder="댓글을 입력해주세요."></textarea>
											<button class = "commentBtn" id="commentBtn" onclick="return fnRegComment()">[댓글 등록]</button>
										</p>
									</div>
								</form>					
							</td>
						</tr>
					</table>
				</c:when>
		</c:choose>
	</div>
</body>
</html>













