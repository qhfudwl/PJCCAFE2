<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user/login.css" />
</head>
<body>
<section>
	<h2>로그인</h2>
     <div id="login">
		<form action="login" method="post">
		<table>
			<tr>
				<td><label>ID</label></td>
				<td><input type="text" name="eid" /></td>
			</tr>
			<tr>
				<td><label>PW</label></td>
				<td><input type="password" name="passwd" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="로그인" /></td>
			</tr>
		</table>
		</form>
	</div>
</section>
</body>
</html>