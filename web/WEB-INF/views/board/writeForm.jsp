<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2024-08-04
  Time: PM 2:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판작성</title>
</head>
<body>
    <h3>게시판 작성하기</h3>
    <form action="write" method="POST" accept-charset="UTF-8">
        <label for="board_title">글제목</label>
        <input type="text" name="board_title" id="board_title"> <br>
        <label for="board_password">글비밀번호</label>
        <input type="password" name="board_password" id="board_password"> <br>
        <label for="board_writer">글쓴이</label>
        <input type="text" name="board_writer" id="board_writer"> <br>
        <label for="board_content">글내용</label>
        <textarea name="board_content" id="board_content" cols="30" rows="10" style="ime-mode: active;"></textarea> <br>
        <input type="submit" value="글쓰기">
    </form>
</body>
</html>
