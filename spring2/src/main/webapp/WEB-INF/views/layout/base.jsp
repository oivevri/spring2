<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Hello World</title>
  <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath}/resources/main/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/resources/main/css/modern-business.css" rel="stylesheet">
<title>Insert title here</title>
</head>
   <body>
   베이스 입니다.
    <section class="content">
      <tiles:insertAttribute name="header"/> <!--  /WEB-INF/views/common/layout/header.jsp -->
      <tiles:insertAttribute name="body"/> <!-- body -->

      <tiles:insertAttribute name="footer"/> <!-- /WEB-INF/views/common/layout/footer.jsp -->
    </section>
  </body>
</html>