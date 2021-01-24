package com.lexor.cs.service;

import com.lexor.cs.beanhandler.CaseInformationHandler;
import com.lexor.cs.domain.CaseInformation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class CaseInformationService extends BaseService<CaseInformation> {

    @Override
    public CaseInformation get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        CaseInformation c = (CaseInformation) o;
        QueryRunner runner = new QueryRunner();
        String insertSQL
                = "INSERT INTO public.CaseInformation ( \"CaseID\", \"DocCode\", \"Status\", \"Address\", \"CreatedDate\" ) VALUES (?, ?, ?, ?, ?::timestamp)";

        return runner.update(connection, insertSQL.toLowerCase(), c.getCaseID(), c.getDocCode(), Integer.parseInt(c.getStatus()), c.getAddress(), c.getCreatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        CaseInformation c = (CaseInformation) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE public.\"CaseInformation\" "
                + " SET \"CaseID\"=?, \"DocCode\"=?, \"Status\"=?, \"Address\"=?, \"CreatedDate\"=? "
                + " WHERE \"TransactionID\"=?;";

        return runner.update(connection, updateSQL, c.getCaseID(), c.getDocCode(), c.getStatus(), c.getAddress(), c.getCreatedDate(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        CaseInformation c = (CaseInformation) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM public.\"CaseInformation\" WHERE ?;";
        try {
            return runner.execute(connection, deleteSQL, c.getTransactionID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseInformation>> resultHandler = new CaseInformationHandler(connection);

        List<CaseInformation> empList = queryRunner.query(connection, "SELECT * FROM \"CaseInformation\" WHERE \"TransactionID\" = ?", resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }

    @Override
    public long count() throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();

        QueryRunner runner = new QueryRunner();
        String query = "SELECT COUNT(0) FROM \"CaseInformation\"";
        long count = runner.query(connection, query, scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseInformation>> resultHandler = new CaseInformationHandler(connection);
        
        String query = "SELECT * FROM public.CaseInformation WHERE \"CaseID\" >= ? AND  \"CaseID\" <= ?";
        List<CaseInformation> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, from, to);
        for (CaseInformation case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
