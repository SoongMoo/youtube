<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
게시글 수정<br />
<form action="boardUpdate.naver" method="post"> <!-- post방식으로 전송하므로 post에서 받는다. -->
<!-- 수정하기 위해서는 input  택그가 있어야 한다. -->
글번호 : <input type="text" name="boardNum"  value="${dto.boardNum }" /><br />
글쓴이 : <input type="text" name="boardWriter"  value="${dto.boardWriter }" /><br />
제목 : <input type="text" name="boardSubject"  value="${dto.boardSubject }" /><br />
내용 : <textarea rows="5" cols="30" name="boardContent">${dto.boardContent }</textarea><br />
<input type="submit" value="수정" />
</form>
</body>
</html>