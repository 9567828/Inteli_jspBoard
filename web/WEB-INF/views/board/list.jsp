<%@ page import="dto.JspBoard" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2024-08-04
  Time: PM 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<JspBoard> boardList = (List) request.getAttribute("boardList");
%>
<html>
<head>
    <title>게시판 목록</title>
</head>
<body>
    <h3>게시판 목록이에여</h3>
    <%
        if (boardList != null && !boardList.isEmpty()) {
            for (JspBoard board : boardList) {
    %>
    <div id="boardList-wrap">
        <div>글번호: <%=board.getBoard_id()%></div>
        <div>글쓴이: <%=board.getBoard_writer()%></div>
        <div>글내용: <%=board.getBoard_content()%></div>
        <div>작성날짜: <%=board.getBoard_write_date()%></div>
        <div onclick="go_comment(<%=board.getBoard_id()%>)">코멘트: <%=board.getCommentCount()%></div>
        <div>좋아요: <%=board.getBoard_good_count()%></div>
        <div>싫어요: <%=board.getBoard_bad_count()%></div>
    </div>
    <%}
    } else {
        System.out.println("게시글 목록이 없다");
    %>
        <p>게시글이 없습니다.</p>
    <%}%>

    <button onclick="location.href='./write'">글쓰러가기</button>

    <script>
        function go_comment(board_id) {
            console.log("board_id: " + board_id)
            location.href='./comment?board_id=' + board_id;
        }
    </script>
</body>
</html>
