package com.lexor.cs.resource;

import java.util.List;
import com.lexor.cs.service.Service;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;


public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract Service getService();

    public int create(T entity) throws SQLException {
        return (int) getService().persist(entity);
    }

    public int edit(Integer id, T entity) throws SQLException {
        return getService().update(id, entity);
    }

    public int remove(T entity) throws SQLException {
        return getService().remove(entity);
    }

    public T find(Integer id) throws SQLException {
        return getService().find(entityClass, id);
    }
    
    public List<T> findByKeyWord(Object entity) throws SQLException {
        return getService().findByKeyWord(entity);
    }
    
    public List<T> findByJoinedKeyWord(Object entity) throws SQLException {
        return getService().findByJoinedKeyWord(entity);
    }
    
    public List<T> findRange(int[] range) throws SQLException {
        return getService().findRange(entityClass, range);
    }

    public long count() throws SQLException {
        return getService().count();
    }
}
