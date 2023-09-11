package org.example.jdbcPractice;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:localhost/~/mvcPractice;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";
    private static final int MAX_POOL_SIZE = 10;

    private static final DataSource ds ;

    static { // DataSource는 하나여야 하므로 static 초기화 블럭으로 초기화(?)
        HikariDataSource hikariDataSource = new HikariDataSource(); // CP로 Hikari 사용할 것이므로 HikariDataSource 사용
        hikariDataSource.setDriverClassName(DB_DRIVER);
        hikariDataSource.setJdbcUrl(DB_URL);
        // JdbcUrl 수정 https://www.h2database.com/html/features.html#embedded_databases - Database URL Overview 참고
        // cf. 이 형태는 connection은 embedded로, 저장은 in-memory 형태로 연결하는 것
        // https://kukim.tistory.com/105도 참고 // 구글 검색 키워드 h2 db embedded url
        hikariDataSource.setUsername(DB_USERNAME);
        hikariDataSource.setPassword(DB_PASSWORD);
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE);

        ds = hikariDataSource;
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
