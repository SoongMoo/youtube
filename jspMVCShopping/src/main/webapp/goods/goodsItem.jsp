<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.1.min.js"></script>
<script type="text/javascript">
function goodsItem(goodsNum, goodsName){
	// 매개변수 값을 부모창으로 보내야 한다.$(opener.document).find()를 하면 부모창의 element를 찾는다.
	$(opener.document).find("#goodsNum").val(goodsNum);
	$(opener.document).find("#goodsName").val(goodsName);
	// 자신의 창을 닫는다.
	window.self.close();
} 
</script>
</head>
<body>
<form action="goodsItem.goods" method="post">
<table>
	<tr><th>
		검색 : <input type="search" name="goodsWord"  value="${goodsWord }"> 
		<input type="submit" value="확인">
	</th></tr>
</table>
<table border=1 width="600" align="center">
	<tr>
		<th colspan="3">상품 리스트</th>
		<th width = "100">상품 개수 : ${dtos.size() }</th>
	</tr>
	<tr>
		<th>번호</th><th>상품 번호</th><th>상품명</th><th>조회수</th>
	</tr>
	<c:forEach items="${dtos }" var="dto" varStatus="status">
	<tr>
			<th>${status.count }</th>
			<th><a href="javascript:goodsItem('${dto.goodsNum }','${dto.goodsName }')">
				${dto.goodsNum }</a></th>
			<th>${dto.goodsName }</th>
			<th>${dto.visitCount }</th>
	</tr>
	</c:forEach>
</table>
</form>
</body>
</html>