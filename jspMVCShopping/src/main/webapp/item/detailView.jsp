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
	$("#cartBtn").click(function(){
		//화면은 그대로인 상태에서 상품을 장바구니에 담아야 하므로 ajax를 사용하겠습니다.
		// 단 로그인되어야 하므로 로그인 여부를 물어봅니다.
		if(${auth != null}){
			//로그인 상태라면 회원만 사용가능하므로 직원은 사용하지 못하도록 하겠습니다.
			if(${auth.grade == 'mem'}){
				$.ajax({ 
					type : "post",
					url : "cart.item", // 상품을 장바구니에 추가하는 주소 
					dataType : "html", // 서버에 전달되는 값으로 goodsNum과 cartQty를 전달합니다.
					data : {"goodsNum":"${dto.goodsNum}","cartQty":$("#cartQty").val()}, 
					success : function(){
						//정상적으로 처리되었다면 계속 쇼핑할 건지 , 장바구니로 이동할 건지 물어보겠습니다.
						con = confirm("장바구니로 이동하시겠습니까?");
						if(con){
							location.href="cartList.item";
						}
					},
					error : function(){
						// loginCk.login부터 처리
						 alert("로그 아웃되었습니다. 다시로그인 해주세요.");
						 window.open("loginCk.login","loginck","width=400,height=400");		
					}
				});
			}else{
				alert("직원은 직원전용 페이지를 사용하세요.");
			}
		}else{//로그인 상태가 아니면 로그인 창이 열리게 합니다.
			window.open("loginCk.login","loginck","width=400,height=400");		
		}
	});
	
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
	<!-- 상품을 장바구니에 담는 것을 해보겠습니다. -->
	<tr><td> <button type="button" id="cartBtn">장바구니</button> | 
				바로구매 |  
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