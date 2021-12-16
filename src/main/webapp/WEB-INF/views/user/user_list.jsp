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
                <form name="updateForm"  method = "get">
                	<input type="submit" value="추가" id="addbtn">
                	<input type="submit" value="수정" id="revisebtn" name="revisebtn" >
                	<input type="submit" value="삭제" id="removebtn">
                	<div>
                        <select name="column" id="column">
                        	<option value="name">이름</option>
                            <option value="phone">전화번호</option>
                            <option value="birth">생일</option>
                        </select>
                        <input type="text" name="keyword" id="keyword" placeholder="검색어 입력">
                    	<input type="submit" id="searchbtn" value="검색하기" formaction="userSearch">
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
                    $("#addbtn").click(function(e) {
                    	e.preventDefault();
                    	popupUpdate("addUserbtn");
                    })
                    $("#revisebtn").click(function(e){
                    	e.preventDefault();
                    	popupUpdate("updateUserbtn");
                    })
                    $("#removebtn").click(function(e){
                    	e.preventDefault();
                    	popupUpdate("removeUser");
                    })
                    function popupUpdate(Url){
                    	let windowWidth = window.screen.width;
                    	let windowHeight = window.screen.height;
                    	
                    	let popupX = (windowWidth/2) - 200;
                    	let popupY = (windowHeight/2) -250;
                    	
                    	let popUpdateUrl = getContextPath() + "/user/" + Url;
                    	let popUpdateOption = "width=400px, height=500px, top=" + popupY + "px, left=" + popupX + "px";
                    	let popUpdateTitle = "유저 갱신"
                    	
                    	if(Url == "addUserbtn" || Url == "updateUserbtn") {
                    		window.open(popUpdateUrl, popUpdateTitle, popUpdateOption);
                    	}
                    	let updateForm = document.updateForm;
                    	updateForm.target = popUpdateTitle;
                    	updateForm.action = popUpdateUrl;
                    	updateForm.method ="get";
                    	
                    	updateForm.submit();
                    }
                    function reloadPage() {
                    	location.reload();
                    }
                    //function addPopup(){
                    //   window.open( getContextPath() + "/user/addUserbtn","유저 회원가입","width=400, height=300, top=10, left=10");
                    //}
                    
                    </script>
            </div>
        </section>
    </main>
</body>
</html>