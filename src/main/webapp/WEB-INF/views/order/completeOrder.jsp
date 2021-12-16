<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/order/comporder.css">
<script src="${pageContext.request.contextPath}/resources/js/incl/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/order/comporder.js" defer></script>
<title>영수증</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/incl/header.jsp"></jsp:include>

<section id="mainContainer">

  <div id="userInfoWrap">
    <p class="employeeInfo userInfoCom">PJCCAFE / 대구 / Manager</p>
    <p class="nowDate userInfoCom">2021-12-08</p>
  </div>
  
  <div id="allOrderWrap">
    <div id="receiptWrap">
      <div id="loginLogoWrap">
        <h2 id="cafeLogo">PJC Cafe</h2>
      </div>
      <div id="storeInfoWrap">
        <div class="storeInfo">
          <p>[지점] : 대구</p>
        </div>
        <div class="orderInfo">
          <p class="orderNumber">[주문번호] : <span>${sales.orderNumber }</span></p>
          <p class="todayDate">[주문날짜] : <span><fmt:formatDate value="${sales.regDate}" pattern="yyyy-MM-dd"/></span></p>
          <c:if test="${sales.place == 'I'.charAt(0)}">
         	 <p class="place">[포장여부] : 매장</p>
          </c:if>
          <c:if test="${sales.place == 'O'.charAt(0)}">
          	<p class="place">[포장여부] : 포장</p>
          </c:if>
          
        </div>
      </div>
      <div id="orderListWrap">
        <h3>주문내역</h3>
        <table class="orderListTable">
          <tr>
            <th>상품명</th>
            <th>단가</th>
            <th>수량</th>
            <th>금액</th>
          </tr>
          <c:forEach var="mItems" items="${sales.order }">
          	<tr>
          		<td>${mItems.menu.menuName }</td>
          		<td>${mItems.quantity }</td>
          		<td><fmt:formatNumber value="${mItems.menu.menuPrice }" pattern=",###" type="currency" /></td>
          		<td><fmt:formatNumber value="${mItems.menu.menuPrice * mItems.quantity }" pattern=",###" type="currency" /></td>
          	</tr>
          </c:forEach>
          <tr class="borderLine">
            <td colspan="3">판매금액</td>
            <td class="rightSideWrap"><fmt:formatNumber value="${sales.amount + sales.usePoint}" pattern=",###" type="currency" /></td>
          </tr>
          <tr>
            <td colspan="3">사용한 포인트</td>
            <td class="rightSideWrap"><fmt:formatNumber value="${sales.usePoint }" pattern=",###" type="currency" /></td>
          </tr>
          <tr>
            <td colspan="3">합계 금액</td>
            <td class="rightSideWrap"><fmt:formatNumber value="${sales.amount }" pattern=",###" type="currency" /></td>
          </tr>
        </table>
      </div>
      <div id="userInfoTableWrap">
        <h3>포인트내역</h3>
        <table class="userInfoTable">
          <tr>
            <th>고객명:</th>
            <c:if test="${sales.user.id == 1 }"><td>/</td></c:if>
            <c:if test="${sales.user.id != 1 }"><td>${fn:substring(sales.user.customerName,0,1) }**</td></c:if>
          </tr>
          <tr>
            <th>휴대폰번호:</th>
            <c:if test="${sales.user.id == 1 }"><td>/</td></c:if>
            <c:if test="${sales.user.id != 1 }"><td>${fn:substring(sales.user.phone,0,6) }*****</td></c:if>
            
          </tr>
          <tr>
            <th>사용 포인트:</th>
            <c:if test="${sales.user.id == 1 }"><td>0</td></c:if>
            <c:if test="${sales.user.id != 1 }"><td><fmt:formatNumber value="${sales.usePoint }" pattern=",###" type="currency" /></td></c:if>
            
          </tr>
          <tr>
            <th>적립된 포인트:</th>
            <c:if test="${sales.user.id == 1 }"><td>0</td></c:if>
            <c:if test="${sales.user.id != 1 }"><td><fmt:formatNumber value="${sales.amount * 0.1 }" pattern=",###" type="currency" /></td></c:if>
            
          </tr>
          <tr>
            <th>남은 포인트:</th>
            <c:if test="${sales.user.id == 1 }"><td>0</td></c:if>
            <c:if test="${sales.user.id != 1 }"><td><fmt:formatNumber value="${sales.user.point }" pattern=",###" type="currency" /></td></c:if>
            
          </tr>
          
        </table>
      </div>
      <div id="comporderBtnWrap">
        <form action="order" method="get" >
        <button type="submit" class="orderListBtnCom orderListCommitBtn">확인</button>
        </form>
        <form action="order" method="post" >
        <input type="hidden" name="leastOrderNumber" value="${sales.orderNumber }"/>
        <button type="submit" class="orderListBtnCom orderListCancelBtn">취소</button>
       	</form>
      </div>
    </div>
  </div>

</section>
  

</body>
</html>