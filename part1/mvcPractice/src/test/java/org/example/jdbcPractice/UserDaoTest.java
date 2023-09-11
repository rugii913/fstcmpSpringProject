package org.example.jdbcPractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    @BeforeEach // 픽스처 - populator에 스크립트 추가, datasource 받아서 populator 실행
    void setUp() { // 픽스처 만들기 위해 spring의 jdbc datasource 패키지에 있는 것들 사용함(Populator 관련)
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql")); // 클래스 패스에서 스키마 파일 읽어서 스크립트로 추가
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
        // DatabasePopulatorUtils 사용하여 populator 실행 - 이 때 데이터 소스 필요 - ConnectionManager라는 클래스 만들어서 받아올 것
    }

    @Test
    void createTest() throws SQLException {
        UserDao userDao = new UserDao();

        userDao.create(new User("wizard", "password", "name", "email"));

        User user = userDao.findByUserId("wizard");
        assertThat(user).isEqualTo(new User("wizard", "password", "name", "email"));
    }
}
