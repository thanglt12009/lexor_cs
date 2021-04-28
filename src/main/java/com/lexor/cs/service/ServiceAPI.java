package com.lexor.cs.service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceAPI {
    public int persist(Object o) throws SQLException;

    public int update(Integer id, Object o) throws SQLException;

    public int remove(Object o) throws SQLException;
    
    public <T extends Object> T get(Integer id, String token) throws SQLException;
    
    public <T extends Object> T find(Class<T> type, Object o, String token) throws SQLException;
    
    public <T> List<T> findByKeyWord(Object o, String token) throws SQLException;
    
    public <T> List<T> findByJoinedKeyWord(Object o) throws SQLException;
        
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException;
    
    public long count(Integer id) throws SQLException;

}
