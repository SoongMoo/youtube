<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deliveryRegist.jsp</title>
</head>
<body>
<form action="deliveryWrite.deli" method="post">
<table width="600" align="center">
	<caption>배송정보 등록</caption>
	<tr><td>주문번호</td>
		<td><input type="text"  name="purchaseNum" value='${purchaseNum }'
				readonly="readonly" /></td></tr>
	<tr><td>송장번호</td>
		<td><input type="text"  name="deliveryNum" value="${deliveryNum }" required="required"/></td></tr>
	<tr><td colspan="2"><input type="submit" value="송장번호등록" /></td></tr>
</table>
</form>
</body>
</html>