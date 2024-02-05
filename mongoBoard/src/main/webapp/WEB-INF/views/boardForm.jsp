<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="boardWrite" method="post" name="frm">
<table border="1">
	<tr><td>글쓴이</td>
		<!--   request.getParameter("boardWriter") -->
		<td><input type="text" name="boardWriter"></td></tr>
	<tr><td>제목</td>
		<td><input type="text" name="boardSubject"></td></tr>
	<tr><td>내용</td>
		<td><textarea rows="6" cols="50" name="boardContent"></textarea></td></tr>
	<tr><td colspan="2">
		<input type="submit" value="전송">
		</td></tr>
</table>
</form>
</body>
</html>