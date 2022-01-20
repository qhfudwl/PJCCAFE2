<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/order/order_popup.css">
<script src="${pageContext.request.contextPath}/resources/js/incl/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/order/order.js" defer></script>

<title>회원가입</title>
</head>
<body>
<div class="popUpContainerWrap">
	<div class="popUpTitleWrap">
		<h1>회원가입</h1>
	</div>
	<div class="popUpContainer">
	<div class="popUpJoinWrap ">
	
      <p>이름</p>
			<input type="text" name="joinNameArea" class="joinNameArea joinAreaCom" placeholder="한글 혹은 영문으로 입력해주세요"/>
      <p>휴대전화번호</p>
			<input type="text" name="joinPhoneArea" class="joinPhoneArea joinAreaCom" placeholder="숫자만 입력해주세요"/>
      <p>생년월일</p>
			<input type="text" name="joinBirthArea" class="joinBirthArea joinAreaCom" placeholder="8자 숫자로 입력해주세요"/>

		</div>
		<div class="popUpBtnWrap">
			<button type="button" class="btnCom subBtnCom joinSubmitBtn subBtnJoinCom" onclick="joinSubmit();">가입하기</button>
			<button type="button" class="btnCom subBtnCom cancelBtn subBtnJoinCom">취소</button>
		</div>
	</div>
</div>
</body>
</html>