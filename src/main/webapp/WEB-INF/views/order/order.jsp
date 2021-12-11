<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>\
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/stylesheet_link.jsp"%>
<script src="${pageContext.request.contextPath}/resources/css/order/order.css"></script>
<script src="${pageContext.request.contextPath}/resources/js/incl/jquery-3.6.0.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/incl/header.jsp"></jsp:include>
	<section id="mainContainer">
		<div id="userInfoWrap">
			<p class="employeeInfo userInfoCom">PJCCAFE / 대구 / Manager</p>
			<p class="nowDate userInfoCom">2021-12-08</p>
		</div>

		<div id="allOrderWrap">
			<div id="orderWrap">
				<!--
    
      (좌) 주문내역
      
      -->
				<div class="leftSideWrap halfSideWrap">
					<h2 class="hidden">주문내역</h2>
					<div class="orderListTableWrap">
						<table class="orderListTable">
							<tr>
								<th>상품명</th>
								<th>수량</th>
								<th>단가</th>
								<th>금액</th>
							</tr>
							<tr>
								<td>아메리카노</td>
								<td>1</td>
								<td>3,000</td>
								<td>3,000원</td>
							</tr>
							<tr>
								<td>아메리카노</td>
								<td>1</td>
								<td>3,000</td>
								<td>3,000원</td>
							</tr>
						</table>
					</div>
					<div class="leftSideFuncWrap">
						<div class="orderListBtnWrap">
							<div class="orderListBtnFirst">
								<button type="button" class="orderListBtnCom orderListBtn">↓</button>
								<button type="button" class="orderListBtnCom orderListBtn">↑</button>
								<button type="button" class="orderListBtnCom">수량:5</button>
								<button type="button" class="orderListBtnCom">9,000원</button>
							</div>
							<div class="orderListBtnSecond">
								<button type="button" class="orderListBtnCom orderListBtn">포장</button>
								<button type="button" class="orderListBtnCom orderListBtn">포인트</button>
								<button type="button" class="orderListBtnCom orderListBtn">취소</button>
							</div>
						</div>
						<div class="customerInfoWrap">
							<table class="customerInfoTable">
								<tr>
									<th>고객명</th>
									<td>홍길동</td>
									<th>전화번호</th>
									<td>010-1234-7891</td>
								</tr>
								<tr>
									<th>포인트</th>
									<td>2,000점</td>
									<th>생년월일</th>
									<td>1990년1월1일</td>
								</tr>
							</table>
						</div>
						<div class="orderPriceWrap">
							<table class="orderPriceTable">
								<tr>
									<th>실판매금액</th>
									<td class="totalPriceBeforePoint">9,000원</td>
								</tr>
								<tr>
									<th>포인트금액</th>
									<td class="usePoint">0원</td>
								</tr>
								<tr>
									<th>받을 금액</th>
									<td><span class="totalPrice">9,000원</span></td>
								</tr>

							</table>
						</div>
					</div>
				</div>

				<!--
    
      (우) 주문선택
      
      -->
				<div class="rightSideWrap halfSideWrap">
					<h2 class="hidden">주문</h2>
					<div class="menuListWrap oneRow">
						<h3 class="hidden">주요메뉴</h3>
						<ul>
							<li><a href="#" class="menuItemCom">인기메뉴</a></li>
							<li><a href="#" class="menuItemCom">신메뉴</a></li>
							<li><a href="#" class="menuItemCom">전체메뉴</a></li>
						</ul>
					</div>
					<div class="menuNameWrap">
						<h3 class="hidden">메뉴종류</h3>
						<div class="menuCategoryWrap oneRow">
							<ul>
								<li><a href="#" class="menuItemCom">커피</a></li>
								<li><a href="#" class="menuItemCom">음료</a></li>
								<li><a href="#" class="menuItemCom">푸드</a></li>
							</ul>
						</div>

						<div class="menuSubCategoryWrap oneRow">
							<ul>
								<li><a href="#" class="menuItemCom">뜨거운음료</a></li>
								<li><a href="#" class="menuItemCom">차가운음료</a></li>
								<li><a href="#" class="menuItemCom"></a></li>
							</ul>
						</div>
					</div>
					<div class="menuItemsWrap oneRow">
						<h3 class="hidden">메뉴</h3>
						<ul>
							<li><a href="#">
									<div class="menuImgWrap">
										<img src="IceAmericano.jpg" alt="menuImg">
										<div class="menuNamePriceWrap">
											<p class="menuName">아메리카노</p>
											<p class="menuPrice">3,000원</p>
										</div>
									</div>
							</a></li>
						</ul>
					</div>
					<div class="funcButtonsWrap oneRow">
						<h3 class="hidden">기능</h3>
						<ul>
							<li><a href="#" class="menuItemCom funcButtonCom">전체취소</a></li>
							<li><a href="#" class="menuItemCom funcButtonCom"></a></li>
							<li><a href="#" class="menuItemCom funcButtonCom">고객선택</a></li>
							<li><a href="#" class="menuItemCom funcButtonCom">주문하기</a></li>
						</ul>
					</div>

				</div>
			</div>
		</div>
	</section>

</body>
</html>