package com.lexor.cs.service;

import com.lexor.cs.beanhandler.RMASO_DetailHandler;
import com.lexor.cs.domain.RMASO_Detail;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class RMASO_DetailService extends BaseService<RMASO_Detail> {

    @Override
    public RMASO_Detail get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        RMASO_Detail c = (RMASO_Detail) o;
        QueryRunner runner = new QueryRunner();
        String insertSQL
                = "INSERT INTO \"RMASO_Detail\" (\"SOID\", \"RMAID\", \"ProductID\",\"Quantity\", \"Price\", \"CreatedDate\", \"UpdatedDate\") VALUES (?, ?, ?, ?, ?, ?, ?)";

        return runner.update(connection, insertSQL.toLowerCase(), c.getSOID(), c.getRMAID(), c.getProductID(),  c.getQuantity(), c.getPrice(), c.getCreatedDate(), c.getUpdatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        RMASO_Detail c = (RMASO_Detail) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE \"RMASO_Detail\" "
                + " SET \"SOID\"=?, \"SODetail_ID\"=?, \"ProductID\"=?, \"Quantity\"=?, \"Price\"=?, \"CreatedDate\"=?, \"UpdatedDate\"=?, \""
                + " WHERE \"ProductID\"=?;";

        return runner.update(connection, updateSQL, c.getSOID(), c.getSODetail_ID(), c.getProductID(), c.getQuantity(), c.getPrice(), c.getCreatedDate(), c.getUpdatedDate(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        RMASO_Detail c = (RMASO_Detail) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM \"RMASO_Detail\" WHERE SODetail_ID = ?";
        try {
            return runner.execute(connection, deleteSQL.toLowerCase() , c.getSODetail_ID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMASO_Detail>> resultHandler = new RMASO_DetailHandler(connection);

        String query = "SELECT * FROM \"RMASO_Detail\" WHERE \"RMAID\" = ?";
        List<RMASO_Detail> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        Integer status = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMASO_Detail>> resultHandler = new RMASO_DetailHandler(connection);

        List<RMASO_Detail> empList = queryRunner.query(connection, "SELECT * FROM \"RMASO_Detail\" WHERE CONCAT_WS(\" \", \"RMAID\", \"SOID\", \"SODetail_ID\", \"ProductID\") LIKE '%?%'", resultHandler, status);

        List<T> list = new ArrayList<>();
        for (RMASO_Detail case1 : empList) {
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
        String query = "SELECT COUNT(0) FROM \"RMASO_Detail\"";
        long count = runner.query(connection, query, scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMASO_Detail>> resultHandler = new RMASO_DetailHandler(connection);
        String query = "SELECT * FROM \"RMASO_Detail\" WHERE \"RMAID\" >= ? AND  \"RMAID\" <= ?";
        
        List<RMASO_Detail> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, from, to);
        for (RMASO_Detail case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
