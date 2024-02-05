<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardModifyForm.jsp</title>
</head>
<body>
<form action="boardUpdate" method="post" >
<input type="hidden" name="id" value="${dto.id }"/>
글쓴이: <input type="text" value="${dto.boardWriter }" name="boardWriter"/> <br />
제목 : <input type="text" value="${dto.boardSubject }" name="boardSubject"><br />
내용 : <textarea rows="6" cols="50" name="boardContent">${dto.boardContent }</textarea><br />
<input type="submit" value="전송">
</form>
</body>
</html>