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

    <link rel="stylesheet" href="/resources/css/style.css">
    <link href="https://cdn.jsdelivr.net/gh/sun-typeface/SUIT@2/fonts/variable/woff2/SUIT-Variable.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/d95a86acc3.js" crossorigin="anonymous"></script>
</head>
<body>
    <div id="container">
        <div class="inner">
            <h3 class="title-text">게시판 목록이에여</h3>
            <div id="to-right">
                <button id="write-btn" onclick="location.href='./write'">글쓰기</button>
            </div>

            <%
                if (boardList != null && !boardList.isEmpty()) {
                    for (JspBoard board : boardList) {
            %>
            <div id="board-wrap">
                <div id="content-wrap">
                    <div class="user-info">
                        <i class="fa-solid fa-circle-user"></i>
                        <div class="user">
                            <p class="board-writer"><%=board.getBoard_writer()%></p>
                            <p class="write-date"><%=board.getBoard_write_date()%></p>
                        </div>
                    </div>
                    <p class="board-title"><%=board.getBoard_title()%></p>
                    <p class="board-content">글내용: <%=board.getBoard_content()%></p>
                </div>
                <div id="commu-wrap">
                    <div id="talk-wrap">
                        <div id="like-wrap">
                            <i class="fa-regular fa-thumbs-up"></i>
                            <p class="thumbs-up"><%=board.getBoard_bad_count()%></p>
                        </div>
                        <div id="bad-wrap">
                            <i class="fa-regular fa-thumbs-down"></i>
                            <p class="thumbs-down"><%=board.getBoard_bad_count()%></p>
                        </div>
                        <div id="comment" onclick="go_comment(<%=board.getBoard_id()%>)">
                            <i class="fa-regular fa-comment fa-flip-horizontal"></i>
                            <p class="comment-cnt"><%=board.getCommentCount()%></p>
                        </div>
                    </div>
                    <div id="edit-wrap">
                        <i class="fa-regular fa-pen-to-square"></i>
                        <i class="fa-solid fa-trash-can"></i>
                    </div>
                </div>
            </div>
            <%}
            } else {
                System.out.println("게시글 목록이 없다");
            %>
                <p>게시글이 없습니다.</p>
            <%}%>
        </div>
    </div>


    <script src="/resources/js/script.js"></script>
</body>
</html>
