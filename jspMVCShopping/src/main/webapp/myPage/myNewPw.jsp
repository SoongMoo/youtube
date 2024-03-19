<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.1.min.js"></script>
<script type="text/javascript">
//$(document).ready();
$(function(){
	$("#frm").submit(function(){
		if($("#newPw").val() !=  $("#newPwCon").val()){
			alert("비밀번호가 틀렸습니다.");
			$("#oldPw").val("");
			$("#newPw").val("");
			$("#newPwCon").val("");
			$("#newPw").focus();
			return false
		}
	});
});
</script>
<script type="text/javascript">
function doSubmit(){
	if(document.getElementById("newPw").value != 
		document.getElementById("newPwCon").value ){
		alert("비밀번호가 틀렸습니다.");
		document.getElementById("newPw").value = "";
		document.getElementById("newPwCon").value = "";
		document.getElementById("newPw").focus();
		return false;
	}
}
</script>
</head>
<body>
<ul>
	<li><a href="memberMyPage.my">내정보 보기</a></li>
	<li><a href="memberUpdate.my">내정보 수정</a></li>
	<li><a href="userPwModify.my">비밀번호변경</a></li>
	<li><a href="memberDrop.my">회원탈퇴</a></li>
</ul>
<form action="memberPwPro.my" method="post" id="frm">
현재비밀번호:<input type="password" name="oldPw" id="oldPW" required="required" /><br />
새 비밀번호:<input type="password" name="newPw" id="newPw" required="required" /><br />
새 비밀번호 확인:<input type="password" name="newPwCon" id="newPwCon" required="required" /><br />
<input type="submit" value="비밀번호 변경" /> 
</form>
</body>
</html>