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
<%@ include file="../incl/header.jsp" %>
	<%--
	<div id="updateMenuPopUp">
		<input class="hidden" type="text" name="menuType" value="${menu.menuType = choiceMenu}" />
		<input class="hidden" type="text" name="stock" value="${menu.stock = true}" />
		<table>
			<tr>
				<td><label>이미지경로</label></td>
				<td><input type="file" name="imgPath" value="${menu.imgPath}" /></td>
			</tr>
			<tr>
				<td><label>이름</label></td>
				<td><input type="text" name="menuName" value="${menu.menuName}" /></td>
			</tr>
			<tr>
				<td><label>가격</label></td>
				<td><input type="text" name="menuName" value="${menu.menuPrice}" /></td>
			</tr>
		</table>
		<input type="submit" name="checkMenu" id="checkMenu" value="확인" />
	</div> --%>
	<div id="updateMenuPopUp">
		<input class="hidden" type="text" name="menuType" value="C" />
		<input class="hidden" type="text" name="stock" value="true" />
		<table>
			<tr>
				<td><label>이미지경로</label></td>
				<td><input type="file" name="imgPath" /></td>
			</tr>
			<tr>
				<td><label>이름</label></td>
				<td><input type="text" name="menuName" /></td>
			</tr>
			<tr>
				<td><label>가격</label></td>
				<td><input type="text" name="menuPrice" /></td>
			</tr>
		</table>
		<input type="submit" name="checkMenu" id="checkMenu" value="확인" />
	</div>
	<script>
		$("#updateMenuPopUp input[name=updateMenu]").click(function() {
			let name= = $(this).val();
			if (name == "add") {
				$("#updateMenuPopUp input[name=menuType]")
			}
		})
	</script>
</body>
</html>