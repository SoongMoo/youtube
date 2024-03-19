<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1 align="center" width="600">
	<caption>${dto.goodsName } 상품 상세보기</caption>
	<tr><th width="150" >상품번호</th><td>${dto.goodsNum }</td></tr>
	<tr><th width="150" >상품명</th><td>${dto.goodsName }</td></tr>
	<tr><th>상품가격</th><td>${dto.goodsPrice }</td></tr>
	<tr><th>배송비</th><td>${dto.deliveryCost }</td></tr>
	<tr><th>상품설명</th><td>${fn:replace(dto.goodsContent,newLine,'<br />')}</td></tr>
	<tr><th>조회수</th><td>${dto.visitCount }</td></tr>
	<tr><th>등록한 사원</th><td>${dto.empNum }</td></tr>
	<tr><th>등록일</th><td>${dto.goodsRegist }</td></tr>
	<tr><th>마지막 수정 사원</th><td>${dto.updateEmpNum }</td></tr>
	<tr><th>마지막 수정일</th><td>${dto.goodsUpdateDate }</td></tr>
	<tr><th>대문이미지</th><td>${dto.goodsMainStoreImg }</td></tr>
	<tr><th>상세이미지</th>
		<td>
			<c:forTokens items="${dto.goodsImagesImg }" var="image" delims="`">
				${image }<br />
			</c:forTokens>
		</td></tr>
	<tr><th colspan="2">
	    	대문이미지<br />
	    	<img src="goods/images/${dto.goodsMainStore }"/><br />
	    	상세이미지<br />
	    	<c:forTokens items="${dto.goodsImages }" delims="`" var="image">
	    		<img src="goods/images/${image }"/><br />
	    	</c:forTokens>
		</th></tr>
	<tr><th colspan="2">
		<a href="goodsUpdate.goods?goodsNum=${dto.goodsNum }">상품수정</a> | 
		<a href="goodsDelete.goods?goodsNum=${dto.goodsNum }">상품 삭제</a> | 
		<a href="goodsList.goods">상품목록</a></th></tr>
</table>
</body>
</html>