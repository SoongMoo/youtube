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
	//checkBoxs를 체크하면 모든 상품의 케크박스에 체크가 되게 한다... 
	$("#checkBoxs").click(function(){
		if($("#checkBoxs").prop("checked")){
			$("input:checkbox[name='prodCk']").prop("checked",true);
		}else{
			$("input[name='prodCk']").prop("checked",false);
		}
		prodChk(); //checkBoxs를 클릭했을 때 상품수, 총수량, 전체 금액이 달라지게 해야 한다.
	});
	// 상품의 체크박스에 적어도 하나만 체크를 안해도 checkBoxs에 체크를 풀리게 한다.
	$("input[name='prodCk']").click(function(){
		var checkCk = $("input[name='prodCk']").length;
		var checked = $("input[name='prodCk']:checked").length;
		if(checkCk != checked)$("#checkBoxs").prop("checked", false);
		else $("#checkBoxs").prop("checked",true);
		prodChk(); // 체크할 때마다 prodChk()함수가 실행되어서 상품수, 총수량, 전체 금액이 달라지게 한다.
	}); // 이미 앞에서 설명이 된 부분이라 복사 붙여 넣기 합니다.
});
/// 체크박스의 체크에 따라서 상품수, 총수량, 전체 금액이 달라지게 하겠습니다.
function prodChk(){
	var cnt = 0; // 상품 수
	var totalPrice = 0; // 총 상품 금액
	var totalQty = 0; // 총 상품의 갯수
	// 체크 박스의 갯수 만큼 돌리나 상품의 수만큼 돌리나 같은 의미이므로 상품의 수(dtos.size())를 가지고 오겠습니다.
	for(var i = 0 ; i < ${dtos.size()}; i++){
		if($("input[name='prodCk']")[i].checked == true){ // 사품 체크벅스에 체크가 되어있다면
			cnt++; // 상품의 수
			totalPrice +=  //체크된 상품의 총 금액
				Number($(".cartPrice:eq("+i+")").text());	
			totalQty +=  // 체크된 상품의 총 수량
				Number($(".cartQty:eq("+i+")").text());
		}
		$("#prodCnt").text(cnt);
		$("#totQty").text(totalQty);
		$("#totalPrice").text(totalPrice);
	}
}
function cartItemDel(goodsNum){
	con = confirm ("정말 삭제하시겠나?");
	if(con) location.href='cartItemDel.item?goodsNum='+goodsNum;
}
function itemsDel(){ //조건을 안 주면  서버에서 오류가 발생할 수 있으므로 조건을 주는 것이 좋다.,
	//삭제 전에 하나 이상 체크가 되어야 한다는 조건이 있으면 좋을 것 같습니다.
	if ($("input[name='prodCk']:checked").length <= 0){ // 한도 체크가 되어 있지 않다면...
		alert("적어도 하나이상 체크되어 있어야합니다.");
		return false;	
	}else{ // 하나이상 체크가 되어 있다면 
		var goodsNums = ""; // prodCk에 체크되어 있는 모든 상품 삭제, 체크 박스에 있는 value를 가져온다.
		$("input:checkbox[name='prodCk']:checked").each(function(){
			goodsNums += $(this).val() +"-"; // 상품명을 "-"로 묶었다.
		});
		location.href="cartItemsDel.item?goodsNums="+goodsNums;
	}
}
function goodsCartAdd(goodsNum){
	$.ajax({ // 이미 장바구니에 추가하는 주소가 있으므로 그 주소를 그대로 사용합니다.
		url: "cart.item",
		type: "post",
		data: {"goodsNum":goodsNum,"cartQty":1 }, //수량을 1씩 증가하는 것이므로 $("#cartQty").val()대신 1을
		success : function(){
			location.href="cartList.item";
		},
		error : function(){ // detailView에 있는 코드 그대로 사용
			 alert("로그 아웃되었습니다. 다시로그인 해주세요.");
			 window.open("loginCk.login","loginck","width=400,height=400");		
		}
	});
}
function checkQty(goodsNum,  idx, goodsPrice){
	cartQty = $(".cartQty:eq("+idx+")").text(); // 이코드로 변경
	if(cartQty > 1){ // 최소 수량이 1이상이어야 하므로 상품의 갯수도 가져온 것입니다.
		$.ajax({
			url: "cartQtyDown.item", //이제 cartQtyDown를 만들어 줘서 수량이 1씩 감소하게 합니다.
			type: "post",
			data: {"goodsNum":goodsNum},
			success : function(){
				$(".cartQty:eq("+idx+")").text(
						Number($(".cartQty:eq("+idx+")").text()) - 1);
				$(".cartPrice:eq("+idx+")").text(
						Number($(".cartPrice:eq("+idx+")").text()) - Number(goodsPrice));
			},
			error : function(){
				alert("서버에 접속하지 못했습니다. 다시 시도해주세요.");
				return;
			}, // 이제 체크되어 있는 상품에 대해 상품수, 총수량, 전체 금액이 달라지게 한다.
			complete:prodChk // complete은 success된 후 실행됩니다.
		});
	}else{
		alert("최소수량은 1이상이어야 합나다.");
		return false;
	}
}
<!-- 상품이 없으면 구매 할 수 없으므로 선택된 상품이 있 는지 확인하는 함수를 만들어줍니다.-->
function goodsCheck(){
	if ($("input[name='prodCk']:checked").length <= 0){
		alert("적어도 하나이상 체크되어 있어야합니다.");
		return false;
	}
}
</script>
</head>
<body> <!-- 전송전에 확인을 하도록 합니다. -->
<form action="itemBuy.item" method="post" onsubmit = "return goodsCheck();">
<table width="600" align = "center">
<!-- 장바구니에 있는 원하는 상품들을 체크 할 수 있게 체크박스를 만들어줍니다. -->
	<tr><td><input type="checkbox" id="checkBoxs" checked="checked" /></td>
		<td>이미지</td><td>제품이름</td><td>수량</td><td>합계금액</td>
		<!-- 선택한 상품을 삭제 할 수 있게 버튼을 추가합니다... -->
		<td><button type="button" onclick="itemsDel();" >선택상품삭제</button></td>
	</tr>
	<c:forEach items="${dtos }" var="dto" varStatus="status">
		<!-- 각각의 상품에도 추가 -->
		<tr><td><input type="checkbox" name="prodCk" value="${dto.goodsNum }" checked="checked" /></td>
			<td><img src="goods/images/${dto.goodsImage }" width="30"/></td>
			<td>${dto.goodsName }</td>
			<!-- 상품 수량 수정하기--><!-- 상품수량을 1씩 감소할 수 있게 checkQty()함수를 만들어줍니다, -->
			<td><a href="javascript:checkQty('${dto.goodsNum }','${status.index }'
											,'${dto.goodsPrice }');" > - </a>
			    <span class="cartQty"> ${dto.cartQty }</span>
			    <!-- 상품수량을 1씩 증가할 수 있게 goodsCartAdd()함수를 만들어줍니다, -->
			 	<a href="javascript:goodsCartAdd('${dto.goodsNum }');"> + </a>
			</td><td><span class="cartPrice">${dto.totalPrice }</span></td>
			<!-- 각가의 상품 삭제 버튼 만들어 줍니다. -->
			<td><button type="button" onclick="javascript:cartItemDel('${dto.goodsNum }')">상품삭제</button></td>
			</tr>
	</c:forEach>
<tr><td colspan="6" align="right">	
		상품수 : <span id="prodCnt">${dtos.size() }</span>개<br />
		총수량 : <span id="totQty">${totQtyt }</span>개<br />
		전체 금액 : <span id="totalPrice">${totPri}</span>원  
	</td>
</tr>
<!-- 선택한 상품 구매하기 -->
<tr><td colspan="6" align="center">	<input type="submit" value="구매하기"/></td></tr>
</table>
</form>
</body>
</html>