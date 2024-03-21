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
    <jsp:include page = '../header.jsp'></jsp:include>
    <h1>과제 관리 시스템</h1>
    <h2>회원</h2>
    <form action='/app/member/add' method='post' enctype='multipart/form-data'>
      <div>
       <label>
       이름: <input name='name' type='text'>
       </label>
      </div>
      <div>
       <label>
       이메일: <input name='email' type='text'>
       </label>
      </div>
      <div>
       <label>
       암호: <input name='password' type='password'>
       </label>
      </div>
      <div>
        사진: <input name='file' type='file'>
      </div>
      <div>
        <button>가입</button>
      </div>
    </form>
    <jsp:include page = '../footer.jsp'></jsp:include>
  </body>
 </html>