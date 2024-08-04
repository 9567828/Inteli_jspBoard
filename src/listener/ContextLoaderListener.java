package listener;

import dao.HikariConnector;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        sc.setAttribute("hikari", new HikariConnector(
                sc.getInitParameter("driverName"),
                sc.getInitParameter("jdbcUrl"),
                sc.getInitParameter("userName"),
                sc.getInitParameter("password")
        ));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        ((HikariConnector) sc.getAttribute("hikari")).close();
    }
}
