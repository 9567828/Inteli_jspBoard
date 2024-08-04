package webprocess;

import dao.HikariConnector;
import dto.JspBoard;
import webInter.WebProcess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardWriteProcess implements WebProcess {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        HikariConnector db = (HikariConnector) request.getServletContext().getAttribute("hikari");

        HttpSession session = request.getSession();

        JspBoard jb = new JspBoard(request);

        String sql = "INSERT INTO board (board_id, board_title, board_password, board_writer, board_content, board_write_date, board_writer_ip_addr) " +
                "VALUES(board_seq.nextval, ?, ?, ?, ?, SYSDATE, ?)";

        try (
                Connection conn = db.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, jb.getBoard_title());
            ps.setString(2, jb.getBoard_password());
            ps.setString(3, jb.getBoard_writer());
            ps.setString(4, jb.getBoard_content());
            ps.setString(5, jb.getBoard_writer_ip_addr());

            int result = ps.executeUpdate();

            if (result > 0) {
                return "redirect:/board/";
            } else {
                request.setAttribute("failed_write", "게시글 업데이트에 오류가 발생했습니다.");
                return "redirect:/error/notfound";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
