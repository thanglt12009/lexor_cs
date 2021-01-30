package com.lexor.cs.service;

import com.lexor.cs.beanhandler.CaseReturnHandler;
import com.lexor.cs.domain.CaseReturn;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class CaseReturns extends BaseService<CaseReturn> {

    @Override
    public CaseReturn get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        CaseReturn c = (CaseReturn) o;
        QueryRunner runner = new QueryRunner();
        String insertSQL
                = "INSERT INTO public.CaseReturn (\"CaseID\", \"CustomerSOID\", \"CreatedDate\", \"UpdatedDate\") VALUES (?, ?, ?, ?)";

        return runner.update(connection, insertSQL.toLowerCase(), c.getCaseID(), c.getCustomerSOID(), c.getCreatedDate(), c.getUpdatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        CaseReturn c = (CaseReturn) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE public.\"CaseReturn\" "
                + " SET \"CaseID\"=?, \"CustomerSOID\"=?, \"CreatedDate\"=?, \"UpdatedDate\"=? "
                + " WHERE \"CaseReturnID\"=?;";

        return runner.update(connection, updateSQL, c.getCaseID(), c.getCustomerSOID(), c.getCreatedDate(), c.getUpdatedDate(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        CaseReturn c = (CaseReturn) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM public.\"CaseReturn\" WHERE ?;";
        try {
            return runner.execute(connection, deleteSQL, c.getCaseReturnID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseReturn>> resultHandler = new CaseReturnHandler(connection);

        List<CaseReturn> empList = queryRunner.query(connection, "SELECT * FROM \"CaseReturn\" WHERE \"CaseReturnID\" = ?", resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        Integer status = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseReturn>> resultHandler = new CaseReturnHandler(connection);

        List<CaseReturn> empList = queryRunner.query(connection, "SELECT * FROM \"CaseReturn\" WHERE CONCAT(\"CaseReturnID\", \" \") LIKE ?", resultHandler, status);
        List<T> list = new ArrayList<>();
        for (CaseReturn case1 : empList) {
            list.add((T) case1);
        }
        return list; 
    }

    @Override
    public long count() throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();

        QueryRunner runner = new QueryRunner();
        String query = "SELECT COUNT(0) FROM public.CaseReturn";
        long count = runner.query(connection, query, scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseReturn>> resultHandler = new CaseReturnHandler(connection);

        List<CaseReturn> empList = queryRunner.query(connection, "SELECT * FROM \"CaseReturn\" WHERE \"CaseReturnID\" >= ? AND  \"CaseReturnID\" <= ?", resultHandler, from, to);
        for (CaseReturn case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
