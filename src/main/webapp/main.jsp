<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="/script/style.css" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>여행가이드_Gayage</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900|Quicksand:400,700|Questrial"
	rel="stylesheet" />
<link rel="stylesheet" href="resource/css/default.css" type="text/css"
	media="all">
<link rel="stylesheet" href="resource/css/fonts.css" type="text/css"
	media="all" />
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

<script type="text/javascript">

	var img=new Array();
	img[0]=new Image(); img[0].src="resource/images/travel1.jpg";
	img[1]=new Image(); img[1].src="resource/images/travel2.jpg";
	img[2]=new Image(); img[2].src="resource/images/travel3.jpg";
	img[3]=new Image(); img[3].src="resource/images/travel4.jpg";
	img[4]=new Image(); img[4].src="resource/images/travel5.jpg";
	
	var interval=3000;
	var n=0;
	var imgs = new Array("resource/images/travel1.jpg",
			"resource/images/travel2.jpg",
			"resource/images/travel3.jpg",
			"resource/images/travel4.jpg",
			"resource/images/travel5.jpg");
	function rotate()
	{
	if(navigator.appName=="Netscape" && document.getElementById)
	{
	document.getElementById("slide").src=imgs[n];
	}
	else document.images.slide.src=imgs[n];
	(n==(imgs.length-1))?n=0:n++;
	setTimeout("rotate()",interval);
	}

</script>

</head>
<body  onload="rotate()">
	<!--================================== 상단부 =====================================  -->
	<div id="header-wrapper">
		<div id="header" class="container">
	
			<!-- 로그인버튼 -->
			<div id="login">
				<c:if test="${MEM_NUM != null and '9' ne MEM_LVL}">
					<a href="mainlogout" class="button">Logout</a>
					<a href="myPage.do" class="button">MaPage</a><br>
					<p id="msg">
						${MEM_NAME}님 환영합니다.
					</p>
				</c:if>
				<c:if test="${MEM_NUM == null or '9' eq MEM_LVL}">
					<a href="login" class="button">Login</a>
					<a href="mbrJoin" class="button">Join</a><br>
					<p id="msg">
						로그인 바랍니다.
					</p>
				</c:if>
			</div>

			<!-- 로고 -->
			<div id="logo">
				<span class="icon icon-cog"></span>
				<span class="icon icon-plane-departure"></span>
				<h1>
					<a href="main">G a y a g e</a>
				</h1>
			</div>

			<div id="menu">
				<ul>
					<!-- <li class="active"><a href="#" accesskey="1" title="">사이트소개</a></li> -->
					<li><a href="siteInfo.do" accesskey="1" title="">사이트소개</a></li>
					<li><a href="domesticBoard.do" accesskey="2" title="">국내여행가이드</a></li>
					<li><a href="abroadBoard.do" accesskey="3" title="">해외여행가이드</a></li>
					<li><a href="groupBoard.do" accesskey="4" title="">동행자모집</a></li>
					<li><a href="communityBoard.do" accesskey="5" title="">커뮤니티</a></li>
				</ul>
			</div>

		</div>
	</div>

	<!--================================== 내용 =====================================  -->
	<div class="wrapper">
		
		<div class="maintitle">
				<h2>Welcome to our website</h2>
		</div>
		
		<div id="travel" class="">
			<img src="resource/images/travel1.jpg" id="slide">
		</div>
		
		<div id="welcome" class="container">
			<!-- <p>
				This is <strong>Undeviating</strong>, a free, fully
				standards-compliant CSS template designed by <a
					href="http://templated.co" rel="nofollow">TEMPLATED</a>. The photos
				in this template are from <a href="http://fotogrph.com/">
					Fotogrph</a>. This free template is released under the <a
					href="http://templated.co/license">Creative Commons Attribution</a>
				license, so you're pretty much free to do whatever you want with it
				(even use it commercially) provided you give us credit for it. Have
				fun :)
			</p> -->
		</div>
		
		<!-- <div id="three-column" class="container">
			<div>
				<span class="arrow-down"></span>
			</div>
			<div id="tbox1">
				<div class="title">
					<h2>■ 공지사항</h2>
				</div>
				<p style="text-align: left;">
					<a href="#">1번 : 공지사항</a> <br> <a href="#">2번 : 공지사항</a> <br>
					<a href="#">3번 : 공지사항</a> <br>
				</p>
				<a href="#" class="button">Learn More</a>
			</div>
			<div id="tbox2">
				<div class="title">
					<h2>■ 최근게시글</h2>
				</div>
				<p style="text-align: left;">
					<a href="#">1번 : 최근글</a> <br> <a href="#">2번 : 최근글</a> <br>
					<a href="#">3번 : 최근글</a> <br>
				</p>
				<a href="#" class="button">Learn More</a>
			</div>
		</div> -->
	</div>
	
	<!--================================== 하단부 =====================================  -->
	<div id="footer">
		<div class="container">
			<div class="fbox1">
				<span class="icon icon-map-marker"></span> <span>1234
					Fictional Road Suite #789 <br />Nashville TN 00000
				</span>
			</div>
			<div class="fbox1">
				<span class="icon icon-phone"></span> <span> Telephone: +1
					800 123 4657 </span>
			</div>
			<div class="fbox1">
				<span class="icon icon-envelope"></span> <span>businessname@business.com</span>
			</div>
		</div>
	</div>
	
	<div id="copyright">
		<p>
			&copy; Untitled. All rights reserved. | Photos by <a
				href="http://fotogrph.com/">Fotogrph</a> | Design by <a
				href="http://templated.co" rel="nofollow">TEMPLATED</a>.
		</p>
		<ul class="contact">
			<li><a href="#" class="icon icon-twitter"><span>Twitter</span></a></li>
			<li><a href="#" class="icon icon-facebook"><span></span></a></li>
			<li><a href="#" class="icon icon-rss"><span>Pinterest</span></a></li>
		</ul>
	</div>
</body>
</html>
