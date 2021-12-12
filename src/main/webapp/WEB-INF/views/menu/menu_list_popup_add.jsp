<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴 추가 / 수정</title>
<%@ include file="../incl/link.jsp" %>
</head>
<body>
	<div id="addMenuPopUp">
	<form method="post" action="addMenu" name="addMenuForm">
		<input class="hidden" type="text" name="menuType" value="${menu.menuType}" />
		<table>
			<tr>
				<td>이미지경로</td>
				<td><input type="file" name="imgPath" /></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="menuName" /></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="menuPrice" /></td>
			</tr>
			<tr>
				<td>현재재고유무</td>
				<td><input type="checkbox" name="stock" value="true" checked /></td>
			</tr>
		</table>
		<input type="submit" value="추가하기" />
	</form>
	<script>
		$("input[type=submit]").click(function(e) {
			e.preventDefault();
			addMenu();
			self.close();
		})
		
		function addMenu() {
			
			let addMenuUrl = getContextPath() + "/menu/addMenu";
			let addMenuTitle = "메뉴 추가";
			let addMenuForm = document.addMenuForm;
			addMenuForm.target = addMenuTitle;
			addMenuForm.action = addMenuUrl;
			
			addMenuForm.submit();
		}
	</script>
	</div>
</body>
</html>