<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="empInsertForm">사원 등록</a>
사원 등록
<c:forEach items="${list}" var="emp">
<a href="empUpdateForm?employeeId=${emp.employeeId}">${emp.employeeId}</a>
${emp.firstName}
${emp.lastName}<br>
</c:forEach>
</body>
</html>