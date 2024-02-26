<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="surveyRegist">설문입력하기</a> 
<a href="surveyList">설문조사하러가기</a><br />
</a><form action="/submit-form" method="post">
    <input type="text" name="key1" />
    <input type="text" name="key2" />
    <!-- 여기에 필요한 다른 입력 요소들을 추가할 수 있습니다 -->
    <button type="submit">Submit</button>
</form>
</body>
</html>