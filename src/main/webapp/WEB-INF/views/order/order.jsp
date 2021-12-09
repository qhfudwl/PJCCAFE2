<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>\
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%@ include file="/WEB-INF/views/incl/stylesheet_link.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/incl/jquery-3.6.0.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/incl/header.jsp"></jsp:include>


<button onclick="ajaxTest()">test</button>
<form action="sayHello" method="post">
	<input type="hidden" name="test" value="testResult"/>
	<button onclick="submit">hi</button>
</form>

<script>

function ajaxTest() {

  var json = {"id" : "아이디", "pw" : "1234"};
  
  $.ajax({
    url: "sayHello",
    type:"post",
    data: JSON.stringify(json),
    dataType    :    "json",
    contentType : "application/json; charset=UTF-8",
    success: function(data) {
    	alert(data.name);
    },
    error: function() {
    	alert("error");
    }
  })
}

</script>


</body>
</html>