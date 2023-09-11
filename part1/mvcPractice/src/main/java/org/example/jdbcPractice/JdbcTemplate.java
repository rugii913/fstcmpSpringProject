package org.example.jdbcPractice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

    public void executeUpdate(String sql, PreparedStatementParameterSetter pss) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setParameter(pstmt); // 메서드를 호출한 쪽에서 pss의 콜백 함수를 만들어서 보내줌(람다식으로)

            pstmt.executeUpdate();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } finally {
                if (con != null) {
                    con.close();
                }
            }
        }
    }

    public <T> T executeQuery(String sql, PreparedStatementParameterSetter pss, RowMapper<T> rowMapper) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setParameter(pstmt); // 메서드를 호출한 쪽에서 pss의 콜백 함수(setParameter)를 만들어서 보내줌(람다식으로)

            rs = pstmt.executeQuery();

            T result = null;
            if (rs.next()) {
                result = rowMapper.map(rs);  // 메서드를 호출한 쪽에서 rowMapper의 콜백 함수(map)를 만들어서 보내줌(람다식으로)
            }

            return result;
        } finally {
            try {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } finally {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                }
            } finally {
                if (con != null) {
                    con.close();
                }
            }
        }
    }
}
