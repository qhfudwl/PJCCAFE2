<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header></header>
	<main>
		<section>
			<form action="">
            	<h2>회원 탈퇴</h2>
            	<table>
                	<tr>
                    	<th class="title">ID : </th>
                    	<td>
                    	    <input type="text" name="id" value="${user.id}">
                    	</td>
                	</tr>
                	<tr>
                    	<th class="title">이름</th>
                    	<td>
                     	   <input type="text" name="name" id="name" value="${user.userName}">
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
                	<input type="submit" value="회원탈퇴" id="joinbtn">
                	<input type="button" value="취소하기" id="canclebtn" onclick="close()">
                </div>
        	</form>
		</section>	
  	</main>
    <footer></footer>
</body>
</html>