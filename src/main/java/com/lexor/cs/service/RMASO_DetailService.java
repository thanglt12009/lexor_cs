package com.lexor.cs.service;

import com.lexor.cs.beanhandler.RMASO_DetailHandler;
import com.lexor.cs.domain.RMA;
import com.lexor.cs.domain.RMASO_Detail;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class RMASO_DetailService extends BaseService<RMASO_Detail> {

    @Override
    public RMASO_Detail get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        RMASO_Detail c = (RMASO_Detail) o;
        QueryRunner runner = new QueryRunner();
        String query
                = "INSERT INTO \"public\".\"RMASO_Detail\" (\"RMASOID\", \"RMAID\", \"ProductID\",\"Quantity\", \"Price\", \"WareHouse\", \"CreatedDate\", \"UpdatedDate\") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        return runner.update(connection, query.toLowerCase(), c.getRMASOID(), c.getRMAID(), c.getProductID(),  c.getQuantity(), c.getPrice(), c.getWareHouse(), c.getCreatedDate(), c.getUpdatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        RMASO_Detail c = (RMASO_Detail) o;
        QueryRunner runner = new QueryRunner();
        List<String> param = new ArrayList<>();
        List<Object> condition = new ArrayList<>();
        Object[] paramObject;
         
        if (c.getWareHouse() != null) {
            param.add("WareHouse=?") ;
            condition.add(c.getWareHouse());
        }
  
        condition.add(id);
        paramObject = condition.toArray();
        
        String query
                = "UPDATE \"public\".\"RMASO_Detail\" "
                + " SET "  + String.join(" ,", param.toArray(new String[0]))
                + " WHERE \"SODetail_ID\"=?;";

        return runner.update(connection, query.toLowerCase(), paramObject);
    }

    @Override
    public int remove(Object o) throws SQLException {
        RMASO_Detail c = (RMASO_Detail) o;
        QueryRunner runner = new QueryRunner();
        String query = "DELETE FROM \"public\".\"RMASO_Detail\" WHERE SODetail_ID = ?;";
        
        try {
            return runner.execute(connection, query.toLowerCase() , c.getSODetail_ID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        //ResultSetHandler<List<RMASO_Detail>> resultHandler = new RMASO_DetailHandler(connection);
        String query = "SELECT * FROM \"public\".\"RMASO_Detail\" WHERE \"SODetail_ID\" = ?;";
        
        //List<RMASO_Detail> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, id);
        List<T> list = new ArrayList<>();
        List<Map<String, Object>> empLists = queryRunner.query(connection, query.toLowerCase(), new MapListHandler(), id);
        for(int i=0; i< empLists.size();i++) {
            Map<String, Object> mapObj = (Map<String, Object>) empLists.get(i);
            RMASO_Detail caseObj = new RMASO_Detail();
            caseObj.setSODetail_ID((Integer)mapObj.get("SODetail_ID"));
            caseObj.setRMAID((Integer)mapObj.get("RMAID"));
            caseObj.setProductID((Integer)mapObj.get("productID"));
            caseObj.setPrice((Double)mapObj.get("price")); 
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
        ResultSetHandler<List<RMASO_Detail>> resultHandler = new RMASO_DetailHandler(connection);
        String query = "SELECT * FROM \"public\".\"RMASO_Detail\" WHERE CONCAT_WS(\" \", \"RMAID\", \"SOID\", \"SODetail_ID\", \"ProductID\") LIKE '%?%';";

        List<RMASO_Detail> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, status);

        List<T> list = new ArrayList<>();
        for (RMASO_Detail case1 : empList) {
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
        String query = "SELECT COUNT(0) FROM \"public\".\"RMASO_Detail\";";
        
        long count = runner.query(connection, query.toLowerCase(), scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        String query = "SELECT * FROM public.RMASO_Detail JOIN public.RMASO ON RMASO.RMASOID = RMASO_Detail.RMASOID WHERE RMASO_Detail.RMAID >= ? AND  RMASO_Detail.RMAID <= ?;";
        
        List<Map<String, Object>> empLists = queryRunner.query(connection, query.toLowerCase(), new MapListHandler(), from, to);
            for(int i=0; i< empLists.size();i++) {
            Map<String, Object> mapObj = (Map<String, Object>) empLists.get(i);
            RMASO_Detail caseObj = new RMASO_Detail();
            caseObj.setSODetail_ID((Integer)mapObj.get("SODetail_ID"));
            caseObj.setRMAID((Integer)mapObj.get("RMAID"));
            caseObj.setSOID((Integer)mapObj.get("SOID"));
            caseObj.setRMASOID((Integer)mapObj.get("RMASOID"));
            caseObj.setProductID((Integer)mapObj.get("productID"));
            caseObj.setPrice((Double)mapObj.get("price")); 
            caseObj.setQuantity((Integer)mapObj.get("quantity")); 
            caseObj.setWareHouse((Integer)mapObj.get("warehouse"));
            list.add((T) caseObj);
        }
        
        return list;
    }
}
