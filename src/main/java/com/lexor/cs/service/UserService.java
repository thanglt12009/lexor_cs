package com.lexor.cs.service;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class UserService extends BaseUserService<BaseUserService> {

    @Override
    public long count(String username, String password) throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();

        QueryRunner runner = new QueryRunner();
        String query = "SELECT COUNT(0) FROM \"public\".\"User\" WHERE username = ? and password = ?;";
        
        long count = runner.query(connection, query.toLowerCase(), scalarHandler, username, password);
        return count;
    }
}
