<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width="600" align="center">
	<caption>회원구매정보</caption>
	<tr><td>구매번호/승인번호</td><td>결제금액</td><td>회원번호</td><td>결제상태</td></tr>
	<c:forEach items="${list }" var="dto">
	<tr><td>${dto.purchaseNum }/
			<c:if test="${empty dto.applDate }">결제대기중</c:if>
			<c:if test="${! empty dto.applDate }">
				<c:if test="${empty dto.confirmNum }">네이버/카카오/토스</c:if>
				<c:if test="${!empty dto.confirmNum }">${dto.confirmNum }</c:if>
			</c:if>
			
		</td><td>${dto.purchasePrice }</td>
		<td>${dto.memberNum }</td>
		<td><c:if test="${ empty dto.applDate }">결제대기중</c:if>
			<c:if test="${ !empty dto.applDate }">
				<c:if test="${dto.deliveryNum == 0}">
					결제완료
					<a href="deliveryRegist.deli?purchaseNum=${dto.purchaseNum }">
						배송정보등록
					</a>
				</c:if>
				<c:if test="${dto.deliveryNum != 0 }">
					<c:if test="${dto.deliveryState == '배송중'}">
						<a href="deliveryModify.deli?purchaseNum=${dto.purchaseNum }">
							${dto.deliveryState}</a>
						<a href="deliveryRegist.deli?purchaseNum=${dto.purchaseNum }">
						배송정보수정
						</a>
					</c:if>
					<c:if test="${dto.deliveryState == '배송완료'}">${dto.deliveryState}</c:if>
				</c:if> 
			</c:if>
		</td></tr>
	</c:forEach>
</table>
</body>
</html>