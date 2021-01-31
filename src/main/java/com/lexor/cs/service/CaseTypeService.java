package com.lexor.cs.service;

import com.lexor.cs.beanhandler.CaseTypeHandler;
import com.lexor.cs.domain.CaseType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class CaseTypeService extends BaseService<CaseType> {

    @Override
    public CaseType get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        CaseType c = (CaseType) o;
        QueryRunner runner = new QueryRunner();
        String insertSQL
                = "INSERT INTO public.CaseType (\"CaseID\", \"CaseTypeValue\" ) VALUES (?, ? )";

        return runner.update(connection, insertSQL.toLowerCase(), c.getCaseID(), c.getCaseTypeValue());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        CaseType c = (CaseType) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE public.\"CaseType\" "
                + " SET \"CaseID\"=?, \"CaseTypeValue\"=?"
                + " WHERE \"CaseTypeID\"=?;";

        return runner.update(connection, updateSQL, c.getCaseID(), c.getCaseTypeValue(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        CaseType c = (CaseType) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM public.CaseType WHERE ?;";
        try {
            return runner.execute(connection, deleteSQL.toLowerCase(), c.getCaseTypeID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseType>> resultHandler = new CaseTypeHandler(connection);

        List<CaseType> empList = queryRunner.query(connection, "SELECT * FROM \"CaseType\" WHERE \"CaseTypeID\" = ?", resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }
    
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        Integer status = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseType>> resultHandler = new CaseTypeHandler(connection);
        
        String query = "SELECT * FROM public.CaseType WHERE CaseID = ?";
        List<CaseType> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, status);
        List<T> list = new ArrayList<>();
        for (CaseType case1 : empList) {
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
        String query = "SELECT COUNT(0) FROM \"CaseType\"";
        long count = runner.query(connection, query, scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseType>> resultHandler = new CaseTypeHandler(connection);
            
        String query = "SELECT * FROM public.CaseType WHERE CaseTypeID >= ? AND CaseTypeID <= ?";
        List<CaseType> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, from, to);
        
        return (List<T>)empList; 
    }
}
