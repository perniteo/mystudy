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
<form action='/assignment/update' method='post'>

    <div>
    <label>
    번호: <input readonly name='no' type='text' value='${assignment.no}'>
      </label>
    </div>
    <div>
    <label>
    제목: <input name='title' type='text' value='${assignment.title}' required>
      </label>
    </div>
    <div>
    <label>
    내용: <textarea name='content' required>${assignment.content}</textarea>
     </label>
     </div>
    <div>
    마감기한: <input name='deadline' type = 'date' value = '${assignment.deadline}' required>
     </div>
    <div>
      <button>변경</button>
    <a href = '/assignment/delete?no=${assignment.no}'>[삭제]</a>
    </div>
    </form>

<jsp:include page="/footer"></jsp:include>

    </body>
    </html>