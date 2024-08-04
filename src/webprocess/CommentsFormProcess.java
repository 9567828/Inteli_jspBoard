package webprocess;

import dao.HikariConnector;
import dto.Comments;
import dto.JspBoard;
import webInter.WebProcess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentsFormProcess implements WebProcess {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        HikariConnector db = (HikariConnector) request.getServletContext().getAttribute("hikari");

        List<Comments> commentsList = new ArrayList<>();

        String boardContentSql = "SELECT * FROM board2 WHERE board_id = ?";
        String commentsSql = "SELECT * FROM comments2 WHERE board_id = ?";

        int boardId = Integer.parseInt(request.getParameter("board_id"));

        System.out.println("게시글 확인용 boardId: " + boardId);

        try(
            Connection conn = db.getConnection();
            PreparedStatement boardPs = conn.prepareStatement(boardContentSql);
            PreparedStatement commentsPs = conn.prepareStatement(commentsSql);
        ){
            boardPs.setInt(1, boardId);
            try(ResultSet rs = boardPs.executeQuery();){
                if (rs.next()) {
                    JspBoard board = new JspBoard(rs);
                    request.setAttribute("board", board);
                } else {
                    request.setAttribute("notfoundBoard", "게시글을 찾을 수 없습니다.");
                    return "/error/notfound";
                }
            }

            commentsPs.setInt(1, boardId);
            try (ResultSet rs = commentsPs.executeQuery();){
                while (rs.next()) {
                    Comments comments = new Comments(rs);
                    commentsList.add(comments);
                }
                request.setAttribute("commentsList", commentsList);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "/board/comment";
    }
}
