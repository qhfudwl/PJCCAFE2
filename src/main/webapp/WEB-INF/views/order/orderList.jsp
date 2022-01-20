<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
    dataType    : "json",
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