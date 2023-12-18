<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<script>
	if(window.name == "update") {			// 게시글 수정 시
		window.opener.parent.location.href=
			"BoardServlet?command=board_update_form&num=${param.num}";
	} else if(window.name == "delete") {	// 게시글 삭제 시
		alert("삭제되었습니다.");
		window.opener.parent.location.href=
			"BoardServlet?command=board_delete&num=${param.num}";
	}
	window.close();
</script>
</body>
</html>




