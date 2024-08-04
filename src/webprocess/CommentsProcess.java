package webprocess;

import webInter.WebProcess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentsProcess implements WebProcess {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        return "/board/comment";
    }
}
