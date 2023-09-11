package org.example.jdbcPractice;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {

    User map(ResultSet rs) throws SQLException;
}
