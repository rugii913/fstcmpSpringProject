package org.example.jdbcPractice;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public static DataSource getDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource(); // CP로 Hikari 사용할 것이므로 HikariDataSource 사용
        hikariDataSource.setDriverClassName("org.h2.Driver");
        hikariDataSource.setJdbcUrl("jdbc:h2:mem:localhost/~/mvcPractice;MODE=MySQL;DB_CLOSE_DELAY=-1");
        // JdbcUrl 수정 https://www.h2database.com/html/features.html#embedded_databases - Database URL Overview 참고
        // cf. 이 형태는 connection은 embedded로, 저장은 in-memory 형태로 연결하는 것
        // https://kukim.tistory.com/105도 참고 // 구글 검색 키워드 h2 db embedded url
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("");

        return hikariDataSource;
    }
}
