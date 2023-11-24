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
<ul>
	<li><a href="goodsWrite.goods">상품 추가</a></li>
	<li><a href="goodsIpgo.ipgo">상품 입고</a></li>
	<li><a href="goodsIpgoList.ipgo">상품 입고 현황</a></li>
</ul>
입고현황<br/>
<table border=1 width="600" align="center">
<!-- 입고 상품 상세보기 -->
	<tr><td>번호</td><td>입고번호</td><td>상품번호</td><td>상품가격</td><td>수량</td><th>입고일</th></tr>
	<c:forEach items="${dtos }" var="dto" varStatus="status">
		<tr><td>${status.count }</td>
			<td><a href="goodsIpgoDetail.ipgo?ipgoNum=${dto.goodsIpgoNum }&num=${dto.goodsNum }">
				${dto.goodsIpgoNum }</a></td>
			<td><a href="goodsIpgoDetail.ipgo?ipgoNum=${dto.goodsIpgoNum }&num=${dto.goodsNum }">
				${dto.goodsNum }</a></td>
			<td>${dto.ipgoPrice }</td><th>${dto.ipgoQty }</th>
			<td>${dto.ipgoDate }</td></tr>
	</c:forEach>
</table>
</body>
</html>