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
<link rel="stylesheet" href="resource/css/default.css" type="text/css"
	media="all">
<link rel="stylesheet" href="resource/css/fonts.css" type="text/css"
	media="all" />
<link rel="stylesheet" href="resource/css/info.css" type="text/css"
	media="all" />
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap&subset=korean" rel="stylesheet">
	
</head>

<script type="text/javascript" src="resource/js/jquery-3.3.1.js"></script>

<script type="text/javascript">

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
				<h1>
					<a href="main">G a y a g e</a>
				</h1>
			</div>
			
			<div id="menu">
				<ul>
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
		<div id="mypage" class="container">
			<div class="titleWrite">
				<h4>
					<span style="color: skyblue;">Introduce Gayage ෆ</span>
				</h4>
			</div>

				<div>
				<h3 id="info" style="text-align: center;">
				"gayage"는 패키지여행과는 다르게 하나부터 열까지 많은 준비가 필요한 자유여행객을 위한 커뮤니티입니다.<br><br>
				여행지를 다녀온 회원들이 직접 등록한 정보로 정확성을 높이고,<br><br> 
				계획을 짤 시간이 없거나 처음부터 계획짜는 것이 막막한 상황에 참고할 수 있도록 여행코스를 추천해줍니다.<br><br>
				또한 같이 여행할 파트너도 모집가능합니다.<br><br>
				"gayage"는 회원분들의 성공적인 여행을 만드는것이 주된 목적입니다.<br><br>
				</h3>
				<br>
				</div>
				
			
				<table class="infoBox">
				<tr>
					<td class="infoBox1">
						<h4 class="textLeft">국내여행후기</h4>
					</td>	
					
					<td class="infoBox1">	
						<img class="imgLeft" src="resource/images/info1.jpg" alt="">
					</td>	
						
					<td class="infoBox1">	
						<img class="imgRight" src="resource/images/info2.jpg" alt="">
					</td>	
					
					<td class="infoBox1">	
						<h4 class="textRight">해외여행후기</h4>
					</td>
				</tr>	
				
				<tr>
				
					<td class="infoBox1">
						<h4 class="textLeft">여행커뮤니티</h4>
	
					<td class="infoBox1">	
						<img class="imgLeft" src="resource/images/info3.jpg" alt="">
					</td>	
						
					<td class="infoBox1">	
						<img class="imgRight" src="resource/images/info4.jpg" alt="">
					</td>
						
					<td class="infoBox1">	
						<h4 class="textRight">여행동행자모집</h4>
					</td>
				</tr>
				</table>
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
