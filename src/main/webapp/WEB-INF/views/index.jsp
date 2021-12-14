<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PJC Cafe</title>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/incl/index.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/incl/header.jsp" %>
<section id="indexContent">
	<div id="orders">
		<h2 class="headerMent">주문 현황</h2>
		<form action="addSales" method="post">
		<input class="hidden" type="submit" value="완료" />
			<div id="salesItemWrap">
				<c:forEach var="salesOrder" items="${sales}">
					<ul class="salesItem">
						<li class="salesNumber">
							주문번호 : ${salesOrder.key}
							<input type="radio" name="orderNumber" id="salesOrder${salesOrder.key}" value="${salesOrder.key}" />
							<label for="salesOrder${salesOrder.key}"></label>
						</li>
						<li class="salesMenu">주문메뉴 : </li>
						<c:forEach var="product" items="${salesOrder.value.order}">
							<li>${product.menu.menuName} ${product.quantity}</li>
						</c:forEach>
						<li class="in_out">
							<c:choose>
								<c:when test="${salesOrder.value.place eq 'I'.charAt(0)}">
									매장
								</c:when>
								<c:otherwise>
									테이크 아웃
								</c:otherwise>
							</c:choose>
						</li>
						<li class="salesDate">
							<fmt:formatDate value="${salesOrder.value.regDate}" pattern="yy년 MM월 dd일 HH시 mm분"/>
						</li>
					</ul>
				</c:forEach>
			</div>
			<input class="hidden" type="submit" value="완료"/>
		</form>
		<script>
			$("input[name=orderNumber]").click(function() {
				$("input[value=완료]").trigger("click");
			})
		</script>
	</div>
	<section id="orderCompletedContent">
	<form>
		<h3 class="headerMent">픽업 완료 목록</h3>
		<ul>
		<c:if test="${not empty compSales}">
			<c:forEach var="compSalesItem" items="${compSales}">
				<li>
					<p class="orderN">${compSalesItem.orderNumber} : </p>
					<p class="shortening">
						<c:forEach var="compProduct" items="${compSalesItem.order}" varStatus="status">
							${compProduct.menu.menuName} ${compProduct.quantity}
							<c:if test="${not status.last}">, </c:if>
						</c:forEach>
					</p>
					<p class="totalP"> / <fmt:formatNumber value="${compSalesItem.getTotalPrice()}" pattern=",###" type="currency" currencySymbol="" /> 원</p>
					<p class="regD">/ ${compSalesItem.regDate}</p>
					<input class="hidden" id="compSales${compSalesItem.orderNumber}" type="radio" value="${compSalesItem.orderNumber}" />
					<label for="compSales${compSalesItem.orderNumber}"></label>
				</li>
			</c:forEach>
		</c:if>
		</ul>
		<input type="submit" value="정산하기"/>
	</form>
	</section>
	<script>
	$("#orderCompletedContent input[type=radio] + label").click(function() {
		popupSalesView();
	})
	function popupSalesView() {
		let windowWidth = window.screen.width;
		let windowHeight = window.screen.height;
		
		let popupX = (windowWidth/2) - 250;
		let popupY = (windowHeight/2) - 250;
		
		let popUpdateMenuUrl = getContextPath() + "/menu/popSalesView";
		let popUpdateMenuOption = "width=500px, height=500px, top=" + popupY + "px, left=" + popupX + "px";
		let popUpdateMenuTitle = "영수증 보기";
		
		window.open(popUpdateMenuUrl, popUpdateMenuTitle, popUpdateMenuOption);
	}
	</script>
</section>
</body>
</html>