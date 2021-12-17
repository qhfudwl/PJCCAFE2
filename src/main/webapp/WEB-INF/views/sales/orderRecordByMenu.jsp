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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sales/orderRecordByMenu.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/incl/header.jsp" %>	
 <section id="SelectMenuContent">
        <div id="titleWrap">
            <h2 id="tabTitle">메뉴별 판매내역</h2>
        </div>
        
        <div id="searchWrap">
       <form action="searchOrderRecordByMenu" method="GET">
	       		<input type="hidden" value ="${periodType}" name="periodType"/>
	       		<input type="hidden" value ="${menuType}" name="menuType"/>
 <input id="searchTxt" type ="text" name="searchTxt"/>
	        <input type="submit" value="검색"/>
	       </form>
       </div>
       
       
       <div id="menuWrap">
	        <form action="viewOrderRecordByMenu" id="periodForm" method="GET">  
	            <div id="periodWrap" class="buttonWrap">
	            <input type="hidden" value ="T" name="menuType"/>
	            <input type="hidden" value ="" name="searchTxt"/>
	                <ul>
	                    <li><button type="submit" id="dDate" name="periodType" class="active" value="D">일간</button></li>
	                    <li><button type="submit" id="wDate" name="periodType" value="W">주간</button></li>
	                    <li><button type="submit" id="mDate" name="periodType" value="M">월간</button></li>
	                </ul>
	            </div>
	            <input type="hidden" value="${periodType}" id="hiddenBtnp" >
	        </form>
	        <form action="viewOrderRecordByMenu" id="menuForm" method="GET">
	        	<input type="hidden" value="${periodType}" name="periodType">
	        <!-- <input type="hidden" value ="" name="searchTxt"/> -->
	        	<input type="hidden" value ="${menuType}" name="searchTxt"/>
	        	
	            <div id="typeWrap" class="buttonWrap">
	                <ul>
	                    <li><button type="submit" name="menuType" id="totalBtn" class="on" value="T">전체</button></li>
	                    <li><button type="submit" name="menuType" id="beverageBtn" value="B">음료</button></li>
	                    <li><button type="submit" name="menuType" id="coffeeBtn" value="C">커피</button></li>
	                    <li><button type="submit" name="menuType" id="foodBtn" value="F">푸드</button></li>  
					</ul>
				</div>
				 <input type="hidden" value="${menuType}" id="hiddenBtnM" >
	        </form>
        </div>
    </section>
					    
					  

	<section id="RecordContent">
 <div id="RecordWrap">
 <table id="RecordTable" border='1'>
<tr> 
 <th class="hDate">기간</th>
 <th class="hMenuName">메뉴</th>
 <th class="hQuantity">주문수</th>
<th class="hPrice">판매금액</th>
</tr>
 <c:forEach var="orderRecord" items="${osList}">
 <c:choose>
<c:when test="${not empty searchTxt}">
<c:if test="${orderRecord.menuName.contains(searchTxt)}">
<tr>
<td class="dDate">${orderRecord.weekDate}</td>
<td class="dName">${orderRecord.menuName}</td>
<td class="dQuantity">${orderRecord.quantity}</td>
<td class="dPrice"><fmt:formatNumber type="currency" currencySymbol="" value="${orderRecord.price}"/>원</td>
</tr>
</c:if>
 </c:when>
 <c:otherwise>
<tr>
<td class="dDate">${orderRecord.weekDate}</td>
<td class="dName">${orderRecord.menuName}</td>
<td class="dQuantity">${orderRecord.quantity}</td>
 <td class="dPrice"><fmt:formatNumber type="currency" currencySymbol="" value="${orderRecord.price}"/>원</td>
</tr>
 </c:otherwise>
 </c:choose>	
 </c:forEach> 
 </table>
    	</div>
    </section>
</body>
</html>

<%-- 
<c:choose>
	<c:when test="${not empty searchTxt}">
		<c:if test="${fn: }"
</c:when>
</c:choose> --%>




