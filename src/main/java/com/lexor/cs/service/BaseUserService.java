package com.lexor.cs.service;

import com.lexor.cs.util.DbConnectionHelper;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseUserService<T> implements BaseUserServiceInterface {

    DbConnectionHelper dbHelper;
    protected Connection connection;

    protected BaseUserService() {
        try {
            this.dbHelper = new DbConnectionHelper();
            this.connection = this.dbHelper.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
