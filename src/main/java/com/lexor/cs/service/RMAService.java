package com.lexor.cs.service;

import com.lexor.cs.beanhandler.RMAHandler;
import com.lexor.cs.domain.RMA;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class RMAService extends BaseService<RMA> {

    @Override
    public RMA get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        RMA c = (RMA) o;
        QueryRunner runner = new QueryRunner();
        String insertSQL
                = "INSERT INTO \"RMA\" (\"CaseID\", \"CustomerSOID\", \"Status\", \"CreatedDate\", \"UpdatedDate\") VALUES (?, ?, ?, ?, ?)";

        return runner.update(connection, insertSQL, c.getCaseID(), c.getCustomerSOID(), c.getStatus(), c.getCreatedDate(), c.getUpdatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        RMA c = (RMA) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE public.\"RMA\" "
                + " SET \"CaseID\"=?, \"CustomerSOID\"=?, \"Status\"=?, \"CreatedDate\"=?, \"UpdatedDate\"=?, \""
                + " WHERE \"RMAID\"=?;";

        return runner.update(connection, updateSQL, c.getCaseID(), c.getCustomerSOID(), c.getStatus(), c.getCreatedDate(), c.getUpdatedDate(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        RMA c = (RMA) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM public.\"RMA\" WHERE ?;";
        try {
            return runner.execute(connection, deleteSQL, c.getRMAID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMA>> resultHandler = new RMAHandler(connection);

        List<RMA> empList = queryRunner.query(connection, "SELECT * FROM \"RMA\" WHERE \"RMAID\" = ?", resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        Integer status = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMA>> resultHandler = new RMAHandler(connection);

        List<RMA> empList = queryRunner.query(connection, "SELECT * FROM \"RMA\" WHERE CONCAT_WS(\" \", \"Status\", \"RMAID\", \"CustomerID\", \"CaseID\") LIKE '%?%'", resultHandler, status);
        List<T> list = new ArrayList<>();
        for (RMA case1 : empList) {
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
        String query = "SELECT COUNT(0) FROM \"RMA\"";
        long count = runner.query(connection, query, scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMA>> resultHandler = new RMAHandler(connection);

        List<RMA> empList = queryRunner.query(connection, "SELECT * FROM \"RMA\" WHERE \"RMAID\" >= ? AND  \"RMAID\" <= ?", resultHandler, from, to);
        for (RMA case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
