<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/order/order_popup.css">
<script src="${pageContext.request.contextPath}/resources/js/incl/jquery-3.6.0.min.js"></script>
<title>고객조회</title>
</head>
<body>
<div class="popUpContainerWrap">
	<div class="popUpTitleWrap">
		<h1>고객선택</h1>
	</div>
	<div class="popUpContainer">
		<div class="popUpResultWrap">
      <div class="userListTableWrap">
        
        <table class="userListTable">
  							<tr class="tableheader">
  								<th>이름</th>
  								<th>휴대전화번호</th>
  								<th>생년월일</th>
  								<th>보유포인트</th>
  							</tr>
                <tr class="userList">
  								<td></td>
  								<td></td>
  								<td></td>
  								<td></td>
  							</tr>
                <tr class="userList">
  								<td></td>
  								<td></td>
  								<td></td>
  								<td></td>
  							</tr>
                <tr class="userList">
  								<td></td>
  								<td></td>
  								<td></td>
  								<td></td>
  							</tr>
              
  			</table>
      </div>
		</div>
		<div class="popUpBtnWrap">
			<button type="button" class="btnCom subBtnCom submitBtn">확인</button>
			<button type="button" class="btnCom subBtnCom cancelBtn">취소</button>
		</div>
	</div>
</div>
</body>
</html>