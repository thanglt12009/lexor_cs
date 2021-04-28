package com.lexor.cs.service;

import com.lexor.cs.util.DbConnectionHelper;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseServiceAPI<T> implements ServiceAPI {

    DbConnectionHelper dbHelper;
    protected Connection connection;

    protected BaseServiceAPI() {
        try {
            this.dbHelper = new DbConnectionHelper();
            this.connection = this.dbHelper.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(BaseServiceAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
