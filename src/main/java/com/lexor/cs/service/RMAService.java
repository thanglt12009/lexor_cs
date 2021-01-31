package com.lexor.cs.service;

import com.lexor.cs.beanhandler.RMAHandler;
import com.lexor.cs.domain.CaseService;
import com.lexor.cs.domain.RMA;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class RMAService extends BaseService<RMA> {

    @Override
    public RMA get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        RMA c = (RMA) o;
        QueryRunner runner = new QueryRunner();
        String insertSQL
                = "INSERT INTO public.RMA (\"CaseID\", \"CustomerSOID\", \"Status\", \"CreatedDate\", \"UpdatedDate\") VALUES (?, ?, ?, ?, ?)";

        return runner.insert(connection, insertSQL.toLowerCase(), new ScalarHandler<Integer>(), c.getCaseID(), c.getCustomerSOID(), c.getStatus(), c.getCreatedDate(), c.getUpdatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        RMA c = (RMA) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE public.RMA "
                + " SET \"Status\"=?, \"CreatedDate\"=?, \"UpdatedDate\"=? "
                + " WHERE \"RMAID\"=?;";

        return runner.update(connection, updateSQL.toLowerCase(), c.getStatus(), c.getCreatedDate(), c.getUpdatedDate(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        RMA c = (RMA) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM public.\"RMA\" WHERE ?;";
        try {
            return runner.execute(connection, deleteSQL, c.getRMAID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMA>> resultHandler = new RMAHandler(connection);

        String query = "SELECT * FROM public.RMA WHERE \"RMAID\" = ?";
        
        List<Map<String, Object>> empLists = queryRunner.query(connection, query.toLowerCase(), new MapListHandler(), id);
        List<T> list = new ArrayList<>();
        for(int i=0; i< empLists.size();i++) {
            Map<String, Object> mapObj = (Map<String, Object>) empLists.get(i);
            RMA caseObj = new RMA();
            caseObj.setCaseID((Integer)mapObj.get("caseID"));
            caseObj.setRMAID((Integer)mapObj.get("rmaID"));
            caseObj.setCustomerSOID((Integer)mapObj.get("customerSOID"));
            caseObj.setStatus((String)mapObj.get("status")); 
            list.add((T) caseObj);
        }
        
        if (list.size() > 0) {
            return (T) list.get(0);
        }
        /*
        List<RMA> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, id);

        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        */
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        Integer status = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMA>> resultHandler = new RMAHandler(connection);
         String query = "SELECT * FROM public.RMA order by RMAID desc";

         /*
        List<RMA> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler);
        
        List<T> list = new ArrayList<>();
        for (RMA case1 : empList) {
            list.add((T) case1);
        }
        return list;
        */
        List<Map<String, Object>> empLists = queryRunner.query(connection, query.toLowerCase(), new MapListHandler());
        List<T> list = new ArrayList<>();
        for(int i=0; i< empLists.size();i++) {
            Map<String, Object> mapObj = (Map<String, Object>) empLists.get(i);
            RMA caseObj = new RMA();
            caseObj.setCaseID((Integer)mapObj.get("caseID"));
            caseObj.setRMAID((Integer)mapObj.get("rmaID"));
            caseObj.setCustomerSOID((Integer)mapObj.get("customerSOID"));
            caseObj.setStatus((String)mapObj.get("status")); 
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
        String query = "SELECT COUNT(0) FROM public.RMA";
        long count = runner.query(connection, query, scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<RMA>> resultHandler = new RMAHandler(connection);

        List<RMA> empList = queryRunner.query(connection, "SELECT * FROM \"RMA\" WHERE \"RMAID\" >= ? AND  \"RMAID\" <= ?", resultHandler, from, to);
        for (RMA case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
