<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user/user_list.css" />
<title>Insert title here</title>
</head>
<body>
    <header><%@ include file="/WEB-INF/views/incl/header.jsp" %></header>
        <main>
            <section id="userWrap">
                <h2>회원목록</h2>
                <div id="userFormWrap">
                    <form name="updateForm"  method = "get">
                        <input class= "submitim" type="submit" value="거래조회" id="saleslist" formaction="userSaleslist">
                        <input class= "submitim" type="submit" value="삭제" id="removebtn">
                        <input class= "submitim" type="submit" value="수정" id="revisebtn" name="revisebtn" >
                        <input class= "submitim" type="submit" value="추가" id="addbtn">
                        <div id="SearchWrap">
                            <select name="column" id="column">
                                <option value="name">이름</option>
                                <option value="phone">전화번호</option>
                                <option value="birth">생일</option>
                            </select>
                            <input type="text" name="keyword" id="keyword" placeholder="검색어 입력">
                            <input class="submitim" type="submit" id="searchbtn" value="검색하기" formaction="userSearch">
                        </div>
                        <div id="userlistWrap">
                            <table id="userlist">
                                   <tr><th class="hname">이름</th><th class="hphone">폰번호</th><th class="hbirth">생년월일</th><th class="hpoint">포인트</th><th class="hregDate">가입일자</th></tr>
                                   <c:forEach var="users" items="${users}">
                                    <tr class="">
                                        <td class="name">${users.customerName}
                                            <label for="label${users.id}" class="userincubate"></label>
                                            <input id="label${users.id}" class="ordernumin" type="radio" name="usersId" value ="${users.id}"/>
                                        </td>
                                        <td class="phone">${users.phone}</td>
                                        <td class="birth">${users.birth}</td>
                                        <td class="point"><fmt:formatNumber value="${users.point}" pattern=",###" type="currency" currencySymbol="" /> P</td>
                                        <td class="regDate">${users.regDate}</td>
                                    </tr>
                                    
                                </c:forEach>
                            </table>
                        </div>
                    </form>
                    
                        
                        <script>
                        <%--$('.userincubate').hover(function(){
                        	$(this).css("background-color", "yellow");
                        }--%>
                        $('.userincubate').click(function(){
                        	
                        	$('.userincubate').closest('tr:nth-child(even)').css("background-color", "#eaeaea");
                        	$('.userincubate').closest('tr:nth-child(odd)').css("background-color", "#f4f4f4");
                        	$(this).closest('tr').css("background-color", "#9dc970");
                        	
                            <%--if($(this).hasClass('active')){     
                            } else {
                                   $('.userincubate').removeClass('active')
                                   $(this).addClass('active')
                                   $('.active').closest('tr').css("background-color", "#9dc970");
                                   
                            }--%>

                      });
                        //$(this).click(function(e){
                        //	$('.test').removeClass('active');
                        //	$(this).addClass('active');
                        //})
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
                        //$("#saleslistbtn").click(function(e){
                        //	e.preventDefalut();
                        //	popupUpDate("userSaleslist");
                        //})
                        function popupUpdate(Url){
                            let windowWidth = window.screen.width;
                            let windowHeight = window.screen.height;
                            
                            let popupX = (windowWidth/2) - 600;
                            let popupY = (windowHeight/2) -200;
                            
                            let popUpdateUrl = getContextPath() + "/user/" + Url;
                            let popUpdateOption = "width=600px, height=400px, top=" + popupY + "px, left=" + popupX + "px";
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