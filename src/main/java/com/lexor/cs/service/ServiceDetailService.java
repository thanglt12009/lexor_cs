package com.lexor.cs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lexor.cs.beanhandler.ServiceDetailHandler;
import com.lexor.cs.beanhandler.ServiceMasterHandler;
import com.lexor.cs.domain.ServiceAPIDetail;
import com.lexor.cs.domain.ServiceDetail;
import com.lexor.cs.domain.ServiceMaster;
import com.lexor.cs.util.APIClient;
import com.lexor.cs.util.TokenHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

public class ServiceDetailService extends BaseService<ServiceDetail> {

    @Override
    public ServiceDetail get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        ServiceDetail c = (ServiceDetail) o;
        try {
            APIClient apiClient = new APIClient();
            ObjectMapper mapper = new ObjectMapper();
            ServiceAPIDetail serviceAPIDetail = new ServiceAPIDetail(
                    c.getSerialNumber(), 
                    c.getQuantity(),
                    c.getSoldPrice(),
                    c.getAmount(), 
                    c.getOriginalSO()
            );
            
            apiClient.setPostRoute(
                    "servicecase/execOrderService/", 
                    new StringEntity(mapper.writeValueAsString(serviceAPIDetail)), 
                    TokenHelper.getToken()
            ).post();
            
        } catch (Exception ex) {
           throw new SQLException("Api Error");
        }
        
        QueryRunner runner = new QueryRunner();
        String query
                = "INSERT INTO \"ServiceDetail\" (\"ServiceMasterID\", \"ProductID\" , \"Quantity\" , \"SoldPrice\" , \"Amount\", \"TotalWeight\"  , \"SerialNumber\", \"IsWarrantly\", \"WarrantyStartDate\", \"WarrantyEndDate\", \"PaymentType\", \"WareHouse\", \"ShipingDay\", \"ProductImage\" ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        return runner.update(connection, query.toLowerCase(), c.getServiceMasterID(), c.getProductID(), c.getQuantity(),c.getSoldPrice(),
                c.getAmount(),  c.getTotalWeight(), c.getSerialNumber(), c.getIsWarrantly(),
                c.getwarrantyStartDate(), c.getWarrantyEndDate(), c.getPaymentType(), c.getWareHouse(), c.getShipingDay(), c.getProductImage());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        ServiceDetail c = (ServiceDetail) o;
        QueryRunner runner = new QueryRunner();
        
        List<String> param = new ArrayList<>();
        List<Object> condition = new ArrayList<>();
        Object[] paramObject;
         
        if (c.getWareHouse() != null) {
            param.add("WareHouse=?") ;
            condition.add(c.getWareHouse());
        }
  
        if (c.getShipingDay() != null) {
            param.add("ShipingDay=?") ;
            condition.add(c.getShipingDay());
        }
        
        condition.add(id);
        paramObject = condition.toArray();
        
        String query
                = "UPDATE \"public\".\"ServiceDetail\" "
                + " SET "  + String.join(" ,", param.toArray(new String[0]))
                + " WHERE \"ServiceDetailID\"=?;";

        return runner.update(connection, query.toLowerCase(), paramObject);
    }

    @Override
    public int remove(Object o) throws SQLException {
        ServiceDetail c = (ServiceDetail) o;
        QueryRunner runner = new QueryRunner();
        String query = "DELETE FROM \"public\".\"ServiceDetail\" WHERE \"ServiceDetailID\" =?;";
        
        try {
            return runner.execute(connection, query.toLowerCase(), c.getServiceDetailID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<ServiceDetail>> resultHandler = new ServiceDetailHandler(connection);        
        String query = "SELECT * FROM \"public\".\"ServiceDetail\" WHERE \"ServiceDetailID\" = ?;";
        
        List<ServiceDetail> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<ServiceMaster>> resultHandler = new ServiceMasterHandler(connection);
        String query = "SELECT * FROM \"public\".\"ServiceDetail\"  WHERE CONCAT_WS(\" \", \"CaseServiceDetailID\", \"CaseServiceID\", \"CustomerSOID\") LIKE '%?%';";

        List<ServiceMaster> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, id);
        List<T> list = new ArrayList<>();
        for (ServiceMaster case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
    
    @Override
    public <T> List<T> findByJoinedKeyWord  (Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<ServiceMaster>> resultHandler = new ServiceMasterHandler(connection);
        String query = "SELECT \"public\".\"ServiceDetail\".* FROM \"public\".\"ServiceDetail\" INNER JOIN \"public\".\"ServiceMaster\" ON \"public\".\"ServiceDetail\".\"ServiceMasterID\" = \"public\".\"ServiceMaster\".\"ServiceMasterID\" WHERE \"public\".\"ServiceMaster\".\"ServiceMasterID\" = ?;";

        List<ServiceMaster> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, id);
        List<T> list = new ArrayList<>();
        for (ServiceMaster case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }

    @Override
    public long count(Integer id) throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();
        QueryRunner runner = new QueryRunner();
        String query = "SELECT COUNT(0) FROM \"public\".\"ServiceDetail\";";
        
        long count = runner.query(connection, query.toLowerCase(), scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<ServiceDetail>> resultHandler = new ServiceDetailHandler(connection);            
        String query = "SELECT * FROM \"public\".\"ServiceDetail\" WHERE \"ServiceMasterID\" >= ? AND \"ServiceMasterID\" <= ?;";
        
        List<ServiceDetail> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, from, to);
        for (ServiceDetail case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
