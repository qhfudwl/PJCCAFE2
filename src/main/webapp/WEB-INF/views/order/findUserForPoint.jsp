<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/order/order_popup.css">
<script src="${pageContext.request.contextPath}/resources/js/incl/jquery-3.6.0.min.js"></script>
<title>고객정보찾기</title>
</head>
<body>
<div class="popUpContainerWrap">
	<div class="popUpTitleWrap">
		<h1>고객정보찾기</h1>
	</div>
	<div class="popUpContainer">
		<div class="popUpSearchWrap">
      <h2>휴대폰번호입력</h2>
			<input type="text" name="popUpPhoneValue" class="popUpInputPhoneArea" placeholder="숫자만입력해주세요"/>
		</div>
		<div class="popUpBtnWrap">
      <button type="button" class="btnCom searchBtn">검색</button>
			<button type="button" class="btnCom subBtnCom joinBtn">회원가입</button>
			<button type="button" class="btnCom subBtnCom cancelBtn">취소</button>
		</div>
	</div>
</div>
</body>
</html>