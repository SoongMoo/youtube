<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="surveySubject" method="post" >
	설문 제목 : <input type="text" name="surveySubject"/><br />
	설문자 이름 : <input type="text" name="surveyWriter"><br />
	설문시작일 : <input type="datetime-local" name="surveyStart"><br />
	설문종료일 : <input type="datetime-local" name="surveyEnd"><br />
	<input type="submit" value="전송" />
</form>
</body>
</html>