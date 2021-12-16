<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <section id="addUserWrap">
            <h1>PJCCAFE 관리자</h1>
            <h2>회원 가입</h2>
            <div id="addUserFormWrap">
                <form action="addUser" method="post">
                <input type="hidden" class="hidden" name="close" value="${close}"/>
                    <table id="addUserForm">
                        <tr>
                            <th class="title">이름</th>
                            <td>
                                <input type="text" name="customerName" id="name" placeholder="이름을 입력하세요">
                            </td>
                        </tr>
                        <tr>
                            <th class="title">휴대폰</th>
                            <td>
                                <input type="text" name="phone" id="phone" placeholder=" ex) 01011110000">
                            </td>
                        </tr>
                        <tr>
                            <th class="title">생년 월 일</th>
                            <td>
                                <input type="text" name="birth" id="birth" placeholder=" ex) 19891212">
                            </td>
                        </tr>
                        
                    </table>
                    <div id="buttonWrap">
                        <p>생년월일을 입력하시면 확인 후 혜택이 증가합니다.</p>
                        <input type="submit" value="가입하기" id="joinbtn">
                    </div>
                </form>
                <script>
                
                window.onload = function() {
                	if ($("input[name=close]").val() == "close"){
                		opener.parent.reloadPage();
                		self.close();
                	}
                }
                
                
                </script>

            </div>
        </section>
    </main>
    <footer></footer>
</body>
</html>