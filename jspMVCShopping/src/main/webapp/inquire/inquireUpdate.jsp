<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>inquireUpdate.jsp</title>
</head>
<body>
<form method="post" action="inquireModify.inq" id="frmMain">
<input type="hidden" name="inquireNum" value="${dto.inquireNum}">
<table width="600" align="center" border=1>
	<caption>상품명 : ${dto.goodsNum}</caption>
	<tr><td>
			<select name="queryType">
				<option value="상품" <c:if test="${dto.inquireKind =='상품' }">selected</c:if> >상품(성능/사이즈) </option>
				<option <c:if test="${dto.inquireKind =='배송' }">selected</c:if> >배송</option>
				<option <c:if test="${dto.inquireKind =='교환' }">selected</c:if> >교환</option>
				<option <c:if test="${dto.inquireKind =='반품/취소/환불' }">selected</c:if> >반품/취소/환불</option>
				<option <c:if test="${dto.inquireKind =='기타' }">selected</c:if> >기타</option>
			</select>
		</td>
		</tr>
		<tr >
			<th>제목</th>
			<td><input type="text" name="inquireSubject" value="${dto.inquireSubject}"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea cols="50" rows="10" name="inquireContent">${dto.inquireContent}</textarea>
			</td>
		</tr>	
		<tr><td colspan="2">
			<input type="submit" value="문의수정" /> 
			<input type="button" value="수정취소" onclick="javascript:self.close();"/>
		</td></tr>
</table>
</form>
</body>
</html>