<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="boardWrite">글쓰기</a><br />
<table border= 1 width="600px">
<caption>게시글 목록</caption>
<thead>
	<tr><td>번호</td><td>글쓴이</td><td>제목</td></tr>
</thead>
<tbody>
	<c:forEach items="${lists}" var="dto" varStatus="status">
		<tr><td><a href="boardDetail?num=${dto.id}">${status.index + 1 }</a></td>
			<td><a href="boardDetail?num=${dto.id}">${dto.boardWriter }</a></td>
			<td><a href="boardDetail?num=${dto.id}">${dto.boardSubject }</a></td></tr>
	</c:forEach>
</tbody>
</table>
</body>
</html>