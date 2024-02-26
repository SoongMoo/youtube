<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.1.min.js"></script>
  <script>
	$(function(){
		$("#btn").click(function(){
			option  = '<div id = "appd">';
			option += '질문<input type="text" size="15" name="question"/> ';
			option += '<button type="button" onclick="insert(this)">답 추가</button>'
			option += '<button type="button" id= "btn" onclick="del(this);">질문 삭제</button>';
			option += '<div>'
			option += '</div>'
			$("#appd").append(option);
		});
	});
	function del(obj1){
		$(obj1).parent().remove();
	}
	function insert(obj1){
		option  = '<div>답 : <input type="text" size="15" name="option"/>';
		option += '<button type="button" id= "btn" onclick="del(this);">삭제</button>'
		option += '</div> ';
		
		$(obj1).parent().last().append(option);
	}
  </script>
</head>
<body>
<form action="questionRegiste" method="post" >
설문 번호 : <input type="number" name="surveyNum" value=1 min=1 step="1"/>
질문을 추가하시려면 <span id= "btn" style="color:red">추가</span>를 누르세요.   
 <div id = "appd"></div>
 </form>
</body>
</html>