package com.lexor.cs.service;

import com.lexor.cs.beanhandler.CaseTypeHandler;
import com.lexor.cs.domain.CaseService;
import com.lexor.cs.domain.CaseType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
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
        String query
                = "INSERT INTO \"public\".\"CaseType\" (\"CaseID\", \"CaseTypeValue\" ) VALUES (?, ? );";

        return runner.update(connection, query.toLowerCase(), c.getCaseID(), c.getCaseTypeValue());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        CaseType c = (CaseType) o;
        QueryRunner runner = new QueryRunner();
        String query
                = "UPDATE \"public\".\"CaseType\" "
                + " SET \"CaseID\"=?, \"CaseTypeValue\"=?"
                + " WHERE \"CaseTypeID\"=?;";

        return runner.update(connection, query.toLowerCase(), c.getCaseID(), c.getCaseTypeValue(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        CaseType c = (CaseType) o;
        QueryRunner runner = new QueryRunner();
        String query = "DELETE FROM public.caseType WHERE caseTypeId = ?";
        
        try {
            return runner.execute(connection, query.toLowerCase(), c.getCaseTypeID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseType>> resultHandler = new CaseTypeHandler(connection);
        String query = "SELECT * FROM \"public\".\"CaseType\" WHERE \"CaseTypeID\" = ?;";

        /*
        List<CaseType> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        */
        
        List<Map<String, Object>> empLists = queryRunner.query(connection, query.toLowerCase(), new MapListHandler(), id);
        List<T> list = new ArrayList<>();
        
        for(int i=0; i< empLists.size();i++) {
            Map<String, Object> mapObj = (Map<String, Object>) empLists.get(i);
            CaseType caseObj = new CaseType();
            caseObj.setCaseID((Integer)mapObj.get("caseID"));
            caseObj.setCaseTypeID((Integer)mapObj.get("caseTypeID"));
            caseObj.setCaseTypeValue((String)mapObj.get("caseTypeValue"));
            list.add((T) caseObj);
        }
        
        if (list.size() > 0) {
            return (T) list.get(0);
        }
        
        throw new SQLException("Record not found");
    }
    
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        Integer status = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseType>> resultHandler = new CaseTypeHandler(connection);        
        String query = "SELECT * FROM \"public\".\"CaseType\" WHERE CaseID = ?;";
        
        /*
        List<CaseType> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, status);
        List<T> list = new ArrayList<>();
        for (CaseType case1 : empList) {
            list.add((T) case1);
        }
        return list; 
        */
        List<Map<String, Object>> empLists = queryRunner.query(connection, query.toLowerCase(), new MapListHandler(), status);
        List<T> list = new ArrayList<>();
        
        for(int i=0; i< empLists.size();i++) {
            Map<String, Object> mapObj = (Map<String, Object>) empLists.get(i);
            CaseType caseObj = new CaseType();
            caseObj.setCaseID((Integer)mapObj.get("caseID"));
            caseObj.setCaseTypeID((Integer)mapObj.get("caseTypeID"));
            caseObj.setCaseTypeValue((String)mapObj.get("caseTypeValue"));
            list.add((T) caseObj);
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
        String query = "SELECT COUNT(0) FROM \"public\".\"CaseType\";";
        
        long count = runner.query(connection, query.toLowerCase(), scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseType>> resultHandler = new CaseTypeHandler(connection);            
        String query = "SELECT * FROM \"public\".\"CaseType\" WHERE \"CaseTypeID\" >= ? AND \"CaseTypeID\" <= ?;";
        
        List<CaseType> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, from, to);
        
        return (List<T>)empList; 
    }
}
