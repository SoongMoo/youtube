<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#wish").click(function(){
		$.ajax({ //화면은 그대로인 상태에서 관심상품이 등록되거나 취소되어야 한다.
			type:"post",
			url:"wishItem.item",
			data:{"goodsNum":"${dto.goodsNum}"},
			dataType : "text",
			success:function(result){
				if(result == "0"){
					$("#wish").attr("src","images/hart.jpg");
					alert("관심상품으로 등록되었습니다.");
				}else{
					$("#wish").attr("src","images/hart1.jpg");
					alert("관심상품으로 등록이 취소되었습니다.");
				}
			},
			error:function(){
				alert('로그인 아웃 되었습니다.\n다시 로그인 해 주세요.');
				location.href='<c:url value="/"/>';
				return false;
			}
		});
	});
});
</script>
</head>
<body>
<table width="800" align="center">
	<tr><td colspan="2"> (${dto.goodsName })의 상품정보입니다. | 상품 조회수 : ${dto.visitCount}</td></tr>
	<tr><td rowspan="5">
			<img src="goods/images/${dto.goodsMainStore }" height="200" />
		</td><td>상품명 : ${dto.goodsName }</td></tr>
	<tr><td>가격 : ${dto.goodsPrice }</td></tr>
	<tr><td>배송비 :  <c:if test="${dto.deliveryCost == 0 }">무료배송</c:if>
					<c:if test="${dto.deliveryCost != 0 }">${dto.deliveryCost}</c:if></td></tr> 
	<tr><td>수량 : <input type="number" id="cartQty" min="1" value="1"step="1" name="cartQty"></td></tr> 
	<tr><td>장바구니 | 바로구매 |  
	<!-- 찜을 했는데도 페이지를 벗어났다가 들어오면 찜이 없는 것처럼 된다. 오류를 해결 -->
		<c:if test="${isTrue == 0 }">
		<img src="images/hart1.jpg" id="wish" width="20" />
		</c:if> 
		<c:if test="${isTrue != 0 }">
		<img src="images/hart.jpg" id="wish" width="20" />
		</c:if> 찜 </td></tr>
	<tr><td colspan="2"><span id="descript">제품 상세 설명</span>|
						<span id="review">리뷰</span> | 
						<span id="inquire" >상품문의</span>
	    </td></tr>
</table>
</body>
</html>