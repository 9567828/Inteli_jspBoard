package servlet;

import dao.HikariConnector;
import webInter.WebProcess;
import webprocess.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class DispatcherServlet extends HttpServlet {
    final private static HashMap<String, WebProcess> URI_MAPPING = new HashMap<>();

    final private static String PREFIX = "/WEB-INF/views";
    final private static String SUFFIX = ".jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        String driverName = context.getInitParameter("driverName");
        String jdbcUrl = context.getInitParameter("jdbcUrl");
        String userName = context.getInitParameter("userName");
        String password = context.getInitParameter("password");

        System.out.println("driverName: " + driverName);
        System.out.println("jdbcUrl: " + jdbcUrl);
        System.out.println("userName: " + userName);
        System.out.println("password: " + password);

        context.setAttribute("hikari", new HikariConnector(driverName, jdbcUrl, userName, password));

        URI_MAPPING.put("GET:/error/", new NotFoundProcess());
        URI_MAPPING.put("GET:/board/", new BoardIndexProcess());
        URI_MAPPING.put("GET:/board/write", new BoardWriteFormProcess());
        URI_MAPPING.put("POST:/board/write", new BoardWriteProcess());
        URI_MAPPING.put("GET:/board/comment", new CommentsFormProcess());
        URI_MAPPING.put("POST:/board/comment", new CommentsProcess());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String contextPath = req.getContextPath();
        String method = req.getMethod();
        String entireURI = req.getRequestURI();
        String uri = entireURI.substring(contextPath.length());

        System.out.println("주소: " + method + ":" + uri);

        WebProcess wp = URI_MAPPING.get(method + ":" + uri);
        String nextView = null;
        if (wp != null) {
            nextView = wp.process(req, resp);
        } else {
            resp.sendRedirect(contextPath + "/error/");
            return;
        }

        if (nextView.startsWith("redirect:")) {
            resp.sendRedirect(nextView.substring("redirect:".length()));
        } else {
            req.getRequestDispatcher(PREFIX + nextView + SUFFIX).forward(req, resp);
        }
    }
}
