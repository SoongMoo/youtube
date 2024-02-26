<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
설문조사 리스트<br />
<table width="800px" >
	<tr><td>번호</td><td>설문자</td><td>설문제목</td><td>설문시작일</td><td>설문종료일</td> <td>진행상태</td></tr>
	<c:forEach items="${list }" var="dto" varStatus="status"> 
	<tr><td>${status.count }</td>
		<td>
			<c:if test="${dto.surveyStatus == '진행 중'}">
				<a href="survey?surveyWriter=${dto.surveyWriter }&num=${dto.surveyNum}">${dto.surveySubject }</a>
			</c:if>
			<c:if test="${dto.surveyStatus != '진행 중'}">
			${dto.surveySubject }
			</c:if>
			</td>
		<td>${dto.surveyWriter }</td><td>${dto.surveyStart }</td><td>${dto.surveyEnd }</td>
		<td>${dto.surveyStatus }</td></tr>
	</c:forEach>
</table>
</body>
</html>