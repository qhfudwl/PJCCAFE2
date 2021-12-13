<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PJC Cafe</title>
<%@ include file="incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/incl/index.css" />
</head>
<body>
<%@ include file="incl/header.jsp" %>
<section id="indexContent">
	<div id="order">
		<h2>주문 현황</h2>
		<form action="addSales" method="post">
		<input class="hidden" type="submit" value="완료" />
			<c:forEach var="salesOrder" items="${sales}">
				<ul>
					<li>
						주문번호 : ${salesOrder.key}
						<input type="radio" name="orderNumber" id="salesOrder${salesOrder.key}" value="${salesOrder.key}" />
						<label for="salesOrder${salesOrder.key}"></label>
					</li>
					<c:forEach var="product" items="${salesOrder.value.order}">
						<li>${product.menu.menuName} ${product.quantity}</li>
					</c:forEach>
					<li>
						<fmt:formatDate value="${salesOrder.value.regDate}" pattern="yy년 MM월 dd일 HH시 mm분"/>
						<input class="hidden" name="userId" type="text" value="${salesOrder.value.user.id}" />
						<input class="hidden" name="amount" type="text" value="${salesOrder.value.amount}" />
						<input class="hidden" name="usePoint" type="text" value="${salesOrder.value.usePoint}" />
						<input class="hidden" name="place" type="text" value="${salesOrder.value.place}" />
					</li>
				</ul>
			</c:forEach>
			<input type="submit" value="완료"/>
		</form>
		<script>
			$("input[name=orderNumber]").click(function() {
				$("input[value=완료]").trigger("click");
			})
		</script>
	</div>
	<section id="orderCompletedContent">
		<h3>주문 완료 목록</h3>
		<ul>
			<c:forEach var="compSalesItem" items="${compSales}">
				<li>
					${compSalesItem.orderNumber} : 
					<c:forEach var="compProduct" items="${order[compSalesItem.orderNumber]}" varStatus="status">
						${compProduct.menu.menuName} ${compProduct.quantity}
						<c:if test="${not status.last}">, </c:if>
					</c:forEach>
					<%-- / ${compSalesItem.getTotalPrice()}원 --%>
					 / ${compSalesItem.regDate}
				</li>
			</c:forEach>
		</ul>
		<input type="submit" value="정산하기"/>
	</section>
</section>
</body>
</html>