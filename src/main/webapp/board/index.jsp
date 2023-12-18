<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<!--
	Snapshot by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
	<head>
		<title>Snapshot by TEMPLATED</title>
		<!-- <meta charset="utf-8" /> -->
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="board/assets/css/main.css" />
		<link rel="stylesheet" href=" https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
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

					<!-- Banner -->
						<section id="banner">
							<div class="inner">
								<h1>모두의 레시피</h1>
								<p>
									<c:choose>
										<c:when test="${not empty userId}">
												<%=session.getAttribute("nick") %>님 안녕하세요.
												<ul class="actions">
													<li><a href="#galleries" class="button alt scrolly big">Continue</a></li>
												</ul>
										</c:when>
										<c:otherwise>
												환영합니다
												<ul class="actions">
													<li><a href="loginServlet?command=foodRecipe_loginForm" class="button alt scrolly big">Login</a></li>
												</ul>
										</c:otherwise>
								 
								 	</c:choose>
								 </p>
							</div>
						</section>

					<!-- Gallery -->
						<section id="galleries">

							<!-- Photo Galleries -->
								<div class="gallery">
									<header class="special">
										<h2>What's New</h2>
									</header>
									<div class="content">
										<c:forEach var="images" items="${imageList }">
						                    <div class="media">
						                        <a href="BoardServlet?command=board_view&num=${images.num }" style="outline: 0px;"><img src="board/images/thumbs/${images.image }" alt="" title=""></a>
						                    </div>
					                    </c:forEach>
									</div>
									<footer>
										<span><a href="../BoardServlet?command=gallery" class="button big">Full Gallery</a></span>
										<c:choose>
											<c:when test="${not empty userId}">
									 			<span><a href="../BoardServlet?command=board_write_form" class="button big">글쓰기</a></span>
													
											</c:when>
										</c:choose>
								 
									</footer>
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
										</div>
										<div class="field half">
											<label for="email">Email</label>
											<input name="email" id="email" type="email" placeholder="Email">
										</div>
										<div class="field">
											<label for="message">Message</label>
											<textarea name="message" id="message" rows="6" placeholder="Message"></textarea>
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
			<!-- <script src="board/assets/js/jquery.poptrox.min.js"></script> -->
			<script src="board/assets/js/jquery.scrolly.min.js"></script>
			<script src="board/assets/js/skel.min.js"></script>
			<script src="board/assets/js/util.js"></script>
			<script src="board/assets/js/main.js"></script>

	</body>
</html>