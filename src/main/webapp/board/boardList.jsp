<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<!--
	Snapshot by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
<head>
    <title>Snapshot by TEMPLATED</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="board/assets/css/main.css" />
    <link rel="stylesheet" href=" https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <style>
    	.orderby{
    		display:inline;
    		appearance: menulist;
    		width:auto;
    		margin:0;
    		text-align:right;
    	}
    	
    	.page-item{
    		display:inline;
    	}
    	
    	.page-link{
    	
    		text-decoration:none;	/* 링크 밑줄 없애기 */
			/* color:black; */			/* 글 색상 */
    	}
    	
    	.page-link:HOVER{
    		text-decoration:underline;	/* 밑줄 */
			color:green;				/* 글 색상 */
    	}
    	.searchType{
    		appearance: menulist;
    		width:auto;
    		display:inline;
    	}
		
    </style>
</head>
<body>
<div class="page-wrap">

    <!-- Nav -->
    <nav id="nav">
        <ul>
            <li><a href="../BoardServlet?command=index" class="active"><span class="icon fa-home"></span></a></li>
			<li><a href="../BoardServlet?command=gallery"><span class="fa-solid fa-utensils"></span></a></li>
			<li><a href="../BoardServlet?command=board_list"><span class="icon fa-file-text-o"></span></a></li>
        </ul>
    </nav>

    <!-- Main -->
    <section id="main">

        <!-- Header -->
        <header id="header">
            <div class="user" align="right">
					 <c:choose>
						<c:when test="${not empty userId}">
								<span><%=session.getAttribute("nick") %>님 안녕하세요.</span>
								<span><a href="memberUpdate?command=viewForm&userId=${userId }">회원정보</a></span>
								<span><a href="board/logout.jsp">로그아웃</a></span>
						</c:when>
						<c:otherwise>
								<a href="loginServlet?command=foodRecipe_loginForm">로그인/회원가입</a>
						</c:otherwise>
					 
					 </c:choose>
			</div>
        </header>

        <!-- Section -->
        <section class="ops">
            <div class="inner">
                <div id="wrap" align="center">
					
					<h1 style="color:orange; font-size:40px; " onclick="location.reload()">레시피 리스트</h1>
					<table>
						<tr>
								<c:choose>
									<c:when test="${not empty userId}">
							<td class="options" style="border:white; text-align:left">
									
											<button type="button" onclick="location.href='BoardServlet?command=board_write_form';">레시피 글쓰기</button>
											<!-- <a href="BoardServlet?command=board_write_form">레시피 글쓰기</a> -->
							</td>
									</c:when>
							 	</c:choose>
							<!-- <td>
								<button name="allfood"></button>
								<button name="kfood"></button>
								<button name="cfood"></button>
								<button name="jfood"></button>
								<button name="wfood"></button>
								<button name="etcfood"></button>
							</td> -->
							<td style="border:white; text-align:right">
								<form class="ops" name="filterForm" action="BoardServlet" method="post">
								<input type="hidden" name="command" value="board_list">
								<input type="hidden" name="currPage" value="${pageHandler.currPage }">
									<select class="orderby" name="filterType">
										<option value="numDESC" ${filterVO.filterType == "numDESC" ? "selected" : "" } >
										최근등록순</option>
										<option value="readcountDESC" ${filterVO.filterType == "readcountDESC" ? "selected" : "" }>
										조회수순</option>
										<option value="numASC" ${filterVO.filterType == "numASC" ? "selected" : "" }>
										작성일순</option>
									</select>
									<input class="selectfilter" type="submit" value="정렬">
								</form>
							</td>
						</tr>
					</table>
					<table class="list">
						<tr>
							<th style="width:7%">번호</th>
							<th style="width:8%">종류</th>
							<!-- <th style="width:8%">사진</th> -->
							<th style="width:*">제목</th>
							<th style="width:15%">작성자</th>
							<th style="width:15%">작성일</th>
							<th style="width:7%">조회</th>
						</tr>
						<c:forEach var="board" items="${boardList }">
						<tr class="record">
							<td>${board.num }</td>
							<td>${board.food }</td>
							<%-- <td>${board.image }</td> --%>
							<td>
								<a style="text-decoration:none;	font-weight:bold; color:black;" href="BoardServlet?command=board_view&num=${board.num }&currPage=${pageHandler.currPage }&searchType=${searchVO.searchType}&searchText=${searchVO.searchText}">
									${board.title }
								</a>
							</td>
							<td>${board.nick }</td>
							<td><fmt:formatDate value="${board.writedate }" pattern="MM-dd hh:mm"/></td>
							<td>${board.readcount }</td>
							
						</tr>
						</c:forEach>
					</table>
					<div class="paging">
						<nav id="pagenav">
							<ul class="pagination justify-content-center">
								<li class="page-item">
										<a class="page-link" href="BoardServlet?command=board_list
											&currPage=1&pageSize=${pageHandler.pageSize }&filterType=${filterVO.filterType}"
											data-page="1">처음</a>
								</li>
								<li class="page-item">
									<c:if test="${pageHandler.showPrev }">
										<a class="page-link" href="BoardServlet?command=board_list
											&currPage=${pageHandler.beginPage - 1 }
											&pageSize=${pageHandler.pageSize }
											&filterType=${filterVO.filterType}"
											data-page="${pageHandler.beginPage - 1 }">이전</a>
									</c:if>
								</li>
								<c:forEach var="i" begin="${pageHandler.beginPage }" end="${pageHandler.endPage }">
								<li class="page-item">
									<a class="page-link" href="BoardServlet?command=board_list
										&currPage=${i }&pageSize=${pageHandler.pageSize }&filterType=${filterVO.filterType}"
										data-page="${i }">${i }</a>
								</li>
								</c:forEach>
								<li class="page-item">
									<c:if test="${pageHandler.showNext }">
										<a class="page-link" href="BoardServlet?command=board_list
											&currPage=${pageHandler.endPage + 1 }
											&pageSize=${pageHandler.pageSize }
											&filterType=${filterVO.filterType}"
											data-page="${pageHandler.endPage + 1 }">다음</a>
									</c:if>
								</li>
								<li class="page-item">
									<a class="page-link" href="BoardServlet?command=board_list
										&currPage=${pageHandler.totalPage }&pageSize=${pageHandler.pageSize }&filterType=${filterVO.filterType}"
										data-page="${pageHandler.totalPage }">마지막</a>
								</li>
							</ul>
						</nav>
					</div>
					<div class="search">
						<form name="searchForm" action="BoardServlet" method="post">
							<input type="hidden" name="command" value="board_list">
							<input type="hidden" name="currPage" value="${pageHandler.currPage }">
							<select class="searchType" name="searchType">
								<option value="title" ${searchVO.searchType == "title" ? "selected" : "" }>
								제목</option>
								<option value="content" ${searchVO.searchType == "content" ? "selected" : "" }>
								내용</option>
								<option value="all" ${searchVO.searchType == "all" ? "selected" : "" }>
								제목+내용</option>
								<option value="author" ${searchVO.searchType == "author" ? "selected" : "" }>
								작성자</option>
							</select>
							<input style="width:300px; display:inline" type="text" name="searchText" value="${searchVO.searchText }">
							<input style="display:inline" type="submit" value="검색">
						</form>
					</div>
				</div>
            </div>
        </section>

        <!-- Contact -->
        <section id="contact">
            <!-- Social -->
            <div class="social column">
                <h3>About Me</h3>
                <p>Mus sed interdum nunc dictum rutrum scelerisque erat a parturient condimentum potenti dapibus vestibulum condimentum per tristique porta. Torquent a ut consectetur a vel ullamcorper a commodo a mattis ipsum class quam sed eros vestibulum quisque a eu nulla scelerisque a elementum vestibulum.</p>
                <p>Aliquet dolor ultricies sem rhoncus dolor ullamcorper pharetra dis condimentum ullamcorper rutrum vehicula id nisi vel aptent orci litora hendrerit penatibus erat ad sit. In a semper velit eleifend a viverra adipiscing a phasellus urna praesent parturient integer ultrices montes parturient suscipit posuere quis aenean. Parturient euismod ultricies commodo arcu elementum suspendisse id dictumst at ut vestibulum conubia quisque a himenaeos dictum proin dis purus integer mollis parturient eros scelerisque dis libero parturient magnis.</p>
                <h3>Follow Me</h3>
                <ul class="icons">
                    <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
                    <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
                    <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
                </ul>
            </div>

            <!-- Form -->
            <div class="column">
                <h3>Get in Touch</h3>
                <form action="#" method="post">
                    <div class="field half first">
                        <label for="name">Name</label>
                        <input name="name" id="name" type="text" placeholder="Name">
                        <div data-lastpass-icon-root="true" style="position: relative !important; height: 0px !important; width: 0px !important; float: left !important;"></div></div>
                    <div class="field half">
                        <label for="email">Email</label>
                        <input name="email" id="email" type="email" placeholder="Email">
                    </div>
                    <div class="field">
                        <label for="message">Message</label>
                        <textarea name="message" id="message" rows="6" placeholder="Message" data-gramm="false" wt-ignore-input="true"></textarea>
                    </div>
                    <ul class="actions">
                        <li><input value="Send Message" class="button" type="submit"></li>
                    </ul>
                </form>
            </div>

        </section>
    </section>
</div>
<div class="copyright">
    Design by: <a href="https://templated.co/">TEMPLATED.CO</a>
</div>
<!-- Scripts -->
<script src="board/assets/js/jquery.min.js"></script>
<script src="board/assets/js/jquery.poptrox.min.js"></script>
<script src="board/assets/js/jquery.scrolly.min.js"></script>
<script src="board/assets/js/skel.min.js"></script>
<script src="board/assets/js/util.js"></script>
<script src="board/assets/js/main.js"></script>

</body>
</html>