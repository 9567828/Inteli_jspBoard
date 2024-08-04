package dto;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Comments {
    int comment_id;
    String comment_writer;
    String comment_password;
    String  comment_content;
    Date comment_date;
    int comment_view_count;
    int comment_like_count;
    int comment_bad_count;

    public Comments(int comment_id, String comment_writer, String comment_password, String comment_content, Date comment_date,
                    int comment_view_count, int comment_like_count, int comment_bad_count) {
        this.comment_id = comment_id;
        this.comment_writer = comment_writer;
        this.comment_password = comment_password;
        this.comment_content = comment_content;
        this.comment_date = comment_date;
        this.comment_view_count = comment_view_count;
        this.comment_like_count = comment_like_count;
        this.comment_bad_count = comment_bad_count;
    }

    public Comments(ResultSet rs) throws SQLException {
        this(
            rs.getInt("comment_id"),
            rs.getString("comment_writer"),
            rs.getString("comment_password"),
            rs.getString("comment_content"),
            rs.getDate("comment_date"),
            rs.getInt("comment_view_count"),
            rs.getInt("comment_like_count"),
            rs.getInt("comment_bad_count")
        );
    }

    public Comments(HttpServletRequest request) {
        this.comment_id = Integer.parseInt(request.getParameter("comment_id"));
        this.comment_writer = request.getParameter("comment_writer");
        this.comment_password = request.getParameter("comment_password");
        this.comment_content = request.getParameter("comment_content");
        this.comment_date = new Date();
        this.comment_view_count = Integer.parseInt(request.getParameter("comment_view_count"));
        this.comment_like_count = Integer.parseInt(request.getParameter("comment_like_count"));
        this.comment_bad_count = Integer.parseInt(request.getParameter("comment_bad_count"));
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_writer() {
        return comment_writer;
    }

    public void setComment_writer(String comment_writer) {
        this.comment_writer = comment_writer;
    }

    public String getComment_password() {
        return comment_password;
    }

    public void setComment_password(String comment_password) {
        this.comment_password = comment_password;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

    public int getComment_view_count() {
        return comment_view_count;
    }

    public void setComment_view_count(int comment_view_count) {
        this.comment_view_count = comment_view_count;
    }

    public int getComment_like_count() {
        return comment_like_count;
    }

    public void setComment_like_count(int comment_like_count) {
        this.comment_like_count = comment_like_count;
    }

    public int getComment_bad_count() {
        return comment_bad_count;
    }

    public void setComment_bad_count(int comment_bad_count) {
        this.comment_bad_count = comment_bad_count;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "comment_id=" + comment_id +
                ", comment_writer='" + comment_writer + '\'' +
                ", comment_password='" + comment_password + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", comment_date=" + comment_date +
                ", comment_view_count=" + comment_view_count +
                ", comment_like_count=" + comment_like_count +
                ", comment_bad_count=" + comment_bad_count +
                '}';
    }
}
