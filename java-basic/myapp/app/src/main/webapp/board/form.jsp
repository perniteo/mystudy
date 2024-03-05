<%@ page language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces = "true"%>

<!DOCTYPE html>
 <html lang='en'>
  <head>
    <meta charset='UTF-8'>
    <title>비트캠프 데브옵스 5기</title>
  </head>

   <body>
<jsp:include page = '/header.jsp'></jsp:include>

    <h1>과제 관리 시스템</h1>
    <h2>${title}</h2>
    <form action='/app/board/add?category=${category}' method='post' enctype='multipart/form-data'>
      <div>
       <label>
       <input readonly name='category' type='hidden' value='${category}'>
       </label>
      </div>
      <div>
       <label>
       제목: <input name='title' type='text'>
       </label>
      </div>
      <div>
       <label>
       내용: <textarea name='content'></textarea>
       </label>
      </div>
      <div>
        첨부파일: <input multiple name='files' type='file'>
      </div>
      <div>
        <button>등록</button>
      </div>
    </form>
<jsp:include page = '/footer.jsp'></jsp:include>
  </body>
 </html>