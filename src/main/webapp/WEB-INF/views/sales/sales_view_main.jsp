<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String datePicker = request.getParameter("datePicker"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sales/sales_view_main.css" />
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
</head>
<body>
	<header>
	<%@ include file="/WEB-INF/views/incl/header.jsp" %>
	</header>
    <main>
        <section id="salesWrap">
            <h2>매출현황</h2>
            <div id="salesbtnWrap">
                <form action="viewSalesType" method="get">
                    <div id="timebtn">
                        <button class="timebtnin <% if("D".equals(datePicker)) {%> active<%} %>" type = "button" value="D">일별</button>
                        <button class="timebtnin <% if("W".equals(datePicker)) {%> active<%} %>" type = "button" value="W">주별</button>
                        <button class="timebtnin <% if("M".equals(datePicker)) {%> active<%} %>" type = "button" value="M">월별</button>
                        <input id="days" type="submit" class="hidden" value="D" name ="datePicker">
                        <input id="weeks" type="submit" class="hidden" value="W" name ="datePicker">
                        <input id="months" type="submit" class="hidden" value="M" name ="datePicker">
                    </div>
                </form>
            </div>
            <div id="salesListWrap">
                <form action="saleController.do" method="post">
                    <div id="salesListtable">
                        <table>
                            <tr><th class="regDate">기간</th><th class="orders">총 주문수</th><th class=tAmount>총 매출</th><th class="tusePoint">총 사용포인트</th><th class="tPrice">총액</th></tr>
                            <c:forEach var="slist" items= "${sList}">
                                <tr>
                                    <td class="regDatein">${slist.totalDate}</td>
                                    <td class="tordersin">${slist.totalorder}</td>
                                    <td class="tAmountin"><fmt:formatNumber value="${slist.totalAmount}" pattern=",###" type="currency" currencySymbol="" /> 원</td>
                                    <td class="tusePointin"><fmt:formatNumber value="${slist.totalusePoint}" pattern=",###" type="currency" currencySymbol=""/> P</td>
                                    <td class="tPricein"><fmt:formatNumber value="${slist.totalPrice}" pattern=",###" type="currency" currencySymbol="" /> 원</td>
                                </tr>
                            </c:forEach>
                            <script>
                            $('.timebtnin').click(function(e){
                            	if($(this).hasClass('active')){  
                                } else {
                                       $('.timebtnin').removeClass('active');
                                       $(this).addClass('active');
                                       if($('.active').val() == "D"){
                                    	   clickEventDay();
                                       }else if($('.active').val() == "W"){
                                    	   clickEventWeeks();
                                       }else if($('.active').val() == "M"){
                                    	   clickEventMonth();   
                                       }
                                	}
                                });
                            function clickEventDay(){
                            	$("#days").trigger("click");
                            }
                            function clickEventWeeks(){
                            	$("#weeks").trigger("click");
                            }
                            function clickEventMonth(){
                            	$("#months").trigger("click");
                            }
                            
                            </script>
                        </table>
                    </div>
                </form>
            </div>
        </section>
    </main>
</body>
</html>