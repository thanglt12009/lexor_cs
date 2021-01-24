package com.lexor.cs.service;

import com.lexor.cs.beanhandler.CaseServiceDetailHandler;
import com.lexor.cs.beanhandler.CaseServiceHandler;
import com.lexor.cs.domain.CaseService;
import com.lexor.cs.domain.CaseServiceDetail;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class CaseServiceDetails extends BaseService<CaseService> {

    @Override
    public CaseService get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        CaseServiceDetail c = (CaseServiceDetail) o;
        QueryRunner runner = new QueryRunner();
        String insertSQL
                = "INSERT INTO public.CaseServiceSO (\"CaseServiceID\", \"CustomerSOID\", \"CreatedDate\", \"UpdatedDate\") VALUES (?, ?, ?, ?)";

        return runner.insert(connection, insertSQL.toLowerCase(), new ScalarHandler<Integer>(), c.getCaseServiceID(), c.getCustomerSOID(), c.getCreatedDate(), c.getUpdatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        CaseServiceDetail c = (CaseServiceDetail) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE public.\"CaseServiceSO\" "
                + " SET \"CaseServiceID\"=?, \"CustomerSOID\"=?, \"CreatedDate\"=?, \"UpdatedDate\"=? "
                + " WHERE \"CaseServiceDetailID\"=?;";

        return runner.update(connection, updateSQL, c.getCaseServiceID(), c.getCustomerSOID(), c.getCreatedDate(), c.getUpdatedDate(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        CaseServiceDetail c = (CaseServiceDetail) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM public.\"CaseServiceSO\" WHERE ?;";
        try {
            return runner.execute(connection, deleteSQL, c.getCaseServiceID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseServiceDetail>> resultHandler = new CaseServiceDetailHandler(connection);

        List<CaseServiceDetail> empList = queryRunner.query(connection, "SELECT * FROM \"CaseService\" WHERE \"CaseServiceID\" = ?", resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }

    @Override
    public long count() throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();

        QueryRunner runner = new QueryRunner();
        String query = "SELECT COUNT(0) FROM public.CaseServiceSO";
        long count = runner.query(connection, query, scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseService>> resultHandler = new CaseServiceHandler(connection);

        List<CaseService> empList = queryRunner.query(connection, "SELECT * FROM \"CaseService\" WHERE \"CaseServiceID\" >= ? AND  \"CaseServiceID\" <= ?", resultHandler, from, to);
        for (CaseService case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
