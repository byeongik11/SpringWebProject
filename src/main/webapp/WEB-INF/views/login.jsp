<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>여행가이드_Gayage</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900|Quicksand:400,700|Questrial"
	rel="stylesheet" />
<link rel="stylesheet" href="resource/css/login.css" type="text/css"
	media="all">
<link rel="stylesheet" href="resource/css/fonts.css" type="text/css"
	media="all" />
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
</head>

<script type="text/javascript" src="resource/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
/*
$(function () {
	
 	var result = ${MSG};
	
	if( result == "0" ) {
		$("#result").show();
	}
})*/


</script>

<body>
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
				<!-- <span class="icon icon-plane-departure"></span> -->
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
		<div id="loginpage" class="container">
			<div class="title">
				<h2><span style="color: skyblue;">로그인이 필요합니다</span></h2>
				
				<div id="loginform">
					<form name="login" action="login.do" method="post">
						<div id="loginTop">
							Userid<br>
							<input type="text" id="userid" name="mem_id" placeholder="아이디를 입력하세요." required="required"><br>
						</div>
						<div id="loginMiddle">
							Password<br>
							<input type="password" id="password" name="mem_pw" placeholder="패스워드를 입력하세요." required="required">
						</div>
						
						<p id="result">
							<span style="color: red; font-size: 14px; padding-top: 1em;">${MSG}</span>
						</p>
						
						<div id="loginLower">
							<div id="findBtn">
								<input type="button" id="findIDPW" value="ID/PW찾기" onclick="document.location.href='findIDPW'"/>
							</div>
							<div id="loginBtn">
								<input type="submit" id="loginChk" value="로그인"/><br>
							</div>
						</div>
					</form>
					
				</div>
			</div>
		</div>
	</div>
	
	<!--================================== 하단부 =====================================  -->
	<div id="footer">
		<div class="container">
			<div class="fbox1">
				<span class="icon icon-map-marker"></span> 
				<span>1234 Fictional Road Suite #789 <br />Nashville TN 00000</span>
			</div>
			<div class="fbox1">
				<span class="icon icon-phone"></span> <span> Telephone: +1800 123 4657 </span>
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
