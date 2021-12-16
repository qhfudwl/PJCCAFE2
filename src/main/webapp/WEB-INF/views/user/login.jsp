<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user/login.css" />
</head>
<body>
<section><h2 id="loginLogo">PJC Cafe</h2></section>
<section id="loginContent">
	<h2>관리자 로그인</h2>
     <div id="login">
		<form:form modelAttribute="employeeCommand" action="checkAdmin" method="post">
		<table>
			<tr>
				<th><label for="eid">ID</label></th>
				<td>
					<form:input path="eid" placeholder="아이디 입력" />
					<c:if test="${not empty errMsg}"><p class="errMsg">${errMsg.get("idErr")}</p></c:if>
				</td>
			</tr>
			<tr>
				<th><label for="passwd">PW</label></th>
				<td>
					<form:password path="passwd" placeholder="비밀번호 입력" />
					<c:if test="${not empty errMsg}"><p class="errMsg">${errMsg.get("pwErr")}</p></c:if>
				</td>
			</tr>
		</table>
		<input type="submit" value="로그인" />
		</form:form>
	</div>
</section>
</body>
</html>