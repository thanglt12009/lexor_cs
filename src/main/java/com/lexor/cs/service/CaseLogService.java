package com.lexor.cs.service;

import com.lexor.cs.beanhandler.CaseLogHandler;
import com.lexor.cs.domain.CaseLog;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class CaseLogService extends BaseService<CaseLog> {

    @Override
    public CaseLog get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        CaseLog c = (CaseLog) o;
        QueryRunner runner = new QueryRunner();
        String insertSQL
                = "INSERT INTO \"CaseLog\" (\"CaseID\", \"LogMessage\", \"CreatedDate\") VALUES (?, ?, ?)";

        return runner.update(connection, insertSQL, c.getCaseID(), c.getLogMessage(), c.getCreatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        CaseLog c = (CaseLog) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE public.\"CaseLog\" "
                + " SET \"CaseID\"=?, \"LogMessage\"=?, \"CreatedDate\"=? "
                + " WHERE \"LogID\"=?;";

        return runner.update(connection, updateSQL, c.getCaseID(), c.getLogMessage(), c.getCreatedDate(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        CaseLog c = (CaseLog) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM public.\"CaseLog\" WHERE ?;";
        try {
            return runner.execute(connection, deleteSQL, c.getLogID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseLog>> resultHandler = new CaseLogHandler(connection);

        List<CaseLog> empList = queryRunner.query(connection, "SELECT * FROM \"CaseLog\" WHERE \"LogID\" = ?", resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }

    @Override
    public long count() throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();

        QueryRunner runner = new QueryRunner();
        String query = "SELECT COUNT(0) FROM \"CaseLog\"";
        long count = runner.query(connection, query, scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseLog>> resultHandler = new CaseLogHandler(connection);

        List<CaseLog> empList = queryRunner.query(connection, "SELECT * FROM \"CaseLog\" WHERE \"LogID\" >= ? AND  \"LogID\" <= ?", resultHandler, from, to);
        for (CaseLog case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
