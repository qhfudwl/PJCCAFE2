<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
          <p class="orderNumber">[주문번호] : <span>A01</span></p>
          <p class="todayDate">[주문날짜] : <span>2021년 12월 16일 12시 28분 33초</span></p>
          <p class="place">[포장여부] : 매장</p>
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
          <tr>
            <td>카페라떼</td>
            <td>1,000</td>
            <td>3</td>
            <td>3,000</td>
          </tr>


          <tr class="borderLine">
            <td colspan="3">판매금액</td>
            <td class="rightSideWrap">43,000</td>
          </tr>
          <tr>
            <td colspan="3">사용한 포인트</td>
            <td class="rightSideWrap">0</td>
          </tr>
          <tr>
            <td colspan="3">합계 금액</td>
            <td class="rightSideWrap">43,000</td>
          </tr>
        </table>
      </div>
      <div id="userInfoTableWrap">
        <h3>포인트내역</h3>
        <table class="userInfoTable">
          <tr>
            <th>고객명:</th>
            <td>김**</td>
          </tr>
          <tr>
            <th>휴대폰번호:</th>
            <td>010-1234-****</td>
          </tr>
          <tr>
            <th>사용 포인트:</th>
            <td>0</td>
          </tr>
          <tr>
            <th>적립된 포인트:</th>
            <td>100</td>
          </tr>
          <tr>
            <th>남은 포인트:</th>
            <td>1,000</td>
          </tr>
          
        </table>
      </div>
      <div id="comporderBtnWrap">
        <button type="button" class="orderListBtnCom">확인</button>
        <button type="button" class="orderListBtnCom">취소</button>
      </div>
    </div>
  </div>

</section>
  

</body>
</html>