<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reviewList.jsp</title>
</head>
<body>
<c:forEach items="${dtos }" var="dto">
<table width="800" align="center" >
	<tr><td>${dto.memberId } | ${dto.reviewDate } </td></tr>
	<tr><td>${fn:replace(dto.reviewContent,newLine,"<br />") }<hr /></td></tr>
</table>
</c:forEach>
</body>
</html>