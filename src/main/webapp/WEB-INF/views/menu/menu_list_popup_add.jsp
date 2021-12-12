<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴 추가</title>
<%@ include file="../incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu/menu_popup_add.css" />
</head>
<body>
	<div id="addMenuPopUp">
	<form method="post" action="addMenu" name="addMenuForm" enctype="multipart/form-data">
		<input class="hidden" type="text" name="menuType" value="${menu.menuType}" />
		<input type="text" class="hidden" name="close" value="${close}" /> 
		<table>
			<tr>
				<th>이미지경로</th>
				<td>
					<input id="addImgFile" type="file" name="file" />
					<label for="addImgFile">업로드</label>
					<input type="text" name="imgPath"  />
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="menuName" placeholder="메뉴 이름 입력"/></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" name="menuPrice" placeholder="메뉴 가격 입력"/> 원</td>
			</tr>
			<tr>
				<th>재고유무</th>
				<td>
					<input type="radio" name="stock" id="addStockTrue" value="true" checked />
					<label for="addStockTrue">재고 있음</label>
					<input type="radio" name="stock" id="addStockFalse" value="false" />
					<label for="addStockFalse">재고 없음</label>
				</td>
			</tr>
		</table>
		<input type="submit" value="추가하기" />
	</form>
	<script>
		$("#addMenuPopUp").mousemove(function() {
			let imgPath = $("input[type=file]").val();
			let arr = imgPath.split("\\");
			let imgName = arr[arr.length - 1];
			$("input[name=imgPath]").val(imgName);
		});
		window.onload = function() {
			if ($("input[name=close]").val() == "close"){
				opener.parent.reloadPage();
				self.close();
			}
		}
	</script>
	</div>
</body>
</html>