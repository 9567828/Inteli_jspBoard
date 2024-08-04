package webprocess;

import dao.HikariConnector;
import dto.JspBoard;
import webInter.WebProcess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardWriteFormProcess implements WebProcess {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        return "/board/writeForm";
    }
}
