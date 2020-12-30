<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert.jsp</title>
</head>
<body>
	<h3>회원가입</h3>
	<form action="userInsert" method="post" encType="multipart/form-data">
		아이디<input type="text" name="id"><br />
		비밀번호<input type="text" name="password"><br />
		이름<input type="text" name="name"><br />
		<input type="radio" name="role" value="Admin">Admin
		<input type="radio" name="role" value="User">User<br />
		첨부파일<input type="file" name="uploadFile" /><br />
		<input type="submit" value="가입">
	</form>
</body>
</html>