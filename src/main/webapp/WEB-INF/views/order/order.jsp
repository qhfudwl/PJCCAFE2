<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>\
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%@ include file="/WEB-INF/views/incl/stylesheet_link.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/incl/jquery-3.6.0.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/incl/header.jsp"></jsp:include>




<div>${mPosition }</div>
<div>${nowDate }</div>
<c:forEach var="Beverages" items="${BeverageMenus }">
<div>${Beverages.menuName }</div>
<img alt="" src="${Beverages.imgPath }">
</c:forEach>


</body>
</html>