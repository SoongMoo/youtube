<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${!empty auth }">
이제 쇼핑몰을 시작하겠습니다.<br />
<ul>
	<c:if test="${auth.grade == 'emp' }">
		<li><a href="memberList.mem">회원 목록</a></li>
		<li><a href="employeeList.emp">직원 목록</a></li>
		<li><a href="goodsList.goods">상품리스트</a></li>	
		<li><a href="memPurchaseList.deli" >회원구매정보</a></li>	
		
		<li><a href="inquire.inq" >상품문의</a></li>
		
		<li><a href="empMyPage.my">내정보 보기</a></li>
	</c:if>

	<c:if test="${auth.grade == 'mem' }">
		<li><a href="wishList.item">관심상품</a></li>
		<li><a href="memberMyPage.my">내정보 보기</a></li>
		<li><a href="cartList.item">장바구니</a></li>
		<li><a href="purchaseList.item">주문조회</a></li>
	</c:if>
		<li><a href="logout.login">로그아웃</a></li>
</ul>
</c:if>


<c:if test="${empty auth }">
<form action="login.login" method="post" >
<table border="1" align="center">
	<tr><td colspan="2">
			 <input type="checkbox" name="keepLogin" value="on"/>로그인유지 | 
			 <input type="checkbox" name="storeId" value="store" 
			 	<c:if test="${!empty sid}">checked</c:if> />아이디저장
		</td></tr>
	<tr><td>
			<input type="text" name="userId"  placeholder="아이디" value="${sid}" />
			<div style="color:red">${errId }</div>
		</td>
		<td rowspan="2">
			<input type="submit" value="로그인" />
		</td></tr>
	<tr><td>
			<input type="password" name="userPw" placeholder="비밀번호"/>
			<div style="color:red">${errPw }</div>
		</td></tr>
	<tr><td colspan="2">
			<a href="idInquiry.help">아이디</a> / 
			<a href="pwInquiry.help">비밀번호 찾기</a> | 
			<a href="userAgree.nhn">회원가입</a>
		</td></tr>
</table>
</form>
</c:if>

<table align = "center"  >
	<colgroup>
		<col span="3" width="300" height="300" />
	</colgroup>
	<tr>
		<c:forEach items="${dtos }" var="dto" varStatus="status" >
			<td><a href="detailView.item?goodsNum=${dto.goodsNum }">
				<img src="goods/images/${dto.goodsMainStore }" width="300" height="150"/><br />
				${dto.goodsName }<br />
				${dto.goodsPrice }원<br />
				<c:if test="${dto.deliveryCost == 0 }">무료배송</c:if> 
				<c:if test="${dto.deliveryCost > 0 }">${dto.deliveryCost}원</c:if>
				</a> 
			</td>
			<c:if test="${status.count % 3 == 0 }">
			</tr><tr>
			</c:if>
		</c:forEach>	
	</tr>
</table>













</body>
</html>