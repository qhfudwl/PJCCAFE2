<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴 수정</title>
<%@ include file="../incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu/menu_popup_update.css" />
</head>
<body>
	<div id="updateMenuPopUp">
	<form method="post" name="updateMenuForm" action="updateMenu" enctype="multipart/form-data">
		<input class="hidden" type="text" name="id" value="${menu.id}" />
		<input class="hidden" type="text" name="menuType" value="${menu.menuType}" />
		<input type="text" class="hidden" name="close" value="${close}" /> 
		<table>
			<tr>
				<th>이미지경로</th>
				<td>
					<input type="file" id="updateImgFile" name="file" />
					<label for="updateImgFile">업로드</label>
					<input type="text" class="hidden" name="sendImgPathText" value="${menu.imgPath}" />
					<input type="text" name="imgPath" />
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="menuName" value="${menu.menuName}" /></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" name="menuPrice" value="${menu.menuPrice}" /></td>
			</tr>
			<tr>
				<th>재고유무</th>
				<td>
					<p class="hidden">${menu.stock}</p>
					<input type="radio" id="updateStockTrue" name="stock" value="true" />
					<label for="updateStockTrue">재고 있음</label>
					<input type="radio" id="updateStockFalse" name="stock" value="false" />
					<label for="updateStockFalse">재고 없음</label>
				</td>
			</tr>
		</table>
		<input type="submit"value="수정하기" />
	</form>
	</div>
	<script>
		$("#updateMenuPopUp").mousemove(function() {
			
			let sendImgPathFile = $("input[name=file]").val();
			let arrFile = sendImgPathFile.split("\\");
			let fileImgName = arrFile[arrFile.length - 1];
			
			let sendImgPathText = $("input[name=sendImgPathText]").val();
			let arrText = sendImgPathText.split("/");
			let textImgName = arrText[arrText.length - 1];
			
			if(sendImgPathFile == ""){
				$("input[name=imgPath]").val(textImgName);
			} else {
				$("input[name=imgPath]").val(fileImgName);
			}
		})
		window.onload = function() {
			let stock = $("#updateMenuPopUp p").text();
			if (stock == "true"){
				$("#updateStockTrue + label").trigger("click");
			} else {
				$("#updateStockFalse + label").trigger("click");
			}
			
			if ($("input[name=close]").val() == "close"){
				opener.parent.reloadPage();
				self.close();
			}
		}
	</script>
</body>
</html>