<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang='en'>
  <head>
  <meta charset='UTF-8'>
  <title>비트캠프 데브옵스 5기</title>
</head>
<body>

<jsp:include page="/header"></jsp:include>

<h1>과제</h1>
<a href='/app/assignment/add'>과제 등록</a>
<table border='1'>
    <thead>
    <tr> <th>번호</th> <th>제목</th> <th>내용</th> <th>마감기한</th> </tr>
    </thead>
    <tbody>
<c:forEach items="${list}" var="assignment">
      <tr>
        <td><a href = '/assignment/view?no=${assignment.no}'>${assignment.no}</td>
        <td>${assignment.title}</td>
        <td>${assignment.content}</td>
        <td>${assignment.deadline}</td>
      </tr>
</c:forEach>
    </tbody>
</table>

<jsp:include page="/footer"></jsp:include>

</body>
</html>