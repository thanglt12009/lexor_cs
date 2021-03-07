package com.lexor.cs.service;

import com.lexor.cs.beanhandler.RMALoglHandler;
import com.lexor.cs.beanhandler.ServiceActivityLogHandler;
import com.lexor.cs.domain.RMALog;
import com.lexor.cs.domain.ServiceLog;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class ServiceLogService extends BaseService<ServiceLog> {

    @Override
    public ServiceLog get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        ServiceLog c = (ServiceLog) o;
        QueryRunner runner = new QueryRunner();
        String query
                = "INSERT INTO \"public\".\"ServiceLog\" (\"ServiceID\", \"LogMessage\",\"CreatedDate\" ) VALUES (?, ?, ?::timestamp);";

        return runner.insert(connection, query.toLowerCase(), new ScalarHandler<Integer>(), c.getServiceID(), c.getLogMessage(), c.getCreatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        ServiceLog c = (ServiceLog) o;
        QueryRunner runner = new QueryRunner();
        String query
                = "UPDATE \"public\".\"ServiceLog\" "
                + " SET \"ServiceID\"=?, \"LogMessage\"=?, \"CreatedDate\"=? \""
                + " WHERE \"ServiceID\"=?;";

        return runner.update(connection, query.toLowerCase(), c.getServiceID(), c.getLogMessage(), c.getCreatedDate(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        ServiceLog c = (ServiceLog) o;
        QueryRunner runner = new QueryRunner();
        String query = "DELETE FROM \"public\".\"ServiceLog\" WHERE ?;";
        
        try {
            return runner.execute(connection, query.toLowerCase(), c.getServiceID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<ServiceLog>> resultHandler = new ServiceActivityLogHandler(connection);
        String query = "SELECT * FROM \"public\".\"ServiceLog\" WHERE \"ServiceID\" = ?;";

        List<ServiceLog> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        String message = (String) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMALog>> resultHandler = new RMALoglHandler(connection);
        String query = "SELECT * FROM \"public\".\"ServiceLog\" WHERE CONCAT(\"LogMessage\", \" \") LIKE ?;";

        List<RMALog> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, message);
        List<T> list = new ArrayList<>();
        for (RMALog case1 : empList) {
            list.add((T) case1);
        }
        return list; 
    }
    
    @Override
    public <T> List<T> findByJoinedKeyWord  (Object o) throws SQLException {        
        return new ArrayList<>();
    }

    @Override
    public long count() throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();
        QueryRunner runner = new QueryRunner();
        String query = "SELECT COUNT(0) FROM \"public\".\"ServiceLog\";";
        
        long count = runner.query(connection, query.toLowerCase(), scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<ServiceLog>> resultHandler = new ServiceActivityLogHandler(connection);        
        String query = "SELECT * FROM \"public\".\"ServiceLog\" WHERE \"ServiceID\" >= ? AND  \"ServiceID\" <= ?;";
        
        List<ServiceLog> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, from, to);
        for (ServiceLog case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
