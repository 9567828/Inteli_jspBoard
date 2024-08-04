package webprocess;

import dao.HikariConnector;
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

public class BoardIndexProcess implements WebProcess {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        HikariConnector db = (HikariConnector) request.getServletContext().getAttribute("hikari");

        List<JspBoard> boardList = new ArrayList<>();

        String boardListSql = "SELECT * FROM board";
        String commentSql = "SELECT count(*) AS comment_cnt FROM comments WHERE board_id = ?";

        try (
                Connection conn = db.getConnection();
                PreparedStatement boardListPs = conn.prepareStatement(boardListSql);
                PreparedStatement commentPs = conn.prepareStatement(commentSql);
        ) {
            try (ResultSet rs = boardListPs.executeQuery()) {
                while(rs.next()) {
                    JspBoard jb = new JspBoard(rs);

                    commentPs.setInt(1, jb.getBoard_id());
                    try(ResultSet commentRs = commentPs.executeQuery()) {
                        if (commentRs.next()) {
                            int comment_cnt = commentRs.getInt("comment_cnt");
                            jb.setCommentCount(comment_cnt);
                            System.out.println("Board ID: " + jb.getBoard_id() + ", Comment Count: " + comment_cnt);
                        } else {
                            jb.setCommentCount(0);
                        }

                    }
                    boardList.add(jb);
                }
                request.setAttribute("boardList", boardList);
            }

        } catch (SQLException e) {
            throw new RuntimeException("db오류: " + e.getMessage(), e);
        }
        return "/board/list";
    }
}