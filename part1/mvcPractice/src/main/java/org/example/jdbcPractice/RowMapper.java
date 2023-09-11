package org.example.jdbcPractice;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper {

    User map(ResultSet rs) throws SQLException;
}
