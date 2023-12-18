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
    <title>Generic - Snapshot by TEMPLATED</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="board/assets/css/main.css" />
    <link rel="stylesheet" href=" https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />

</head>
<style>
/*화면에 표출되기 전*/
.animatable {
	visibility: hidden; 
	animation-play-state: paused;
}

/*화면에 표출된 후*/
.animated {
	animation-name: fadeInUp; 
	visibility: visible; 
	animation-fill-mode: both; 
	animation-duration: 2s; 
	animation-play_state:running;
}

@keyframes fadeInUp {
	0% {
	    opacity: 0;
	    transform: translateY(20px);
	}
	100% {
	    opacity: 1;
	    transform: translateY(0);
	}
}
</style>
<script>
	window.onload = function() {
		window.addEventListener("scroll", function(e) {
			scrollEvent();
		});
	}
	
	var scrollEvent = function() {
		// 사용자 모니터 화면 높이 + 스크롤이 움직인 높이
		var scroll = window.innerHeight + window.scrollY;
		
	    // 애니메이션 효과를 넣을 DOM 객체 배열
		var itemList = document.querySelectorAll(".content div");
		 
		Array.prototype.forEach.call(itemList, function(div){
			// 객체 위치와 높이 비교 : 화면에 표출되는 높이인지 체크
			if(div.offsetTop < scroll) {
				// 객체 animatable 클래스 지우고, animated 클래스 추가
				div.classList.remove("animatable");
				div.classList.add("animated");
			}
		});
	}
</script>
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
								<a href="board/Login.jsp">로그인/회원가입</a>
						</c:otherwise>
					 
					 </c:choose>
			</div>
        </header>

        <!-- Gallery -->
        <section id="galleries">

            <!-- Photo Galleries -->
            <div class="gallery">

                <!-- Filters -->
                <header>
                    <h1>Gallery</h1>
                    <ul class="tabs">
                        <li><a href="#" data-tag="all" class="button active">All</a></li>
                        <li><a href="#" data-tag="한식" class="button">한식</a></li>
                        <li><a href="#" data-tag="중식" class="button">중식</a></li>
                        <li><a href="#" data-tag="일식" class="button">일식</a></li>
                        <li><a href="#" data-tag="양식" class="button">양식</a></li>
                        <li><a href="#" data-tag="기타" class="button">기타</a></li>
                    </ul>
                </header>

                <div class="content">
                	<c:forEach var="images" items="${imageList }">
	                    <div class="media all ${images.food } animatable">
	                        <a href="BoardServlet?command=board_view&num=${images.num }" style="outline: 0px;"><img src="board/images/thumbs/${images.image }" alt="" title="" ></a>
	                    </div>
                    </c:forEach>
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
<script src="board/assets/js/jquery.scrolly.min.js"></script>
<script src="board/assets/js/jquery.poptrox.min.js"></script>
<script src="board/assets/js/skel.min.js"></script>
<script src="board/assets/js/util.js"></script>
<script src="board/assets/js/main.js"></script>

</body>
</html>