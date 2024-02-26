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
			option  = '<div>답 : <input type="text" size="15" name="options"/>';
			option += '<button type="button" id= "btn" onclick="del(this);">삭제</button>';
			option += '</div> ';
			$("#appd").append(option);
		});
	});
	function del(obj1){
		$(obj1).parent().remove();
	}
  </script>
</head>
<body>
<form action="questionRegiste" method="post" >
설문 번호 : <input type="number" name="surveyNum" value=${surveyNum } min=1 step="1"/><br />
설문자 <input type="text" name="surveyWriter" value="${surveyWriter }" ><br />
질문 번호 : <input type="number" name="questionNum" value=${questionNum } min=1 step="1"><br />
질문<input type="text" size="15" name="question"/><button type="button"  id="btn">답 추가</button>
<div id = "appd"></div>
<input type="submit" value="전송" />
 </form>
 <a href="/">설문등록 끝내기</a>
</body>
</html>