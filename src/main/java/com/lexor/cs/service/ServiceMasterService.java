package com.lexor.cs.service;

import com.lexor.cs.beanhandler.ServiceMasterHandler;
import com.lexor.cs.domain.CaseService;
import com.lexor.cs.domain.ServiceMaster;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class ServiceMasterService extends BaseService<ServiceMaster> {

    @Override
    public ServiceMaster get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        ServiceMaster c = (ServiceMaster) o;
        QueryRunner runner = new QueryRunner();
        String query
                = "INSERT INTO \"public\".\"ServiceMaster\" (\"CaseServiceID\", \"SubTotal\" , \"ShippingFee\" , \"ManagerDiscount\" , \"Total\", \"CreatedDate\"  , \"IsSubmittedProduction\", \"Status\" ) VALUES (?,?,?,?,?,?,?,?);";

        return runner.insert(connection, query.toLowerCase(), new ScalarHandler<Integer>(), c.getCaseServiceID(), c.getSubTotal(), c.getShippingFee(),c.getManagerDiscount(), c.getTotal(), c.getCreatedDate(), c.getIsSubmittedProduction(), c.getStatus());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        ServiceMaster c = (ServiceMaster) o;
        QueryRunner runner = new QueryRunner();
        
        List<String> param = new ArrayList<>();
        List<Object> condition = new ArrayList<>();
        Object[] paramObject;
         
        if (c.getIsSubmittedProduction() != null) {
            param.add("IsSubmittedProduction=?") ;
            condition.add(c.getIsSubmittedProduction());
        }
        
        if (c.getStatus() != null) {
            param.add("Status=?");
            condition.add(c.getStatus());
        }
        
        if (c.getPaymentType()!= null) {
            param.add("PaymentType=?");
            condition.add(c.getPaymentType());
        }
        
        if (c.getShippingFee()!= null) {
            param.add("ShippingFee=?");
            condition.add(c.getShippingFee());
        }
        
        if (c.getTotal()!= null) {
            param.add("Total=?");
            condition.add(c.getTotal());
        }
        
        if (c.getSubTotal()!= null) {
            param.add("SubTotal=?");
            condition.add(c.getSubTotal());
        }
        
        condition.add(id);
        paramObject = condition.toArray();
       
        String query
                = "UPDATE \"public\".\"ServiceMaster\""
                + " SET " + String.join(" ,", param.toArray(new String[0]))
                + " WHERE \"CaseServiceID\"=?;";

        //\"CaseServiceID\"=?, \"SubTotal\"=?, \"ShippingFee\"=?, \"ManagerDiscount\"=?, \"Total\"=?, \"CreatedDate\"=?,
        //return runner.update(connection, updateSQL.toLowerCase(), c.getCaseServiceID(), c.getSubTotal(), c.getShippingFee(), c.getManagerDiscount(), c.getTotal(), c.getCreatedDate(), c.getIsSubmittedProduction(), c.getStatus(), id);
        return runner.update(connection, query.toLowerCase(), paramObject);

    }

    @Override
    public int remove(Object o) throws SQLException {
        ServiceMaster c = (ServiceMaster) o;
        QueryRunner runner = new QueryRunner();
        String query = "DELETE FROM \"public\".\"ServiceMaster\" WHERE ?;";
        
        try {
            return runner.execute(connection, query.toLowerCase(), c.getServiceMasterID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        //ResultSetHandler<List<ServiceMaster>> resultHandler = new ServiceMasterHandler(connection);
        String query = "SELECT * FROM \"public\".\"ServiceMaster\" WHERE \"CaseServiceId\" = ?;";

        //List<ServiceMaster> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, id);
        List<Map<String, Object>> empLists = queryRunner.query(connection, query.toLowerCase(), new MapListHandler(), id);
        List<T> list = new ArrayList<>();
        
        for(int i=0; i< empLists.size();i++) {
            Map<String, Object> mapObj = (Map<String, Object>) empLists.get(i);
            ServiceMaster serviceMaster = new ServiceMaster();
            serviceMaster.setCaseServiceID((Integer)mapObj.get("caseServiceID"));
            serviceMaster.setPaymentType((Integer)mapObj.get("paymentType"));
            serviceMaster.setServiceMasterID((Integer)mapObj.get("serviceMasterID"));
            serviceMaster.setShippingFee((Double)mapObj.get("shippingFee"));
            list.add((T) serviceMaster);
        }
        if (list.size() > 0) {
            return (T) list.get(0);
        }
        
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        Integer serviceId = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<ServiceMaster>> resultHandler = new ServiceMasterHandler(connection);
        String query = "SELECT * FROM \"public\".\"ServiceMaster\" WHERE CONCAT(\"Status\", \" \") LIKE ?;";
       
        
        List<ServiceMaster> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, serviceId);
        List<T> list = new ArrayList<>();
        for (ServiceMaster case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
    
    @Override
    public <T> List<T> findByJoinedKeyWord(Object o) throws SQLException {
        return new ArrayList<>();        
    }

    @Override
    public long count() throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();

        QueryRunner runner = new QueryRunner();
        String query = "SELECT COUNT(0) FROM \"public\".\"ServiceMaster\";";
        
        long count = runner.query(connection, query.toLowerCase(), scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<ServiceMaster>> resultHandler = new ServiceMasterHandler(connection);            
        String query = "SELECT * FROM \"public\".\"ServiceMaster\" WHERE \"ServiceMasterID\" >= ? AND \"ServiceMasterID\" <= ?;";
        
        List<ServiceMaster> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, from, to);
        for (ServiceMaster case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
