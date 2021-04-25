package com.lexor.cs.resource;

import com.lexor.cs.service.BaseUserServiceInterface;
import java.util.List;
import com.lexor.cs.service.Service;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;


public abstract class UserAbstractFacade<T> {

    private Class<T> entityClass;

    public UserAbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract BaseUserServiceInterface getService();

    public long count(String username, String password) throws SQLException {
        return getService().count(username, password);
    }
}
