package org.example.jdbcPractice;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementParameterSetter {

    void setParameter(PreparedStatement pstmt) throws SQLException;
}
