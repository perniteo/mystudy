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
    <body>

<jsp:include page="../header.jsp"></jsp:include>

    <h1>과제 관리 시스템</h1>
    <h2>로그인</h2>
    <form action='/app/auth/login' method='post'>
     <div>
  이메일: <input name='email' type='text' value='${email}'>
     </div>
    <div>
     암호: <input name='password' type='password'>
     </div>
    <button>로그인</button>

    <c:choose>
    <c:when test="${not empty email}">
            <input name = 'saveEmail' type = 'checkbox' checked> 이메일 저장
    </c:when>
    <c:otherwise>
            <input name = 'saveEmail' type = 'checkbox'> 이메일 저장
    </c:otherwise>
    </c:choose>
    </form>

<jsp:include page="../footer.jsp"></jsp:include>

    </body>
    </html>