<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>purchaseList.jsp</title>
<script type="text/javascript">
function purchased(purchaseNum){
	location.href="purchased.item?purchaseNum="+purchaseNum;
}
function reviewRegist(purchaseNum, goodsNum){
	location.href="reviewRegist.review?purchaseNum="+purchaseNum+"&goodsNum="+goodsNum;
}
</script>
</head>
<body>
<table width="800" align="center">
	<tr><td>주문번호 / 결제번호</td><td> 상품명 </td><td> 주문상태 </td></tr>
<c:forEach items="${list }" var="dto">	
	<tr><td>${dto.purchaseNum } / 
			<c:if test="${empty dto.confirmNum }">결제대기중</c:if>
			<c:if test="${!empty dto.confirmNum }">${dto.confirmNum }</c:if> </td>
	<td> ${dto.goodsName } </td>
	<td> <c:if test="${empty dto.confirmNum }">
			<a href="paymentOk.item?purchaseNum=${dto.purchaseNum }">결제하기</a>
		 </c:if> 
		 <c:if test="${!empty dto.confirmNum }">
		 	<c:if test="${dto.deliveryNum == 0}">
		 		<a href="paymentDelete.item?purchaseNum=${dto.purchaseNum }">결제취소</a>
		 	</c:if>	
		 	<c:if test="${dto.deliveryNum != 0}">
		 		${dto.deliveryState} 
		 		<c:if test="${dto.deliveryState == '배송완료' and dto.purchaseStatus != '구매확정' }">
		 			<button type="button" onclick="purchased('${dto.purchaseNum }');">구매확정</button>
		 		</c:if>   <!-- 배송중/배송완료 -->
		 		<c:if test="${dto.deliveryState == '배송완료' and dto.purchaseStatus == '구매확정' }">
		 			<button type="button" 
		 			onclick="reviewRegist('${dto.purchaseNum }','${dto.goodsNum}' );">리뷰등록/수정</button>
		 			<c:if test="${dto.reviewNum != 0}">
		 				<a href="reviewDelete.review?reviewNum=${dto.reviewNum}">리뷰삭제</a>
		 			</c:if>
		 		</c:if>
		 	</c:if>
		 </c:if>
	</td></tr>
</c:forEach>
</table>
</body>
</html>