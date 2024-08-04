package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnector {
    private final HikariDataSource dataSource;

    public HikariConnector(String driverName, String jdbcUrl, String userName, String password) {
        HikariConfig config = new HikariConfig();

        System.out.println("driverName: " + driverName);
        System.out.println("jdbcUrl: " + jdbcUrl);
        System.out.println("userName: " + userName);
        System.out.println("password: " + password);

        config.setDriverClassName(driverName);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(userName);
        config.setPassword(password);

        dataSource = new HikariDataSource(config);
    }
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void close() {
        dataSource.close();
    }
}
