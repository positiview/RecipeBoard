<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="board/assets/css/update.css">
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script>
	function checkForm() {
		var id = $("[name=id]");
		var pw = $("[name=pw]");
		var checkPw = $("[name=checkPw]");
		var name = $("[name=name]");
		var nick = $("[name = nickName]");
		var phone = $("[name=phone]");
		var gender = $("[name=gender]");
		var comment = $("[name=comment]");
		
		if(pw.val() == "") {
			alert("비밀번호를 입력해주세요.");
			pw.focus();
			return false;
		}else if(checkPw.val() == "" || checkPw.val() != pw.val()) {
			alert("비밀번호 확인을 다시 해주세요.");
			pw.focus();
			return false;
		}else if(nickName.val() == "") {
			alert("닉네임을 입력해주세요.");
			nick.focus();
			return false;
		}else if(phone.val() == "") {
			alert("전화번호를 입력해주세요.");
			phone.focus();
			return false;
		} else if(comment.val() == "") {
			alert("가입인사를 입력해주세요.");
			comment.focus();
			return false;
		}
		$("[name=joinMember]").submit();
	}
	function checkId() {
		var inputId = $("[name=id]").val();
		$.ajax({
			type : "post",
			url : "/checkId",
			data : {"inputId" : inputId},
			success : function(data) {
				$("#result").text(data);
			}, error : function(){
				console.log("서버 요청 실패");
			}
		})
	}
	function checkNick() {
		var inputNick = $("[name=nick]").val();
		$.ajax({
			type : "post",
			url : "/checkNick",
			data : {"inputNick" : inputNick},
			success : function(data) {
				$("#result1").text(data);
			}, error : function(){
				console.log("서버 요청 실패");
			}
		})
	}
	function checkConfirm(){
		if(confirm("계정을 삭제 하시겠습니까?")){
			window.open('/board/deleteId.jsp');
		}
	}
</script>
<body>
	<form id="wrap" action="/memberUpdate" name="joinMember" method="post" >
		<input type="hidden" name="command" value="updateForm">
		<input type="hidden" name="name" value="${joinMember.name }">
		
			<h3 >회원정보</h3>
				<p class = "check">아 이 디 : <input class="in" type="text" name="id" value="${joinMember.id }"></p>
					
				<p class = "check">비밀번호 : <input class="in"  type="password" name="pw"></p>
				<p class = "check">비밀번호 확인 : <input class="in"  type="password" name="checkPw"></p>
				<p class = "checkName">이름 : ${joinMember.name }</p>
				<p class = "check">닉네임 : <input class="button" type="button" value="닉네임 중복 검사" onclick="checkNick();">
				<input class="in"  type="text" name = "nick" value="${joinMember.nick }"> 
							  
							  <span id="result1"></span></p>
				<p class = "check">연락처 : <input class="in"  type="text" name="phone" value= "${joinMember.phone }"></p>
				<p >성별 : <input  type="radio" id="male" name="gender" value="남성" checked>
							 <label for="male">남성</label>
							 <input type="radio" id="female" name="gender" value="여성">
							 <label for="female">여성</label></p>
				
				<p><textarea class="textarea" name="comment" cols="50" rows="6"  >${joinMember.comment }</textarea></p>
				
				<p><input class = "submit" type="submit" value="수정하기" onclick="return checkForm();"> <input class = "submit2" type="reset" value="다시 쓰기">
					<input class = "submit2" type="button" value ="계정삭제" onclick ="return checkConfirm();"></p>
			
		
	</form>
</body>
</html>