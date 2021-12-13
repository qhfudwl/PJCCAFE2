<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/order/order_popup.css">
<script src="${pageContext.request.contextPath}/resources/js/incl/jquery-3.6.0.min.js"></script>
<title>고객선택</title>
</head>
<body>
<div class="popUpContainer">
	<div class="popUpTitleWrap">
		<h1>고객포인트추가</h1>
	</div>
	<div class="popUpContainer">
		<h2>휴대폰번호입력</h2>
		<input type="text" name="popUpPhoneValue" class="popUpPhoneValue"/>
		<button type="button">검색</button>
		<div class="popUpBtnWrap">
			<button type="button">회원가입</button>
			<button type="button">취소</button>
		</div>
	</div>
</div>


</body>
</html>