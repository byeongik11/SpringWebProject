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
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
</head>

<script type="text/javascript" src="resource/js/jquery-3.3.1.js"></script>

<script type="text/javascript">

$(function() {
	$("#chkpw").click(function() {

		var pw 	   = $("input[name='mem_pwchk']").val();
		var mem_pw = ${MBRDTO.mem_pw}
		
		if( pw == mem_pw ) {
			document.getElementById("pw").disabled = false;
			document.getElementById("name").disabled = false;
			document.getElementById("birth").disabled = false;
			document.getElementById("sex").disabled = false;
			document.getElementById("adres").disabled = false;
			document.getElementById("tel").disabled = false;
			document.getElementById("email").disabled = false;
			
		}
		else {
			alert("비밀번호가 일치하지 않습니다.");
			$("input[name='mem_pwchk']").focus();
			return false;
		}
		
	});
});

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
			<div class="title">
				<h2>
					<span style="color: skyblue;">My Page ෆ</span>
				</h2>
				
				<div id="mypageform">
					<form name="mypage" action="joinUpdate.do" method="post">
						<table id="mypagetable">
							<tr>
								<td class="mypagetitle">아이디</td>
								<td class="mypage"><input type="text" name="mem_id" id="id" value="${MBRDTO.mem_id}" disabled='disabled' size="50" style="height: 30px;"></td>
							</tr>
							<tr>
								<td class="mypagetitle">비밀번호</td>
								<td class="mypage"><input type="password" name="mem_pwchk" id="pwchk" size="50" style="height: 30px;"></td>
								<td class="mypage"><input type="button" id="chkpw" value="비밀번호확인" /></td>
							</tr>
							<tr>
								<td class="mypagetitle">비밀번호 변경</td>
								<td class="mypage"><input type="password" name="mem_pw" id="pw" value="${MBRDTO.mem_pw}" disabled="disabled" size="50" style="height: 30px;"></td>
							</tr>
							<tr>
								<td class="mypagetitle">이름</td>
								<td class="mypage"><input type="text" name="mem_name" id="name" value="${MBRDTO.mem_name}" readonly="readonly" disabled='disabled' size="50" style="height: 30px;"></td>
							</tr>
							<tr>
								<td class="mypagetitle">생년월일</td>
								<td class="mypage"><input type="text" name="mem_birth" id="birth" value="${MBRDTO.mem_birth}" readonly="readonly" disabled='disabled' size="50" style="height: 30px;"></td>
							</tr>
							<tr>
								<td class="mypagetitle">성별</td>
								<c:if test="${ '1' eq MBRDTO.mem_sex}" >
									<td class="mypage"><input type="text" name="mem_sex" id="sex" value="남" disabled='disabled' readonly="readonly" size="50" style="height: 30px;"></td>
								</c:if>
								<c:if test="${ '2' eq MBRDTO.mem_sex}" >
									<td class="mypage"><input type="text" name="mem_sex" id="sex" value="여" disabled='disabled' readonly="readonly" size="50" style="height: 30px;"></td>
								</c:if>
							</tr>
							<tr>
								<td class="mypagetitle">주소</td>
								<td class="mypage"><input type="text" name="mem_adres" id="adres" value="${MBRDTO.mem_adres}" disabled='disabled' size="50" style="height: 30px;"></td>
							</tr>
							<tr>
								<td class="mypagetitle">연락처</td>
								<td class="mypage"><input type="text" name="mem_tel" id="tel" value="${MBRDTO.mem_tel}" disabled='disabled' size="50" style="height: 30px;"></td>
							</tr>
							<tr>
								<td class="mypagetitle">메일주소</td>
								<td class="mypage"><input type="text" name="mem_email" id="email" value="${MBRDTO.mem_email}" disabled='disabled' size="50" style="height: 30px;"></td>
							</tr>
						</table>
						<div id="lower" align="center">
								<input type="submit" id="mbrupdate" value="회원수정" />
								<input type="button" id="mbrdelete" value="회원탈퇴" onclick="document.location.href='joinDelete.do'"/>
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
