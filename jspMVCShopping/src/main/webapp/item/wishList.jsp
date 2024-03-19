<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>wishList.jsp</title>
</head>
<script type="text/javascript">
function wishDelete(goodsNum){
	location.href="wishDelete.item?goodsNum="+goodsNum;
}
</script>
<body>
관심상품<br />
<table width="600" >
	<tr><td>상품번호</td><td>이미지/상품명</td><td>가격</td><td>등록일</td></tr>
<c:forEach items="${list }" var="dto" >
	<tr><td>${dto.goodsNum }</td>
		<td><img src="goods/images/${dto.goodsMainStore }"  width="30" />
			/${dto.goodsName }</td>
		<td>${dto.goodsPrice }</td>
		<td>${dto.wishDate } <button type="button" onclick="wishDelete('${dto.goodsNum }');">삭제하기</button></td></tr>
</c:forEach>
</table>
</body>
</html>