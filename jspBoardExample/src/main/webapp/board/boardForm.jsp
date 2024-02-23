<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardForm.jsp</title>
</head>
<body>
게시글 쓰기 폼<br /> <!-- input 태그에 있는 값들을 boardWrite.naver 주소에 전송 -->
				<!-- post방식으로 전송 -->
<form action="boardWrite.naver" method="post">
이름 : <input type="text" name="boardWriter" /><br />
제목 : <input type="text" name="boardSubject" /><br />
내용 : <textarea rows="5" cols="30" name="boardContent"></textarea><br />
<input type="submit" value="전송"> 
</form>
</body>
</html>