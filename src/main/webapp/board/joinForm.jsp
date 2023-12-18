<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="/board/assets/css/join.css">
</head>
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
		
		if(id.val() == "") {
			alert("아이디를 입력해주세요.");
			id.focus();
			return false;
		} else if(pw.val() == "") {
			alert("비밀번호를 입력해주세요.");
			pw.focus();
			return false;
		}else if(checkPw.val() == "" || checkPw.val() != pw.val()) {
			alert("비밀번호 확인을 다시 해주세요.");
			pw.focus();
			return false;
		}else if(name.val() == "") {
			alert("이름을 입력해주세요.");
			name.focus();
			return false;
		}else if(nickName.val() == "") {
			alert("닉네임을 입력해주세요.");
			nick.focus();
			return false;
		}else if(phone.val() == "") {
			alert("전화번호를 입력해주세요.");
			phone.focus();
			return false;
		}else if(!gender.is(":checked")) {
			alert("성별을 선택해주세요.");
			gender.focus();
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
</script>

<body>
<div id="wrap">
	<h2 align="center">회원 가입</h2>
	<form action="/joinMemberDB" name="joinMember" method="post" >
		<p class = "check">아이디</p>
		<input class = "button" type="button" value="아이디 중복 검사" onclick="checkId();">
				 			<input class="in" type="text" name="id">
							<span id="result"></span>
		<p class = "check" >비밀번호<input class="in" type="password" name="pw">
		<p class = "check">비밀번호 확인</p> <input class="in" type="password" name="checkPw">
		<p class = "check">이름 </p><input class="in" type="text" name="name">
		<p class = "check">닉네임</p>
					<input class = "button" type="button" value="닉네임 중복 검사" onclick="checkNick();">
					<input class="in" type="text" name = "nick"> 
					<span id="result1"></span>
		<p class = "check">연락처</p> <input class="in" type="text" name="phone"  placeholder="-를 포함해주세요">
		<p class = "check">성별&nbsp&nbsp <input type="radio" id="male" name="gender" value="남성" checked>
					<label for="male">남성&nbsp&nbsp </label>
					<input type="radio" id="female" name="gender" value="여성">
					<label for="female">여성</label></p>
		<br><br>
		<p><textarea class="textarea" name="comment" cols="50" rows="6" placeholder="가입 인사를 입력해주세요."></textarea></p>			
		<p><input class="submit" type = "submit" style = "text-align: center; "value="가입하기" onclick="return checkForm();"> <input class = "submit" type="reset" value="다시 쓰기"></p>
			
	</form>
</div>
</body>



