package com.lexor.cs.service;

import com.lexor.cs.beanhandler.RMAPaymentHandler;
import com.lexor.cs.domain.RMAPayment;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class RMAPaymentService extends BaseService<RMAPayment> {

    @Override
    public RMAPayment get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        RMAPayment c = (RMAPayment) o;
        QueryRunner runner = new QueryRunner();
        String insertSQL
                = "INSERT INTO \"RMAPayment\" (\"RMAID\", \"PaymentType\", \"PaymentAmount\", \"PaymentStatus\" ) VALUES (?, ?, ?, ?)";

        return runner.update(connection, insertSQL, c.getRMAID(), c.getPaymentType(), c.getPaymentAmount(), c.getPaymentStatus());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        RMAPayment c = (RMAPayment) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE public.\"RMAPayment\" "
                + " SET \"RMAID\"=?, \"PaymentType\"=?, \"PaymentAmount\"=?, \"PaymentStatus\"=? \""
                + " WHERE \"PaymentID\"=?;";

        return runner.update(connection, updateSQL, c.getRMAID(), c.getPaymentType(), c.getPaymentAmount(), c.getPaymentStatus(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        RMAPayment c = (RMAPayment) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM public.\"RMAPayment\" WHERE ?;";
        try {
            return runner.execute(connection, deleteSQL, c.getPaymentID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMAPayment>> resultHandler = new RMAPaymentHandler(connection);

        List<RMAPayment> empList = queryRunner.query(connection, "SELECT * FROM \"RMAPayment\" WHERE \"PaymentID\" = ?", resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        Integer status = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMAPayment>> resultHandler = new RMAPaymentHandler(connection);

        List<RMAPayment> empList = queryRunner.query(connection, "SELECT * FROM \"RMAPayment\" WHERE CONCAT(\"PaymentAmount\", \" \", \"PaymentStatus\", \" \") LIKE ?", resultHandler, status);
        List<T> list = new ArrayList<>();
        for (RMAPayment case1 : empList) {
            list.add((T) case1);
        }
        return list;  
    }

    @Override
    public long count() throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();

        QueryRunner runner = new QueryRunner();
        String query = "SELECT COUNT(0) FROM \"RMAPayment\"";
        long count = runner.query(connection, query, scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMAPayment>> resultHandler = new RMAPaymentHandler(connection);

        List<RMAPayment> empList = queryRunner.query(connection, "SELECT * FROM \"RMAPayment\" WHERE \"PaymentID\" >= ? AND  \"PaymentID\" <= ?", resultHandler, from, to);
        for (RMAPayment case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
