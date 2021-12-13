<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<title>Insert title here</title>
</head>
<body>
<header></header>
    <main>
        <section id="userWrap">
            <h2>회원목록</h2>
            <div id="userFormWrap">
                <form action="viewUserMain" method = "get" name ="viewUserMain">
                	<input type="submit" value="추가" id="addbtn" onclick="addPopup()">
                	<input type="submit" value="수정" id="revisebtn" onclick="revisePopup()">
                	<input type="submit" value="삭제" id="removebtn" formaction="removeUser">
                	<div>
                        <select name="column" id="column">
                        	<option value="name">이름</option>
                            <option value="phone">전화번호</option>
                            <option value="birth">생일</option>
                        </select>
                        <input type="text" name="keyword" id="keyword" placeholder="검색어 입력">
                    	<input type="submit" id="searchbtn" value="검색하기" formaction="userSearch">
                    	 <a href="#none" target="_blank">팝업</a>
                    </div>
                	<table>
                       	<tr><th>회원등록번호</th><th>이름</th><th>폰번호</th><th>생년월일</th><th>포인트</th><th>가입일자</th></tr>
                   		<c:forEach var="users" items="${users}">
                            <tr>
                                <td><label path="usersId"></label><input type="radio" name="usersId" value ="${users.id}"/></td>
                                <td>${users.customerName}</td>
                                <td>${users.phone}</td>
                                <td>${users.birth}</td>
                                <td>${users.point}</td>
                                <td>${users.regDate}</td>
                            </tr>
                        </c:forEach>
                     </table>
                </form>
                
                    
                    <script>
                    function addPopup(){
                        window.open( getContextPath() + "/user/addUserbtn","유저 회원가입","width=400, height=300, top=10, left=10");
                    }
                    function revisePopup(){
                    	window.open( getContextPath() + "/user/updateUserbtn","유저 업데이트","width=400, height=300, top=10, left=10");
                    }
                    </script>
            </div>
        </section>
    </main>
    <!-- 
    <header></header>
    <main>
        <section id="userWrap">
            <h2>회원목록</h2>
            <div id="userFormWrap">
                <form action="addUserbtn" method = "get">
                	<input type="submit" value="전체" id ="allbtn">
                	<input type="submit" value="추가" id="addbtn">
                    <input type="submit" value="수정" id="revisebtn">
                    <input type="submit" value="삭제" id="removebtn">
                    <!--<div id="searcheWrap">
                        <form action="userSearch" method="post">
                        	<div>
                            	<select name="column" id="column">
                                	<option value="name" name="name">이름</option>
                                	<option value="phone" name="phone">전화번호</option>
                                	<option value="birth" name="birth">생일</option>
                            	</select>
                            	<input type="text" name="searche" placeholder="검색어 입력">
                            	<input type="submit" value="검색하기">
                            </div>
                        </form>
                    </div>
                    <div id="userlist">
                    	<table>
                            	<tr><th>회원등록번호</th><th>이름</th><th>폰번호</th><th>생년월일</th><th>포인트</th><th>가입일자</th></tr>
                        		<c:forEach var="user" items="${users}">
                            			<tr>
                                			<td><label path="userid"></label><input type="radio" name="userid" value ="${user.id}"/></td>
                                			<td>${user.customerName}</td>
                                			<td>${user.phone}</td>
                                			<td>${user.birth}</td>
                                			<td>${user.point}</td>
                                			<td>${user.regDate}</td>
                            			</tr>
                        		</c:forEach>
                        	</table>
                    </div>
                </form>
                
                    
                    <script>
                    $("#allbtn").click(function(e) {
                        e.preventDefault();
                        popupUser();
                    })
                    $("#addbtn").click(function(e) {
                    	e.preventDefault();
                    	popupUser();
                    })
                    $("#revisebtn").click(function(e) {
                    	e.preventDefault();
                    	popupUser();
                    })
                    $("#removebtn").click(function(e) {
                    	e.preventDefault();
                        popupUser();
                    })
                    </script>
                    function popupUser(goUrl) {
                        let windowWidth = window.screen.width;
                        let windowHeight = window.screen.height;

                        let popupX = (windowWidth/2) - 300;
                        let popupY = (windowHeight/2) -200;

                        let popupUserdataUrl = getContextPath() + "/user/" + goUrl;
                        let popupUserdataOption = "width = 600px, height = 400px, top =" + popupY + "px, left=" + popupX + "px";
                        let popupUserdataTitle = "유저 업데이트";

                        if(goUrl == "popAddmenu" || goUrl == "popUpdateMenu") {
                            window.open(popupUserdataUrl, popupUserdataTitle, popupUserdataOption);
                        }

                        let updateMenuForm = document.updateUserForm;
                        updateUserForm.target = popupUserdataTitle;
                        updateUserForm.action = popupUserdataUrl;
                        updateUserForm.method = "post";

                        updateUserForm.submit();
                    }
                    function reloadPage() {
                        location.reload();
                    }
                     
                    
            </div>
        </section>
    </main> -->
</body>
</html>