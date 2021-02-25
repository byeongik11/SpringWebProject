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

<!-- 달력 스크립트 CSS -->
<link rel="stylesheet" type="text/css"
	href="resource/css/datedropper.css">

<!-- CSS 작업시 아래 default.css  -->
<link rel="stylesheet" href="resource/css/default.css" type="text/css"
	media="all">
<link rel="stylesheet" href="resource/css/abroadWrite.css"
	type="text/css" media="all">

<!-- 폰트 추가 작업시 fonts.css에서 작업 -->
<link rel="stylesheet" href="resource/css/fonts.css" type="text/css"
	media="all" />
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

<script type="text/javascript" src="resource/js/jquery-3.3.1.js"></script>

<script type="text/javascript">
	function re_comment(CMTNO) {

		var data = CMTNO; //댓글넘버

		/* alert(data); */

		var html = "<form id='commentForm' name='commentForm' method='post'>";
		html += "<input type='hidden' id='cmt_no' name='cmt_no' value="+data+">";
		html += "<div class='cmtbody2'>";
		html += "<div class='leftc6'>";
		html += "<textarea id='comment' name='cmt_text'></textarea>";
		html += "</div>";
		html += "<div class='rightc6'>";
		html += "<a onclick='fn2_comment(" + $
		{
			boardDto.textNo
		}
		+")' class='button'>등록</a>";
		html += "</div>";
		html += "</div>";
		html += "</form>";

		$("#reCmt").html(html);

	}

	function fn2_comment(bod_no) {

		var cmt_text = $("#comment").val();
		var cmt_no = $("#cmt_no").val();

		/* 	alert(bod_no);
		 alert(cmt_no);
		 alert(cmt_text); */

		var allData = {
			"bod_no" : bod_no,
			"cmt_no" : cmt_no,
			"cmt_text" : cmt_text
		};

		$.ajax({

			url : "<c:url value='reCmtInsert.do' />",
			type : 'POST',
			data : allData,
			success : function(data) {
				if (data == "success") {

					getCommentList();

					$("#comment").val("");

				}
			},
			error : function() {
				alert("에러발생");
				self.close();
			}

		});

	}

	function fn_comment(bod_no) {

		$.ajax({

			type : 'POST',
			url : "<c:url value='cmtInsert.do' />",
			data : $("#commentForm").serialize(),
			success : function(data) {
				if (data == "success") {

					getCommentList();

					$("#comment").val("");

				}
			},
			error : function(request, status, error) {
				//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}

		})

	}

	$(function() {

		getCommentList();

	})

	function getCommentList() {

		$
				.ajax({

					type : 'GET',
					url : "<c:url value='cmtSlt.do' />",
					dataType : "json",
					data : $("#commentForm").serialize(),
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					success : function(data) {

						var html = "";
						var cCnt = data.length;

						/* alert(data); */

						if (data.length > 0) {

							for (i = 0; i < data.length; i++) {

								/* alert(data[i].CMTNO); */

								html += "<div class='cmtbody'>";
								html += "<span class='leftc2'>" + data[i].MEMID
										+ " ( " + data[i].MEMNAME + " )"
										+ "</span>";
								/* html += "<span class='middlec2'>ㅣ</span>"; */
								html += "<span class='rightc2'>"
										+ data[i].CMTDATE + "</span>";
								/* html += "<span class='rightc3'><a href='#' onclick='re_comment('data[i].BODNO')' >답글</a></span>"; */
								/* html += "<span class='rightc3'><a href='#' onclick='re_comment("+data[i].BODNO+"|"+data[i].CMTNO+")' >답글</a></span>"; */
								html += "<span class='rightc3'><a href='#' onclick='re_comment("
										+ data[i].CMTNO + ")' >답글</a></span>";
								html += "</div>";
								html += "<div class='cmtbody'>";
								html += "<span class='leftc3'>"
										+ data[i].CMTTEXT + "</span>";
								html += "</div>";
								html += "<div id='reCmt'>";
								html += "</div>";
							}

						} else {

							html += "<div class='cmtbody'>";
							html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
							html += "</table></div>";
							html += "</div>";

						}

						$("#cCnt").html(cCnt);
						$("#area").html(html);

					},
					error : function(request, status, error) {

					}

				});
	}
</script>


</head>
<body>
	<!--================================== 상단부 =====================================  -->
	<div id="header-wrapper">
		<div id="header" class="container">

			<!-- 로그인버튼 -->
			<div id="login">
				<c:if test="${MEM_NUM != null and '9' ne MEM_LVL}">
					<a href="mainlogout" class="button">Logout</a>
					<a href="myPage.do" class="button">MaPage</a>
					<br>
					<p id="msg">${MEM_NAME}님환영합니다.</p>
				</c:if>
				<c:if test="${MEM_NUM == null or '9' eq MEM_LVL}">
					<a href="login" class="button">Login</a>
					<a href="mbrJoin" class="button">Join</a>
					<br>
					<p id="msg">로그인 바랍니다.</p>
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
	<div class="alines">
		<div id="abwrap" class="wrapper">
			<div class="titleWrite">
				<h2>
					<span style="color: skyblue;">Go Together ෆ</span>
				</h2>
			</div>

			<div class="readForm">
				<c:if test="${boardDto != null }">
					<div class="row1">
						<div class="left1">제목</div>
						<div class="middle1">${boardDto.title}
							<table style="float: right;">
								<tr>
									<td>조회수</td>
									<td>${boardDto.cnt}</td>
								</tr>
							</table>
						</div>
					</div>

					<div class="row">
						<div class="left">작성자</div>
						<div class="middle">${boardDto.mbrName}</div>
					</div>

					<div class="row">
						<div class="left">작성일</div>
						<div class="middle">${boardDto.date}</div>
					</div>
				</c:if>

				<c:if test="${detailDto != null }">
					<div class="row">
						<div class="left">여행 기간</div>
						<div class="middle">${detailDto.startDate}~
							${detailDto.endDate}</div>
					</div>

					<div class="row">
						<div class="left">여행지</div>
						<div class="middle">
							<table>
								<tr>
									<td>대분류</td>
									<td class="innerTd">: ${detailDto.trvArea}</td>
								</tr>
								<tr>
									<td>세부지역</td>
									<td class="innerTd">: ${detailDto.trvAreaDtail}</td>
								</tr>
							</table>
						</div>
						
					</div>

					<div class="row">
						<div class="left">경비</div>
						<div class="middle">${detailDto.cost}</div>
					</div>

					<div class="row">
						<div class="left">최대 인원</div>
						<div class="middle">${detailDto.max} 명</div>
					</div>

					<div class="row">
						<div class="left">이동 수단</div>
						<div class="middle">${detailDto.trans}</div>
					</div>
				</c:if>

				<c:if test="${boardDto != null }">
					<div class="row">
						<div class="left">여행일정</div>
						<div class="middle">${boardDto.content}</div>
					</div>
				</c:if>

				<c:if test="${detailDto != null }">
					<div class="row">
						<div class="left">기타사항</div>
						<div class="middle">${detailDto.etc}</div>
					</div>
				</c:if>

				<c:if test="${mbrDto != null }">
					<div class="row">
						<div class="left">동행 모집자<br>정보</div>
						<div class="middle">
							<table>
								<tr>
									<td>이름</td>
									<td class="innerTd">: ${mbrDto.mem_name}</td>
								</tr>
								<tr>
									<td>전화번호</td>
									<td class="innerTd">: ${mbrDto.mem_tel}</td>
								</tr>
								<tr>
									<td>이메일</td>
									<td class="innerTd">: ${mbrDto.mem_email}</td>
								</tr>
							</table>
						</div>
					</div>
				</c:if>

				<div class="row">
					<div class="left">동행자 신청<br>현황</div>
					<div class="middle">
						<table>
							<tr>
								<td>현재 신청 인원 : ${JOINCNT} 명</td>
									<c:if test="${MEM_NUM != boardDto.mbrCode}">
										<c:if test="${JOIN == false and detailDto.max > JOINCNT}">
											<td><input type="button" id="groupJoin" value="참가 신청"
												onclick="document.location.href='groupJoinInsert.do?value=${boardDto.textNo}&mCode=${MEM_NUM}'">
											</td>
										</c:if>
										<c:if test="${JOIN == true}">
											<td><input type="button" id="groupJoin" value="참가 취소"
												onclick="document.location.href='groupJoinDelete.do?value=${boardDto.textNo}&mCode=${MEM_NUM}'">
											</td>
										</c:if>
									</c:if>
							</tr>
						</table>
					</div>
				</div>

					<!-- 댓글 -->
					<div class="comment">

						<div class="cmthead">
							<span class="leftc1">댓글 <span id="cCnt"></span><span>건</span></span>
						</div>

						<form id="commentListForm" name="commentListForm" method="post">
							<div id="area"></div>
						</form>

						<form id="commentForm" name="commentForm" method="post">
							<div class="cmtbody2">
								<div class="leftc6" style="text-align: left;">
									<textarea id="comment" name="cmt_text"></textarea>
								</div>
								<div class="rightc6">
									<a onclick="fn_comment('${boardDto.textNo}')" id="reIst"
										class="button">등록</a>
								</div>
							</div>
							<input type="hidden" id="b_code" name="bod_no"
								value="${boardDto.textNo}">
						</form>

					</div>

					<div class="cmmntyLower1">
						<c:if test="${MEM_NUM == boardDto.mbrCode}">
							<input type="button" id="updatePost" value="수정"
								onclick="document.location.href='updateAbroad?value=${boardDto.textNo}'">
							<input type="button" id="deletePost" value="삭제"
								onclick="document.location.href='deletePost.do?value=${boardDto.textNo}'">
						</c:if>
					</div>

					<div class="cmmntyLower2">
						<input type="button" id="selectAll" value="목록"
							onclick="document.location.href='groupBoard.do'">
					</div>
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
					<span class="icon icon-phone"></span> <span> Telephone:
						+1800 123 4657 </span>
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
