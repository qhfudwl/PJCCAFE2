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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/order/order.css">
<script src="${pageContext.request.contextPath}/resources/js/incl/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/order/order.js" defer></script>
<title>주문하기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/incl/header.jsp"></jsp:include>
	<section id="mainContainer">
		<div id="userInfoWrap">
			<p class="employeeInfo userInfoCom">PJCCAFE / 대구 / ${mPosition }</p>
			<p class="nowDate userInfoCom">${nowDate }</p>
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
						</table>
					</div>
					<div class="leftSideFuncWrap">
						<div class="orderListBtnWrap">
							<div class="orderListBtnFirst">
								<button type="button" class="orderListBtnCom orderListBtn orderListDownBtn">↓</button>
								<button type="button" class="orderListBtnCom orderListBtn orderListUpBtn">↑</button>
								<button type="button" class="orderListBtnCom orderListTotalNum">수량:0</button>
								<button type="button" class="orderListBtnCom orderListTotalPrice">0원</button>
							</div>
							<div class="orderListBtnSecond">
								<button type="button" class="orderListBtnCom orderListBtn orderListInOutBtn">포장</button>
								<button type="button" class="orderListBtnCom orderListBtn orderListPointBtn">포인트</button>
								<button type="button" class="orderListBtnCom orderListBtn orderListCancelBtn">취소</button>
							</div>
						</div>
						<div class="customerInfoWrap">
							<input type="hidden" class="orderCustId"/>
							<table class="customerInfoTable">
								<tr>
									<th>고객명</th>
									<td class="orderCustName" id="orderCustName"></td>
									<th>전화번호</th>
									<td class="orderCustPhone"></td>
								</tr>
								<tr>
									<th>생년월일</th>
									<td class="orderCustBirth"></td>
									<th>포인트</th>
									<td class="orderCustPoint"></td>
								</tr>
							</table>
						</div>
						<div class="orderPriceWrap">
							<table class="orderPriceTable">
								<tr>
									<th>실판매금액</th>
									<td class="totalPriceBeforePoint">0원</td>
								</tr>
								<tr>
									<th>포인트금액</th>
									<td class="usePoint">0원</td>
								</tr>
								<tr>
									<th>받을 금액</th>
									<td><span class="totalPrice">0원</span></td>
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
							<li><a href="#" class="menuItemCom menuItemCaltCom calSelected">인기메뉴</a></li>
							<li><a href="#" class="menuItemCom menuItemCaltCom">신메뉴</a></li>
							<li><a href="#" class="menuItemCom menuItemCaltCom totalMenuItem">전체메뉴</a></li>
						</ul>
					</div>
					<div class="menuNameWrap">
						<h3 class="hidden">메뉴종류</h3>
						<div class="menuCategoryWrap oneRow">
							<ul>
								<li><a href="#" class="menuItemCom menuItemTypeCom mItemCoffee">커피</a></li>
								<li><a href="#" class="menuItemCom menuItemTypeCom mItemBeverage">음료</a></li>
								<li><a href="#" class="menuItemCom menuItemTypeCom mItemFood">푸드</a></li>
							</ul>
						</div>

						<div class="menuSubCategoryWrap oneRow mCBSubType">
							<ul>
								<li><a href="#" class="menuItemCom menuItemSubTypeCom mItemSubTypeHot">뜨거운음료</a></li>
								<li><a href="#" class="menuItemCom menuItemSubTypeCom mItemSubTypeIce">차가운음료</a></li>
								<li><a href="#" class="menuItemCom menuItemSubTypeCom"></a></li>
							</ul>
						</div>
						<div class="menuSubCategoryWrap oneRow mFSubType">
							<ul>
								<li><a href="#" class="menuItemCom menuItemSubTypeCom"></a></li>
								<li><a href="#" class="menuItemCom menuItemSubTypeCom"></a></li>
								<li><a href="#" class="menuItemCom menuItemSubTypeCom"></a></li>
							</ul>
						</div>
					</div>
					<div class="menuItemsWrap oneRow">
						<h3 class="hidden">메뉴</h3>
						<ul>					
							<c:forEach var="beverages" items="${BeverageMenus }">
								<input type="hidden" name="stock" value="${beverages.stock }"/>
								
								
								<c:choose>
								<c:when test = "${fn:contains(beverages.menuName,'핫') }">
									<li class="mList mBeverage mBeverageHot"><a href="#">
								</c:when>
								<c:when test = "${fn:contains(beverages.menuName,'아이스') }">
									<li class="mList mBeverage mBeverageIce"><a href="#">
								</c:when>
								<c:otherwise>
									<li class="mList mBeverage"><a href="#">
								</c:otherwise>
								</c:choose>
									<input type="hidden" name="menuId" class="menuId" value="${beverages.id }"/>
										<div class="menuImgWrap">
											<img src="${beverages.imgPath }" alt="menuImg">
											<div class="menuNamePriceWrap">
												<p class="menuName">${beverages.menuName }</p>
												<p class="menuPrice"><fmt:formatNumber value="${beverages.menuPrice }" pattern=",###" type="currency" />원</p>
											</div>
										</div>
								</a></li>
							</c:forEach>
							
							<c:forEach var="coffees" items="${CoffeeMenus }">
								<input type="hidden" name="stock" value="${coffees.stock }"/>
								
								<c:choose>
								<c:when test = "${fn:contains(coffees.menuName,'핫') }">
									<li class="mList mCoffee mCoffeeHot"><a href="#">
								</c:when>
								<c:when test = "${fn:contains(coffees.menuName,'아이스') }">
									<li class="mList mCoffee mCoffeeIce"><a href="#">
								</c:when>
								<c:otherwise>
									<li class="mList mCoffee"><a href="#">
								</c:otherwise>
								</c:choose>
									<input type="hidden" name="menuId" class="menuId" value="${coffees.id }"/>
										<div class="menuImgWrap">
											<img src="${coffees.imgPath }" alt="menuImg">
											<div class="menuNamePriceWrap">
												<p class="menuName">${coffees.menuName }</p>
												<p class="menuPrice"><fmt:formatNumber value="${coffees.menuPrice }" pattern=",###" type="currency" />원</p>
											</div>
										</div>
								</a></li>
							</c:forEach>
							
							<c:forEach var="foods" items="${FoodMenus }">
								<input type="hidden" name="stock" value="${foods.stock }"/>
									<li class="mList mFood"><a href="#">
										<input type="hidden" name="menuId" class="menuId" value="${foods.id }"/>
										<div class="menuImgWrap">
											<img src="${foods.imgPath }" alt="menuImg">
											<div class="menuNamePriceWrap">
												<p class="menuName">${foods.menuName }</p>
												<p class="menuPrice"><fmt:formatNumber value="${foods.menuPrice }" pattern=",###" type="currency" />원</p>
											</div>
										</div>
								</a></li>
							</c:forEach>
						
						</ul>
					</div>
					<div class="funcButtonsWrap oneRow">
						<h3 class="hidden">기능</h3>
						<form id="goCompOrder" action="compOrder" method="post"></form>
						<ul>
							<li><a href="#" class="menuItemCom funcButtonCom funcAllCancelBtn">전체취소</a></li>
							<li><a href="#" class="menuItemCom funcButtonCom"></a></li>
							<li><a href="#" class="menuItemCom funcButtonCom funcCustomerSelectBtn">고객선택</a></li>
							<li><a href="#" class="menuItemCom funcButtonCom funcOrderBtn">주문하기</a></li>
						</ul>
						
						
					</div>

				</div>
			</div>
		</div>
	</section>

</body>
</html>