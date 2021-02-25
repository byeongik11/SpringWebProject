<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!-- 페이지 타이틀 -->
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
<!-- <link rel="stylesheet" href="resource/css/joinWrite.css"
	type="text/css" media="all">
 -->
<!-- 폰트 추가 작업시 fonts.css에서 작업 -->
<link rel="stylesheet" href="resource/css/fonts.css" type="text/css"
	media="all" />
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

<!-- ckeditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/ckeditor/ckeditor.js"></script>
	
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
		
<script type="text/javascript">
    $(function(){
    	   CKEDITOR.replace('inputTip',{
    	      filebrowserUploadUrl: '${pageContext.request.contextPath}/adm/fileupload.do'                                    
    	   });
   	});
</script>

<!-- DB에서 가져온 값으로 콤보박스 자동 선택하기 -->
<script type="text/javascript">
$(document).ready(function(){
	$("#inputLoc option[value = '${detailDto.trvArea}']").prop("selected", true);
});
</script>
<!-- DB에서 가져온 값으로 콤보박스 자동 선택하기 -->


<!-- 날짜 선택 스크립트 -->
<script type="text/javascript">

  function inputDateComparison(obj) {
	  
	    // 날짜 입력 엘리먼트 ID는 startDate(시작일), endDate(종료일)로 동일해야 한다.
        var startDate = inputDateSplit(document.getElementById("inputSDate").value);    // 시작일
        var endDate = inputDateSplit(document.getElementById("inputEDate").value);        // 종료일
        
        var objDate = inputDateSplit(obj.value);    // 입력한 엘리먼트의 일자
        // 입력일을 확인하는 이유는 현재 작성한 일자가 시작일인지 종료일인지 확인하기 위해서이다.
        
        if(startDate == objDate && startDate > endDate) {

            alert("시작일이 종료일보다 이 후 일수는 없습니다.\n다시 선택하여 주시기 바랍니다.");
            obj.value = document.getElementById("inputEDate").value;
            obj.focus();
        } else if(endDate == objDate && endDate < startDate) {

            alert("종료일이 시작일보다 이 전 일수는 없습니다.\n다시 선택하여 주시기 바랍니다.");
            obj.value = document.getElementById("inputSDate").value;
            obj.focus();
        } else {
            return false;
        }
    }

    // 날짜형식에 "-"이 사용된 경우에 한하여 날짜값에서 "-" 기호를 제거한다.
    function inputDateSplit(obj) {



        var dateArray = obj.split("-");
        return dateArray[0] + dateArray[1] + dateArray[2];
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
		<div id="grouppage" class="container">
			<div class="title">
				<h2>
					<span style="color: skyblue;">Go Together ෆ</span>
				</h2>
			</div>
	
			<form class="form-groupWrite" method="post"	action="/WebProjectV201/groupUpdate.do">
			
				<input type="hidden" name="textNo" value="${boardDto.textNo}">
				<input type="hidden" name="gubun" value="3">
				<div id="writeForm">
					<table class="table table-bordered">
					    <tbody>
					            <tr>
					                <th class="tableth">제목</th>
					                <td class="tabletd"><input type="text" name="title" id="inputTitle" class="inputData" placeholder="제목을 입력해주세요" value="${boardDto.title}" required autofocus></td>
					            </tr>
					            <tr>
					                <th class="tableth">여행 일정</th>
					                <td class="tabletd"><input type="date" name="startDate" id="inputSDate" onChange="inputDateComparison(this);" value="${SDATE}" required autofocus> ~ 
					                <input type="date" name="endDate" id="inputEDate" onChange="inputDateComparison(this);" value="${EDATE}" required autofocus></td>
					            </tr>
					            <tr>
					                <th class="tableth">지역 선택</th>
					                <td class="tabletd"><select name="trvArea" class="inputData" id="inputLoc" name="Location" required autofocus>
					                		<optgroup label="국내"></optgroup>	
					                			<option value="서울"	>서울</option>
												<option value="부산"	>부산</option>
												<option value="대구"	>대구</option>
												<option value="인천"	>인천</option>
												<option value="광주"	>광주</option>
												<option value="대전"	>대전</option>
												<option value="울산"	>울산</option>
												<option value="경기"	>경기도</option>
												<option value="강원"	>강원도</option>
												<option value="충북"	>충청북도</option>
												<option value="충남"	>충청남도</option>
												<option value="전북"	>전라북도</option>
												<option value="전남"	>전라남도</option>
												<option value="경북"	>경상북도</option>
												<option value="경남"	>경상남도</option>
												<option value="제주"	>제주도</option>
											<optgroup label="해외"></optgroup>
												<option value="asia"		>아시아</option>
												<option value="europe"		>유럽</option>
												<option value="middleEast"	>중동</option>
												<option value="africa"		>아프리카</option>
												<option value="northAmerica">북아메리카</option>
												<option value="southAmerica">남아메리카</option>
												<option value="oceania"		>오세아니아</option>
										</select> </td>
					            </tr>
					            <tr>
					            	<th class="tableth">여행지</th>
					            	<td class="tabletd"><input type="text" name="trvAreaDtail" id="inputLocDtl" class="inputData" placeholder="상세지역 입력" value="${detailDto.trvAreaDtail}" required autofocus></td>
					            </tr>
					            <tr>
					            	<th class="tableth">여행경비</th>
					            	<td class="tabletd"><input type="text" name="cost" id="inputMoney" class="inputData" placeholder="여행 경비 입력" value="${detailDto.cost}" required autofocus>(단위 : 원)</td>
					            </tr>
					            <tr>
					                <th class="tableth">최대 인원</th>
					                <td class="tabletd"><input type="text"  name="max" id="inputMax" class="inputData" placeholder="최대인원 입력 " value="${detailDto.max}" required autofocus>명</td>
					            </tr>
					            <tr>
					                <th class="tableth">이동 수단</th>
					                <td class="tabletd"><textarea cols="10" rows="2" name="trans" id="inputTrans" class="taContent" placeholder="내용을 입력하세요. "  required autofocus>${detailDto.trans}</textarea></td>
					            </tr>
					            <tr>
					            	<th class="tableth">여행 일정</th>
					            	<td class="tabletd"><textarea cols="10" rows="5" name="content" id="inputTip" class="taContent" placeholder="내용을 입력하세요. " required autofocus>${boardDto.content}</textarea></td>
					            </tr>
					            <tr>
					                <th class="tableth">기타사항</th>
					                <td class="tabletd"><textarea cols="10" rows="5" name="etc" id="inputEtc" class="taContent" placeholder="내용을 입력하세요. "  required autofocus>${detailDto.etc}</textarea></td>
					            </tr>
					    </tbody>
					</table>
					
					<div class="cmmntyLower1">
					<c:if test="${MEM_NUM == boardDto.mbrCode}">
						<input type="submit" id="updatePost" value="수정" >
						<input type="button" id="backward" value="이전" onclick="document.location.href='groupContent.do?value=${boardDto.textNo}&mCode=${dto.mbrCode}'">
					</c:if>
					</div>
				
					<div class="cmmntyLower2">
						<input type="button" id="selectAll" value="목록" onclick="document.location.href='groupBoard.do'">
					</div>
					
				</div>
			</form>
	</div>
</div>
	<div id="footer">
		<div class="container">
			<div class="fbox1">
				<span class="icon icon-map-marker"></span> <span>1234
					Fictional Road Suite #789 <br />Nashville TN 00000
				</span>
			</div>
			<div class="fbox1">
				<span class="icon icon-phone"></span> <span> Telephone: +1800
					123 4657 </span>
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
