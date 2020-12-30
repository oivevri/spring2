<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>단건조회</title>
</head>
<body>
	<h3>유저 조회</h3>

${user.id}<br>
${user.password}<br>
${user.name}<br>
${user.role}<br>
${user.profile}<br>

이미지 보려면 그냥 img 태그 <img src="images/${user.profile}"><br>

이때 파일다운 이거는  @RequestMapping("/filedown") 했던 그 이름이고 uFile도 파라미터 그거고..
<a href="filedown?uFile=${user.profile}">파일다운</a>
</body>
</html>