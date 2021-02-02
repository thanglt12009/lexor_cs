package com.lexor.cs.service;

import com.lexor.cs.beanhandler.RMASOHandler;
import com.lexor.cs.domain.RMASO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class RMASOService extends BaseService<RMASO> {

    @Override
    public RMASO get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        RMASO c = (RMASO) o;
        QueryRunner runner = new QueryRunner();
        String query
                = "INSERT INTO \"public\".\"RMASO\" (\"RMAID\", \"Fee\", \"Total\", \"CreatedDate\", \"UpdatedDate\") VALUES (?, ?, ?, ?, ?);";

        return runner.insert(connection, query.toLowerCase(), new ScalarHandler<Integer>(), c.getRMAID(), c.getFee(), c.getTotal(), c.getCreatedDate(), c.getUpdatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        RMASO c = (RMASO) o;
        QueryRunner runner = new QueryRunner();
        String query
                = "UPDATE \"public\".\"RMASO\" "
                + " SET \"RMAID\"=?, \"Fee\"=?, \"Total\"=?, \"CreatedDate\"=?, \"UpdatedDate\"=?, \""
                + " WHERE \"SOID\"=?;";

        return runner.update(connection, query.toLowerCase(), c.getRMAID(), c.getFee(), c.getTotal(), c.getCreatedDate(), c.getUpdatedDate(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        RMASO c = (RMASO) o;
        QueryRunner runner = new QueryRunner();
        String query = "DELETE FROM \"public\".\"RMASO\" WHERE ?;";
        try {
            return runner.execute(connection, query.toLowerCase(), c.getSOID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMASO>> resultHandler = new RMASOHandler(connection);
        String query = "SELECT * FROM \"public\".\"RMASO\" WHERE \"SOID\" = ?;";
        
        List<RMASO> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        Integer status = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMASO>> resultHandler = new RMASOHandler(connection);
        String query = "SELECT * FROM \"public\".\"RMASO\" WHERE CONCAT(\"Fee\", \" \") LIKE ?;";

        List<RMASO> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, status);
        List<T> list = new ArrayList<>();
        for (RMASO case1 : empList) {
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
        String query = "SELECT COUNT(0) FROM \"public\".\"RMASO\";";
        
        long count = runner.query(connection, query.toLowerCase(), scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMASO>> resultHandler = new RMASOHandler(connection);
        String query = "SELECT * FROM \"RMASO\" WHERE \"SOID\" >= ? AND  \"SOID\" <= ?;";

        List<RMASO> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, from, to);
        for (RMASO case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
