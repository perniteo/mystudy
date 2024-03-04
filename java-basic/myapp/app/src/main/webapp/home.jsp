<%@ page language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces = "true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
  <html lang='en'>
   <head>
      <meta charset='UTF-8'>
      <title>비트캠프 데브옵스 5기</title>
   </head>
    <body>
<jsp:include page="/header"></jsp:include>

<h1>과제 관리 시스템</h1>

<c:if test="${empty loginUser}">
<p>환영합니다 ^^</p>
</c:if>

<c:if test="${not empty loginUser}">
<p>환영합니다 ${loginUser.name}님</p>
</c:if>

<jsp:include page="/footer"></jsp:include>

   </body>
  </html>