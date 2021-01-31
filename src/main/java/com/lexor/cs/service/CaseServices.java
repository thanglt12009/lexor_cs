package com.lexor.cs.service;

import com.lexor.cs.beanhandler.CaseServiceHandler;
import com.lexor.cs.domain.CaseService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class CaseServices extends BaseService<CaseService> {

    @Override
    public CaseService get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        CaseService c = (CaseService) o;
        QueryRunner runner = new QueryRunner();
        String insertSQL
                = "INSERT INTO public.CaseService (\"CaseID\", \"CustomerSOID\", \"LogMessage\", \"CreatedDate\", \"UpdatedDate\") VALUES (?, ?, ?, ?, ?)";

        return runner.insert(connection, insertSQL.toLowerCase(), new ScalarHandler<Integer>(), c.getCaseID(), c.getCustomerSOID(), c.getLogMessage(), c.getCreatedDate(), c.getUpdatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        CaseService c = (CaseService) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE public.\"CaseService\" "
                + " SET \"CaseID\"=?, \"CustomerSOID\"=?, \"LogMessage\"=?, \"CreatedDate\"=?, \"UpdatedDate\"=? "
                + " WHERE \"CaseServiceID\"=?;";

        return runner.update(connection, updateSQL, c.getCaseID(), c.getCustomerSOID(), c.getLogMessage(), c.getCreatedDate(), c.getUpdatedDate(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        CaseService c = (CaseService) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM public.\"CaseService\" WHERE ?;";
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
        ResultSetHandler<List<CaseService>> resultHandler = new CaseServiceHandler(connection);
        
        String query = "SELECT caseservice.*, servicemaster.* FROM public.CaseService inner join public.servicemaster on caseservice.caseserviceid = servicemaster.caseserviceid WHERE caseservice.caseserviceid = ?";
        /*List<CaseService> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }*/
        List<Map<String, Object>> empLists = queryRunner.query(connection, query.toLowerCase(), new MapListHandler(), id);
        List<T> list = new ArrayList<>();
        for(int i=0; i< empLists.size();i++) {
            Map<String, Object> mapObj = (Map<String, Object>) empLists.get(i);
            CaseService caseObj = new CaseService();
            caseObj.setCaseID((Integer)mapObj.get("caseID"));
            caseObj.setCaseServiceID((Integer)mapObj.get("caseServiceID"));
            caseObj.setCustomerSOID((Integer)mapObj.get("customerSOID"));
            caseObj.setLogMessage((String)mapObj.get("logMessage")); 
            caseObj.setStatus((Integer)mapObj.get("status"));
            caseObj.setServiceMasterID((Integer)mapObj.get("serviceMasterID"));
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
        ResultSetHandler<List<CaseService>> resultHandler = new CaseServiceHandler(connection);

        String query = "SELECT * FROM \"CaseService\" WHERE CONCAT_WS(' ', \"CustomerSOID\", \"CaseServiceID\", \"CaseID\") LIKE '%1%' order by CaseServiceID desc";
        
        //List<CaseService> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler);
        List<Map<String, Object>> empLists = queryRunner.query(connection, query.toLowerCase(), new MapListHandler());
        List<T> list = new ArrayList<>();
        for(int i=0; i< empLists.size();i++) {
            Map<String, Object> mapObj = (Map<String, Object>) empLists.get(i);
            CaseService caseObj = new CaseService();
            caseObj.setCaseID((Integer)mapObj.get("caseID"));
            caseObj.setCaseServiceID((Integer)mapObj.get("caseServiceID"));
            caseObj.setCustomerSOID((Integer)mapObj.get("customerSOID"));
            caseObj.setLogMessage((String)mapObj.get("logMessage")); 
            list.add((T) caseObj);
        }
        
       /* for (CaseService case1 : empList) {
            list.add((T) case1);
        }*/
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
        String query = "SELECT COUNT(0) FROM public.CaseService";
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
        ResultSetHandler<List<CaseService>> resultSetHandler =
                new BeanListHandler<CaseService>(CaseService.class, new BasicRowProcessor(new GenerousBeanProcessor()));
        String query = "SELECT * FROM \"CaseService\" WHERE \"CaseID\" >= ? AND  \"CaseID\" <= ?";
        List<CaseService> empList = queryRunner.query(connection, query.toLowerCase(), resultSetHandler, from, to);
        for (CaseService case1 : empList) {
            System.err.println(case1);
            list.add((T) case1);
        }
        return list;
    }
}
