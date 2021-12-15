<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴 수정</title>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu/menu_popup_update.css" />
</head>
<body>
<section id="uploadContent">
<h3>메뉴 정보 수정</h3>
	<div id="updateMenuPopUp">
	<form:form method="post" modelAttribute="menuCommand" path="updateMenuForm" action="updateMenu" enctype="multipart/form-data">
		<form:input class="hidden" path="id" />
		<form:input class="hidden" path="menuType" />
		<input type="text" class="hidden" name="close" value="${close}" /> 
		<table>
			<tr>
				<th>이미지경로</th>
				<td>
					<input type="file" id="updateImgFile" name="file" />
					<label for="updateImgFile">업로드</label>
					<form:input path="imgPath" />
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td><form:input path="menuName" /></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><form:input path="menuPrice" /></td>
			</tr>
			<tr>
				<th>재고유무</th>
				<td>
					<p class="hidden">${menuCommand.stock}</p>
					<input type="radio" id="updateStockTrue" name="stock" value="true" />
					<label for="updateStockTrue">재고 있음</label>
					<input type="radio" id="updateStockFalse" name="stock" value="false" />
					<label for="updateStockFalse">재고 없음</label>
				</td>
			</tr>
		</table>
		<input type="submit"value="수정하기" />
	</form:form>
	</div>
</section>
	<script>
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