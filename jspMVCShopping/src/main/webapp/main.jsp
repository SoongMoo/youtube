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
<!-- 로그인되었을 때 -->
<c:if test="${!empty auth }">
<ul>
<!-- 직원과 회원으로 분리 : userDAO의 메서드 loginSelect()에서 회원과 직원을 분리-->
<!-- 직원 메뉴 -->
<c:if test="${auth.grade == 'emp' }">
	<li><a href="employeeList.emp">직원 목록</a></li>
	<!-- 회원관리 -->
	<li><a href="memberList.mem">회원 목록</a></li>
	<!-- 상품관리 --><!-- 로그인 할 때 관리자아이디로 로그인 한다.  -->
	<!-- 관리자 계정인 없는 경우 아래 url로 접속하면 직원목록이 열린다. -->
	<!--  http://localhost:8080/jspMVCShopping/employeeList.emp -->
	<li><a href="goodsList.goods">상품리스트</a></li>
</c:if>
<!-- 회원 메뉴 -->
<c:if test="${auth.grade == 'mem' }">
	<li><a href="memberMyPage.my">내정보 보기</a></li>
	<li><a href="wishList.item">관심상품</a></li><!-- 찜한 상품들을 보로가자 -->
	<li><a href="cartList.item">장바구니</a></li> <!--  장바구니 페이지로 이동합니다. -->
</c:if>
	<li><a href="logout.login">로그아웃</a></li>
</ul>
</c:if>
<!-- 비 로그인일 때 -->
<c:if test="${empty auth }">
	<!-- 회원가입과 로그인 -->
	<form action="login.login" method="post" >
		<table border="1" align="center">
			<tr><th colspan="2"><input type="checkbox" name="keepLogin" value="on"/>로그인 유지 | 
								<input type="checkbox" name="storeId" value="store"/>아이디 저장
				</th></tr>
			<tr><th><input type="text" name="userId" placeholder="아이디"/>
					<div style="color:red">${errId }</div></th>
				<th rowspan="2"><input type="submit" value="로그인" /></th></tr>
			<tr><th><input type="password" name="userPw" placeholder="비밀번호"/>
					<div style="color:red">${errPw }</div></th></tr>
			<tr><th colspan="2">
				<a href="idInquiry.help">아이디</a>/
				<a href="pwInquiry.help">비밀번호 찾기</a> | 
				<a href="userAgree.nhn">회원가입</a></th></tr>
		</table>
	</form>
</c:if>
<!-- 메인페이지에 상품 출력하기 -->
<table align = "center">
	<colgroup>
		<col span="3" width="300" height="300"/>
	</colgroup>
	<tr>
		<!-- 행으로 반복하는 것이 아니라 열을 반복한다. -->
		<c:forEach items="${dtos }" var="dto" varStatus="status">
		<td> <!-- td를 클릭하면 상품상세페이지로 이동 -->
			<a href="detailView.item?goodsNum=${dto.goodsNum }">
				<img src="goods/images/${dto.goodsMainStore }" width="300" height="150"/><br />
				${dto.goodsName }<br />
				${dto.goodsPrice }<br />
				<c:if test="${dto.deliveryCost == 0 }">무료배송</c:if>
				<c:if test="${dto.deliveryCost != 0 }">${dto.deliveryCost }</c:if>
			</a>
		</td>
		<!-- td가 3번마다 </tr><tr>을 주어야 상품 3개 마다 행이변경될 것입니다. 3으로나눈 나머지가 0일 때마다 </tr><tr>출력 -->
		<c:if test="${status.count % 3 == 0  }"></tr><tr></c:if>
		</c:forEach>
	</tr>
</table>
</body>
</html>
















