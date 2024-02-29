<%@ page language="java" import="bitcamp.myapp.vo.Member"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces = "true" %>

<!DOCTYPE html>
  <html lang='en'>
   <head>
      <meta charset='UTF-8'>
      <title>비트캠프 데브옵스 5기</title>
   </head>
    <body>
<jsp:include page="/header"></jsp:include>

    <h1>과제 관리 시스템</h1>
<%Member loginUser = (Member) request.getSession().getAttribute("loginUser");%>

    <%if (loginUser == null) {%>
      <p>환영합니다 ^^</p>
<%} else {%>
      <p>환영합니다 <%=loginUser.getName()%>님^^</p>
<%}%>

<jsp:include page="/footer"></jsp:include>

   </body>
  </html>