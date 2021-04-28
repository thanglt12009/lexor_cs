package com.lexor.cs.resource;

import java.util.List;
import com.lexor.cs.service.Service;
import com.lexor.cs.service.ServiceAPI;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;


public abstract class AbstractAPIFacade<T> {

    private Class<T> entityClass;

    public AbstractAPIFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract ServiceAPI getService();

    public int create(T entity) throws SQLException {
        return (int) getService().persist(entity);
    }

    public int edit(Integer id, T entity) throws SQLException {
        return getService().update(id, entity);
    }

    public int remove(T entity) throws SQLException {
        return getService().remove(entity);
    }

    public T find(Integer id, String token) throws SQLException {
        return getService().find(entityClass, id, token);
    }
    
    public List<T> findByKeyWord(Object entity, String token) throws SQLException {
        return getService().findByKeyWord(entity, token);
    }
    
    public List<T> findByJoinedKeyWord(Object entity) throws SQLException {
        return getService().findByJoinedKeyWord(entity);
    }
    
    public List<T> findRange(int[] range) throws SQLException {
        return getService().findRange(entityClass, range);
    }

    public long count(Integer id) throws SQLException {
        return getService().count(id);
    }
}
