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
<p>응답 내용</p>
<p>설문자 : ${ansData.surveyWriter }</p>
	<c:forEach items="${ansData.responses }" var="response" 
		varStatus="status">
		${status.count }번 문항 : ${response } <br />
	</c:forEach>    
	<p>응답자 위치: ${ansData.res.location }</p>
	<p>응답자 위치: ${ansData.res.userName }</p>
	<p>응답자 나이: ${ansData.res.age }
	
<table  border = 1>
	<tr><td>문항</td><td>번호 1</td><td>번호 2</td><td>번호 3</td><td>번호 4</td></tr>
	
	<c:forEach items="${dtos}" var="dto">
		<tr><td>${dto.questionNum }</td>
		<td>${dto.option1Count }번 / ${dto.option1Probability }%</td>
		<td>${dto.option2Count }번 / ${dto.option2Probability }%</td>
		<td>${dto.option3Count }번 / ${dto.option3Probability }%</td>
		<td>${dto.option4Count }번 / ${dto.option4Probability }%</td></tr>	
	</c:forEach>
	
</table>
</body>
</html>