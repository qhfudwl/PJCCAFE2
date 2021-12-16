<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/order/order_popup.css">
<script
	src="${pageContext.request.contextPath}/resources/js/incl/jquery-3.6.0.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/order/order.js"
	defer></script>
<title>포인트사용</title>
</head>
<body>
	<div class="popUpContainerWrap">
		<div class="popUpTitleWrap">
			<h1>포인트사용</h1>
		</div> 
		<div class="popUpContainer">

			<div class="popUpUserPointWrap">
				<c:if test="${not empty userPoint }">
					<p>사용 가능한 포인트 : <span class="canUsePoint"><fmt:formatNumber value="${userPoint }" pattern=",###" type="currency" /></span></p>
				</c:if>
				<c:if test="${empty userPoint }">
					<p>사용 가능한 포인트 : <span class="canUsePoint">0</span></p>
				</c:if>
				<p>사용할 포인트 입력</p>
				<form id="userPointForm" onsubmit="searchSubmit();" action=""
					method="">
					<input type="text" name="popUpUserPoint"
						class="popUpInputUserPointArea" placeholder="숫자만입력해주세요" />
				</form>
			</div>
			<div class="popUpBtnWrap">
				<button type="button" class="btnCom subBtnCom usePointBtn">사용</button>
				<button type="button" class="btnCom subBtnCom cancelBtn">취소</button>
			</div>

		</div>
	</div>
</body>
</html>