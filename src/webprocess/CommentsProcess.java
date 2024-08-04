package webprocess;

import dao.HikariConnector;
import dto.Comments;
import webInter.WebProcess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentsProcess implements WebProcess {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        HikariConnector db = (HikariConnector) request.getServletContext().getAttribute("hikari");

        String boardIdStr = request.getParameter("board_id");
        int boardId;

        // boardId가 null이거나 빈 문자열이 아닌지 확인
        if (boardIdStr == null || boardIdStr.isEmpty()) {
            System.out.println("빈문자열?" + boardIdStr);
            request.setAttribute("error", "잘못된 접근입니다. 게시판 ID가 없습니다.");
            return "/error/notfound";
        }

        try {
            boardId = Integer.parseInt(boardIdStr);
            System.out.println("댓글작성용 boardId = " + boardId);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "잘못된 게시판 ID 형식입니다.");
            return "/error/notfound";
        }


        Comments comment = new Comments(request);

        String sql = "INSERT INTO comments2 (comment_id, board_id, comment_writer, comment_password, comment_content, comment_date) " +
                "VALUES (comment_seq.nextval, ?, ?, ?, ?, sysdate)";

        try (
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ){
            pstmt.setInt(1, boardId);
            pstmt.setString(2, comment.getComment_writer());
            pstmt.setString(3, comment.getComment_password());
            pstmt.setString(4, comment.getComment_content());

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("작성완료");
                return "redirect:/board/comment?board_id=" + boardId;
            } else {
                System.out.println("작성실패");
                request.setAttribute("failedComment", "댓글 작성이 실패 되었습니다.");
                return "/error/notfound";
            }

        } catch (SQLException e) {
            request.setAttribute("error", "데이터베이스 오류 입니다.");
            System.out.println("오류발생: " + e.getMessage());
            e.printStackTrace();
            return "/error/notfound";
        }
    }
}
