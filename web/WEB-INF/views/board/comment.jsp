<%@ page import="dto.Comments" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.JspBoard" %>
<%@ page import="java.util.LinkedList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Comments> commentsList = (List) request.getAttribute("commentsList");
    JspBoard board = (JspBoard) request.getAttribute("board");

    if (board == null) {
        response.sendRedirect("/error/notfound");
        return;
    }

    if (commentsList == null) {
        commentsList = new ArrayList<>();
    }
%>
<html>
<head>
    <title>댓글쓰기</title>

    <link rel="stylesheet" href="/resources/css/style.css">
    <script src="https://kit.fontawesome.com/d95a86acc3.js" crossorigin="anonymous"></script>
</head>
<body>
    <div id="board-wrap">
        <div><%=board.getBoard_writer()%>님의 글</div>
        <div>내용: <%=board.getBoard_content()%></div>
        <div>작성일: <%=board.getBoard_write_date()%></div>
    </div>

    <div id="comment-wrap">
        <% for (Comments comment : commentsList) { %>
        <div>댓쓴이: <%=comment.getComment_writer()%></div>
        <div>댓글: <%=comment.getComment_content()%></div>
        <% } %>
    </div>

    <div id="comment-write">
        <p>댓글쓰기</p>
        <form action="comment" method="POST">
            <input type="hidden" name="board_id" value="<%=board.getBoard_id()%>">
            <label for="comment_writer">작성자: </label>
            <input type="text" name="comment_writer" id="comment_writer">
            <label for="comment_password">비밀번호: </label>
            <input type="password" name="comment_password" id="comment_password">
            <label for="comment_content">내용</label>
            <textarea name="comment_content" id="comment_content" cols="30" rows="10" style="ime-mode: active;"></textarea>
            <input type="submit" value="확인">
        </form>
    </div>
    <button onclick="location.href='/board/'">목록으로</button>

</body>
</html>
