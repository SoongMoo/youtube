<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList.jsp</title>
</head>
<body>
게시판 리스트입니다.<br />
<a href="boardWrite.naver">게시글 쓰기</a><br />
<!-- 이제 리스트에 저장된 값이 출력되게 한다. -->
<table border = 1 width=600>
	<tr><th>번호</th><th>글쓴이</th><th>제목</th></tr>
	<c:forEach items="${list }" var="dto">
	<tr><th><a href="boardDetail.naver?num=${dto.boardNum }">${dto.boardNum }</a></th>
		<th><a href="boardDetail.naver?num=${dto.boardNum }">${dto.boardWriter }</a></th>
		<th>${dto.boardSubject }</th></tr>
	</c:forEach>
</table>
</body>
</html>