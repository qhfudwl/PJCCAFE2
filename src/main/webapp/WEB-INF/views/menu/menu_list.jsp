<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴 목록</title>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu/menu_view.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/incl/header.jsp" %>
<nav id="menuSnb">
<h2 class="hidden">메뉴 타입 선택</h2>
	<p>PJC <span>Cafe</span> / 대구 / Manager</p>
	<form action="viewMenuList" method="get">
		<ul>
			<li>
				<button type="submit" value="C" name="choiceMenu" id="menuC">커피</button>
			</li>
			<li>
				<button type="submit" value="B" name="choiceMenu" id="menuB">음료</button>
			</li>
			<li>
				<button type="submit" value="F" name="choiceMenu" id="menuF">푸드</button>
			</li>
		</ul>
	</form>
</nav>
<section id="menuListContent">
	<h2 class="hidden">메뉴 목록</h2>
	<div id="menuListWrap">
		<form name="updateMenuForm" method="post">
		<div id="updateMenuWrap">
			<input type="submit" name="updateMenu" value="추가" id="addMenu" />
			<input type="submit" name="updateMenu" value="수정" id="updateMenu" />
			<input type="submit" name="updateMenu" value="삭제" id="removeMenu" />
		</div>
		<div id="itemWrap">
			<c:forEach var="m" items="${menus}">
				<div class="item">
					<div class="imgWrap">
						<img height="145px" src="${m.imgPath}"/>
					</div>
					<ul>
						<li class="menuName">${m.menuName}</li>
						<li class="menuPrice"><fmt:formatNumber value="${m.menuPrice}" pattern=",###" type="currency" /> 원</li>
						<li class="stock">
							<c:choose>
								<c:when test="${m.stock eq 'true'}">재고 있음</c:when>
								<c:otherwise>재고 없음</c:otherwise>
							</c:choose>
						</li>
						<li class="regDate"><fmt:formatDate value="${m.regDate}" pattern="yyyy년 MM월 dd일"/></li>
						<li class="menuId">
							<input type="radio" name="choiceItem" id="menuId${m.id}" value="${m.id}" />
							<label for="menuId${m.id}"></label>
						</li>
					</ul>
				</div>
			</c:forEach>
		</div>
		<input class="hidden" value="${choiceMenu}" name="choiceMenu" id="hiddenChoiceMenu" type="text"/>
		</form>
		<script>
			window.onload = function() {
				$("button[name=choiceMenu]").removeClass();
				if ($("#hiddenChoiceMenu").val() == "C") {
					$("#menuC").addClass("on");
				} else if ($("#hiddenChoiceMenu").val() == "B") {
					$("#menuB").addClass("on");
				} else if ($("#hiddenChoiceMenu").val() == "F") {
					$("#menuF").addClass("on");
				}
			}
			$("#addMenu").click(function(e) {
				e.preventDefault();
				popupUpdateMenu("popAddMenu", 500, 300);
			})
			$("#updateMenu").click(function(e) {
				e.preventDefault();
				if ($("input[name=choiceItem]:checked").val() == null) {
					alert("먼저 메뉴를 선택해주세요!");
				} else {
					popupUpdateMenu("popUpdateMenu", 600, 300);
				}
			})
			$("#removeMenu").click(function(e) {
				e.preventDefault();
				if ($("input[name=choiceItem]:checked").val() == null) {
					alert("먼저 메뉴를 선택해주세요!");
				} else {
					removeSubmit("removeMenu");
				}
			})
			function popupUpdateMenu(goUrl, popWidth, popHeight) {
				let windowWidth = window.screen.width;
				let windowHeight = window.screen.height;
				
				let popupX = (windowWidth/2) - popWidth/2;
				let popupY = (windowHeight/2) - popHeight/2;
				
				let popUpdateMenuUrl = getContextPath() + "/menu/" + goUrl;
				let popUpdateMenuOption = "width=" + popWidth + "px, height=" + popHeight + "px, top=" + popupY + "px, left=" + popupX + "px";
				let popUpdateMenuTitle = "메뉴 업데이트";
				
				window.open(popUpdateMenuUrl, popUpdateMenuTitle, popUpdateMenuOption);
				
				let updateMenuForm = document.updateMenuForm;
				updateMenuForm.action = popUpdateMenuUrl;
				updateMenuForm.target = popUpdateMenuTitle;
				updateMenuForm.method = "post";
				
				updateMenuForm.submit();
				
			}
			function removeSubmit(goUrl) {
				
				let popUpdateMenuUrl = getContextPath() + "/menu/" + goUrl;
				let updateMenuForm = document.updateMenuForm;
				
				updateMenuForm.action = popUpdateMenuUrl;
				updateMenuForm.method = "post";
				
				updateMenuForm.submit();
			}
			function reloadPage() {
				location.reload();
			}
		</script>
	</div>
</section>
</body>
</html>