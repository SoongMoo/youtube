<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="survey" method="post">
<input type="text"  name="surveyNum" value="${surveyNum }"/>
<input type="text"  name="surveyWriter" value="${surveyWriter }"/>
<c:forEach items="${questions}" var="q" varStatus="status">
<p>
	${q.questionDTO.questionNum }번 문제 ) ${q.questionDTO.questionTitle } <br />
	<c:if test="${q.options != null}">
		<c:forEach items="${q.options }" var="option">
		<input type="radio" name="responses[${q.questionDTO.questionNum}]" value="${option.optionsNum }" />${option.optionsContent }
		</c:forEach>
	</c:if>
	<c:if test="${q.options == null}">
		<input type="text" name="responses[${q.questionDTO.questionNum}]">
	</c:if>
</p>	
</c:forEach>
<p>
<label>응답자 위치:<br>
<input type="text" name="res.location">
</label>
</p>
<p>
<label>응답자:<br>
<input type="text" name="res.userName">
</label>
</p>
<p>
<label>응답자 나이:<br>
<input type="text" name="res.age">
</label>
</p>
<input type="submit" value="전송">
</form>
</body>
</html>