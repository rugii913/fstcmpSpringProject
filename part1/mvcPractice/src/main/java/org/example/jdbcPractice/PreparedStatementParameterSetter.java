package org.example.jdbcPractice;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementParameterSetter {

    void setParameter(PreparedStatement pstmt) throws SQLException;
}
