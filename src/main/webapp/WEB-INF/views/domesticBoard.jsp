<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
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
<link rel="stylesheet" href="resource/css/board.css" type="text/css"
	media="all" />
<link rel="stylesheet" href="resource/css/fonts.css" type="text/css"
	media="all" />
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	
<style type="text/css">
* {
	font size: 9pt;
}

p {
	width: 1000px;
	text-align: right;
}

table therd tr td {
	background-color: gray;
}

td#title {
	text-align: left;
}
</style>

<!-- 페이징 버튼 css -->	
<link rel="stylesheet" href="resource/css/paging.css" type="text/css"
	media="all" />
	
<!-- 제이쿼리, 페이징 스크립트 -->
<script type="text/javascript" src="resource/js/jquery-3.3.1.js"></script>
<script src="resource/js/pageset.js"></script>

<!-- DB에서 가져온 값으로 콤보박스 자동 선택하기 -->
<script type="text/javascript">
$(document).ready(function(){
	$("select option[value = '${serDto.searchWhat}']").prop("selected", true);
});
</script>
<!-- DB에서 가져온 값으로 콤보박스 자동 선택하기 -->

</head>
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
		<div id="domesticpage" class="container">
			<div class="title">
				<h2>
					<span style="color: skyblue;">Domestic Guide ෆ</span>
				</h2>

				<!--================================== 검색 =====================================  -->
				<p id="searcharea">	
					<select id="searchType" name="searchType" >
						<option value="all" >전체검색</option>
						<option value="subject">제목</option>
						<option value="writer">작성자</option>
						<option value="area">지역명</option>
					</select>
					
					<input type="text" name="searchText" id="searchText" value="${serDto.searchTxt}" />
					<input type="button" id="btnSearch" onclick="setSearchText(${pageDto.pageCnt+1},${pageDto.pageCnt});" value="검색">
				</p>
				<!--============================================================================  -->

				<!-- 게시판 리스트 -->
				<table
					style="border: 0px; width: 100%; height: 100px: margin:auto; text-align: center;">
					<colgroup>
						<col width="80" />
						<col width="500" />
						<col width="100" />
						<col width="100" />
						<col width="80" />
					</colgroup>

					<thead>
						<tr id="cmmntyThead">
							<th class="cmmntyTh">지역</th>
							<th class="cmmntyTh">제목</th>
							<th class="cmmntyTh">작성자</th>
							<th class="cmmntyTh">작성일</th>
							<th class="cmmntyTh">조회수</th>
						</tr>
					</thead>

					<tbody id="selCont">
						<c:forEach  var="dto" items="${list}" varStatus="status">	
							<c:if test="${'9' ne CmonDto.bod_delchk}">	
								<tr class="selCont" id="selCont">
									<td>${dto.trvArea}</td>
						 			<td class="titletd">
										<c:if test="${MEM_NUM != null and '9' ne MEM_LVL}">
											<a href="domesticContent.do?value=${dto.textNo}">${dto.title}</a>
										</c:if>
										<c:if test="${MEM_NUM == null or '9' eq MEM_LVL}">
											<a href="login" >${dto.title}</a>
										</c:if>
									</td>
									<td class="tabletd">${dto.mbrName}</td>
									<td class="tabletd">${dto.date}</td>
									<td class="tabletd">${dto.cnt}</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
				
				
				<!-- 게시글 보기 위해 글 번호 전달 = 스크립트의 함수 이용하여 set--> 
				<form action="./domesticContent.do" method="post" id='viewContent'>
		            <input type='hidden' name='textNo' id='textNo' value='${dto.textNo}'>   
		        </form>
				
		
				<!--================================== 페이징 =====================================  -->
				<div id="divLower">
					<div id="divPage">
						<ul class="pagination">
							<c:if test="${pageDto.pageStartNum ne 1}">
								<!--맨 첫페이지 이동 -->
								<li><a
									onclick='pagePre(${pageDto.pageCnt+1},${pageDto.pageCnt});'>«</a></li>
								<!--이전 페이지 이동 -->
								<li><a
									onclick='pagePre(${pageDto.pageStartNum},${pageDto.pageCnt});'>‹</a></li>
							</c:if>
	
							<!--페이지번호 -->
							<c:forEach var='i' begin="${pageDto.pageStartNum}"
								end="${pageDto.pageLastNum}" step="1">
								<li class='pageIndex${i}'><a onclick='pageIndex(${i});'>${i}</a></li>
							</c:forEach>
	
							<c:if test="${pageDto.lastChk}">
								<!--다음 페이지 이동 -->
								<li><a
									onclick='pageNext(${pageDto.pageStartNum},${pageDto.total},${pageDto.listCnt},${pageDto.pageCnt});'>›</a></li>
								<!--마지막 페이지 이동 -->
								<li><a
									onclick='pageLast(${pageDto.pageStartNum},${pageDto.total},${pageDto.listCnt},${pageDto.pageCnt});'>»</a></li>
							</c:if>
						</ul>
					</div>
				
					<div id="btnWrite">
						<c:if test="${MEM_NUM != null and '9' ne MEM_LVL}">
							<a href="domesticWrite" id="insertPost" class="button">글쓰기</a>
						</c:if>
						<c:if test="${MEM_NUM == null or '9' eq MEM_LVL}">
							<a href="login" id="insertPost" class="button">글쓰기</a>
						</c:if>
					</div>
				
				</div>
				<form action="domesticBoard.do" method="post" id='frmPaging'>
		            <!--출력할 페이지번호, 출력할 페이지 시작 번호, 출력할 리스트 갯수 -->
		            <input type='hidden' name='index' id='index' value='${pageDto.index}'>
		            <input type='hidden' name='pageStartNum' id='pageStartNum' value='${pageDto.pageStartNum}'>
		            <input type='hidden' name='listCnt' id='listCnt' value='${pageDto.listCnt}'>
					<input type='hidden' name='searchWhat' id='searchWhat' value='${serDto.searchWhat}'>
					<input type='hidden' name='searchTxt' id='searchTxt' value='${serDto.searchTxt}'>
				</form>
				<!--============================================================================  -->
 			
			</div>
		</div>
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
