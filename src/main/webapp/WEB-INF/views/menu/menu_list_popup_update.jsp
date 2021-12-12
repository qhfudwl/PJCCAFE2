<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴 수정</title>
<%@ include file="../incl/link.jsp" %>
</head>
<body>
	<div id="updateMenuPopUp">
	<form method="post" name="updateMenuForm">
		<input class="hidden" type="text" name="id" value="${menu.id}" />
		<input class="hidden" type="text" name="menuType" value="${menu.menuType}" />
		<table>
			<tr>
				<td>이미지경로</td>
				<td>
					<input type="file" name="sendImgPathFile" />
					<input type="text" class="hidden" name="sendImgPathText" value="${menu.imgPath}" />
					<input type="text" class="hidden" name="imgPath" />
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="menuName" value="${menu.menuName}" /></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="menuPrice" value="${menu.menuPrice}" /></td>
			</tr>
			<tr>
				<td>현재재고유무</td>
				<td><input type="checkbox" name="stock" value="${menu.stock}" checked /></td>
			</tr>
		</table>
		<input type="submit"value="수정하기" />
	</form>
	</div>
	<script>
		$("input[type=submit]").mousedown(function(e) {
			e.preventDefault();
			let sendImgPathFile = $("input[name=sendImgPathFile]").val();
			let sendImgPathText = $("input[name=sendImgPathText]").val();
			if(sendImgPathFile == ""){
				$("input[name=imgPath]").val(sendImgPathText);
			} else {
				$("input[name=imgPath]").val(sendImgPathFile);
			}
		})
		$("input[type=submit]").click(function(e) {
			e.preventDefault();
			updateMenu();
			self.close();
		})
		
		function updateMenu() {
			
			let updateMenuUrl = getContextPath() + "/menu/updateMenu";
			let updateMenuTitle = "메뉴 수정";
			let updateMenuForm = document.updateMenuForm;
			updateMenuForm.target = updateMenuTitle;
			updateMenuForm.action = updateMenuUrl;
			
			updateMenuForm.submit();
		}
	</script>
</body>
</html>