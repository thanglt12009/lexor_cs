package com.lexor.cs.service;

import com.lexor.cs.beanhandler.CaseServiceDetailHandler;
import com.lexor.cs.beanhandler.CaseServiceHandler;
import com.lexor.cs.domain.CaseService;
import com.lexor.cs.domain.CaseServiceDetail;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
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
        String query
                = "INSERT INTO \"public\".\"CaseServiceSO\" (\"CaseServiceID\", \"CustomerSOID\", \"CreatedDate\", \"UpdatedDate\") VALUES (?, ?, ?, ?);";

        return runner.insert(connection, query.toLowerCase(), new ScalarHandler<Integer>(), c.getCaseServiceID(), c.getCustomerSOID(), c.getCreatedDate(), c.getUpdatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        CaseServiceDetail c = (CaseServiceDetail) o;
        QueryRunner runner = new QueryRunner();
        String query
                = "UPDATE \"public\".\"CaseServiceSO\" "
                + " SET \"CaseServiceID\"=?, \"CustomerSOID\"=?, \"CreatedDate\"=?, \"UpdatedDate\"=? "
                + " WHERE \"CaseServiceDetailID\"=?;";

        return runner.update(connection, query.toLowerCase(), c.getCaseServiceID(), c.getCustomerSOID(), c.getCreatedDate(), c.getUpdatedDate(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        CaseServiceDetail c = (CaseServiceDetail) o;
        QueryRunner runner = new QueryRunner();
        String query = "DELETE FROM \"public\".\"CaseServiceSO\" WHERE ?;";
        try {
            return runner.execute(connection, query.toLowerCase(), c.getCaseServiceID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseServiceDetail>> resultHandler = new CaseServiceDetailHandler(connection);
        String query = "SELECT * FROM \"public\".\"CaseService\" WHERE \"CaseServiceID\" = ?;";

        List<CaseServiceDetail> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        Integer status = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseService>> resultHandler = new CaseServiceHandler(connection);
        String query = "SELECT * FROM \"public\".\"CaseService\" WHERE CONCAT_WS(\" \", \"CaseServiceDetailID\", \"CaseServiceID\", \"CustomerSOID\") LIKE '%?%';";

        List<CaseService> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, status);
        List<T> list = new ArrayList<>();
        for (CaseService case1 : empList) {
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
        String query = "SELECT COUNT(0) FROM \"public\".\"CaseServiceSO\";";
        
        long count = runner.query(connection, query.toLowerCase(), scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        //ResultSetHandler<List<CaseService>> resultHandler = new CaseServiceHandler(connection);
        String query = "SELECT * FROM \"public\".\"CaseServiceSO\" WHERE \"CaseServiceID\" >= ? AND  \"CaseServiceID\" <= ?;";

        //List<CaseService> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, from, to);
        //for (CaseService case1 : empList) {
            //list.add((T) case1);
        //}
        //return list;
        
        List<Map<String, Object>> empLists = queryRunner.query(connection, query.toLowerCase(), new MapListHandler(), from, to);
        
        for(int i=0; i< empLists.size();i++) {
            Map<String, Object> mapObj = (Map<String, Object>) empLists.get(i);
            CaseServiceDetail caseObj = new CaseServiceDetail();
            caseObj.setCaseServiceID((Integer)mapObj.get("caseServiceID"));
            caseObj.setCustomerSOID((Integer)mapObj.get("customerSOID"));
            list.add((T) caseObj);
        }
        
        if (list.size() > 0) {
            return list;
        }
        
        throw new SQLException("Record not found");
    }
}
