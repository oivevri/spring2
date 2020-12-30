<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.error{
	color: red;
}
</style>
<title>insert.jsp</title>
</head>
<body>
   <h1>사원등록/수정</h1>
   
   <form:form action="empInsert" method="post" modelAttribute="emp"><!--이상태에서 default는 Get방식임  -->
   <c:if test="${not empty employeeId}">
   <form:input path="employeeId" readonly="readonly"/><br>
   </c:if>
   <!-- 이때 코드값은 라벨.프로퍼티에 적은 그거임 -->
   <spring:message code="emp.firstName"></spring:message>
   <form:input path="firstName" placeholder="firstName" /><br>
   <spring:message code="emp.lastName"></spring:message>
   <form:input path="lastName" placeholder="lastName"/><br>
   <form:errors path="lastName" cssClass="error" htmlEscape="false"></form:errors><br>
   <spring:message code="emp.email"></spring:message>
   <form:input path="email" placeholder="email"/><br>
   <form:errors path="email" cssClass="error" htmlEscape="false"></form:errors><br>
   <form:select path="jobId">
   <form:option value="">job선택</form:option>
   <form:options items="${jobs}"
				 itemLabel="jobTitle"
				 itemValue="jobId" />
   </form:select><br>
   <form:errors path="jobId" cssClass="error" htmlEscape="false"></form:errors><br>
   <form:input path="salary" placeholder="salary"/><br>
   
   <form:checkboxes items="${departments}" path="departmentId"
					itemLable="departmentName"
					itemValue="departmentId"/><br>
   <form:input path="hireDate" placeholder="hireDate"/><br>
   
   <button>등록</button>
   </form:form>
</body>
</html>