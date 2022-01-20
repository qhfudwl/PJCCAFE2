<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user/user_sales_list.css" />
<title>회원 거래내역 조회</title>
</head>
<body>
	<header><%@ include file="/WEB-INF/views/incl/header.jsp" %></header>
	<main>
		<section id="userSalesList">
			<h2>${user.customerName}회원님 거래내역</h2>
			<div id="userSaleslistWrap">
				<form method ="get" action="viewUserMain">
					<input type="submit" value="돌아가기" id="backbtn">
					<table id="userSaleslisttable">
						<tr><th class="orderNum">주문번호</th><th class="Amount">입금액</th><th class="usePoint">사용포인트</th><th class="addPoint">적립포인트</th><th class="buyDay">구매일</th></tr>
						<c:forEach var="slist" items="${slist}">
							<tr>
								<td class="orderNumin">${slist.orderNumber}</td>
								<td class="Amountin"><fmt:formatNumber value="${slist.amount}" pattern=",###" type="currency" currencySymbol="" /> 원</td>
								<td class="usePointin"><fmt:formatNumber value="${slist.usePoint}" pattern=",###" type="currency" currencySymbol="" /> P</td>
								<td class="addPointin"><fmt:formatNumber value="${slist.amount * 0.1}" pattern=",###" type="currency" currencySymbol="" /> P</td>
								<td class="buyDayin">${slist.regDate}</td>
							</tr>
						</c:forEach>
					</table>
				</form>
			</div>
		</section>
	</main>
</body>
</html>