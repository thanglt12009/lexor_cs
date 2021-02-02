package com.lexor.cs.service;

import com.lexor.cs.beanhandler.RMAMessageHandler;
import com.lexor.cs.domain.RMAMessage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class RMAMessageService extends BaseService<RMAMessage> {

    @Override
    public RMAMessage get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        RMAMessage c = (RMAMessage) o;
        QueryRunner runner = new QueryRunner();
        String insertSQL
                = "INSERT INTO \"RMAMessage\" (\"RMAID\", \"SendTo\", \"Subject\", \"MessageBody\", \"CreatedDate\") VALUES (?, ?, ?, ?, ?)";

        return runner.update(connection, insertSQL, c.getRMAID(), c.getSendTo(), c.getSubject(), c.getMessageBody(), c.getCreatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        RMAMessage c = (RMAMessage) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE \"RMAMessage\" "
                + " SET \"RMAID\"=?, \"SendTo\"=?, \"Subject\"=?, \"MessageBody\"=?, \"CreatedDate\"=?, \""
                + " WHERE \"MessageID\"=?;";

        return runner.update(connection, updateSQL, c.getRMAID(), c.getSendTo(), c.getSubject(), c.getMessageBody(), c.getCreatedDate(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        RMAMessage c = (RMAMessage) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM \"RMAMessage\" WHERE ?;";
        try {
            return runner.execute(connection, deleteSQL, c.getMessageID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMAMessage>> resultHandler = new RMAMessageHandler(connection);

        List<RMAMessage> empList = queryRunner.query(connection, "SELECT * FROM \"RMAMessage\" WHERE \"MessageID\" = ?", resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        String status = (String) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMAMessage>> resultHandler = new RMAMessageHandler(connection);

        List<RMAMessage> empList = queryRunner.query(connection, "SELECT * FROM \"RMAMessage\" WHERE CONCAT(\"Subject\", \" \", \"SendTo\", \" \") LIKE ?", resultHandler, status);
        List<T> list = new ArrayList<>();
        for (RMAMessage case1 : empList) {
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
        String query = "SELECT COUNT(0) FROM \"RMAMessage\"";
        long count = runner.query(connection, query, scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMAMessage>> resultHandler = new RMAMessageHandler(connection);

        List<RMAMessage> empList = queryRunner.query(connection, "SELECT * FROM \"RMAMessage\" WHERE \"MessageID\" >= ? AND  \"MessageID\" <= ?", resultHandler, from, to);
        for (RMAMessage case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
