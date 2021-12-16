<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user/user_popup_revise.css" />
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
</head>
<body>
    <main>
    	<section id="updateUserPage">
        	<h1>PJCCAFE</h1>
            <h3>회원 수정</h3>
            <div id="updateUserFormWrap">
            	<form action="updateUser" method="post" name="updateUser" onsubmit="return checkValue()">
            		<input type="hidden"  name="close" value="${close}"/>
            		<table id="updateUsertable">
            			<tr>
            				<td>
            					<input type="hidden" name="id" id="userId" value="${user.id}">
            				</td>            		
            			</tr>
                		<tr>
                    		<th class="title">이름</th>
                    		<td>
                        		<input type="text" name="customerName" id="name" value="${user.customerName}">
                    		</td>
                		</tr>
                		<tr>
                    		<th class="title">휴대폰</th>
                    		<td>
                        		<input type="text" name="phone" id="phone" value="${user.phone}">
                   		 	</td>
                		</tr>
                		<tr>
                    		<th class="title">생년 월 일</th>
                    		<td>
                        		<input type="text" name="birth" id="birth" value="${user.birth}">
                    		</td>
                		</tr>
                		<tr>
                    		<th>포인트</th>
                    		<td>
                        		<input type="text" name="point" id="point" value="${user.point}">
                    		</td>
                		</tr>
            		</table>
                	<div id="buttonWrap">
                	<input type="submit" value="수정하기" id="joinbtn">
                	<!-- <input type="button" value="취소하기" id="canclebtn" onclick="close()"> -->
                	</div>
        		</form>
        	</div>
        </section>
    </main>
    <script>
    	window.onload = function() {
    		if($("input[name=close]").val() == "close"){
    			opener.parent.reloadPage();
    			self.close();
    		}
    	}
    	function checkValue() 
        {
        	var form = document.updateUser;
        	
        	let nameExp = /^[가-힣a-zA-Z]+$/gm;
			if(form.customerName.value.match(nameExp) == null){
				alert("올바른 이름 형식이 아닙니다.");
				return false;
			}
		
			let birthExp = /^[0-9][0-9][0-9][0-9](0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|[3][0-1])$/gm;
    		if(form.birth.value.match(birthExp) == null){
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
    <footer></footer>
</body>
</html>