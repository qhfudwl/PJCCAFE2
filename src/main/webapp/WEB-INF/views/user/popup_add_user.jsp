<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user/user_popup_add.css" />
<title>Insert title here</title>
</head>
<body>
    <main>
        <section id="addUserWrap">
            <h1>PJCCAFE</h1>
            <h3>회원 가입</h3>
            <div id="addUserFormWrap">
                <form action="addUser" method="post" name="addUser" onsubmit="return checkValue()">
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
                
                function checkValue() 
                {
                	var form = document.addUser;
                	
                	let nameExp = /^[가-힣a-zA-Z]+$/gm;
    				if(form.customerName.value.match(nameExp) == null){
    					alert("올바른 이름 형식이 아닙니다.");
    					return false;
    				}
    			
    				let ssnExp = /^\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|[3][0-1])$/gm;
            		if(form.birth.value.match(ssnExp) == null){
            			alert("올바른 주민등록번호 형식이 아닙니다.");
                	    return false;
            		}
            	
            		let phoneExp = /^(01[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])$/gm;
            		if(form.phone.value.match(phoneExp) == null){
                	    alert("올바른 휴대전화 번호 공식이 아닙니다.");
                	    return false;
                	}
                }
            	
            	
                
                
                
                </script>

            </div>
        </section>
    </main>
    <footer></footer>
</body>
</html>