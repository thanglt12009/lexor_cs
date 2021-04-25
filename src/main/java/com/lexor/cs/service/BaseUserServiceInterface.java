package com.lexor.cs.service;

import java.sql.SQLException;

public interface BaseUserServiceInterface {
    public long count(String name, String password) throws SQLException;
}
