package com.lexor.cs.service;

import com.lexor.cs.beanhandler.CaseInformationHandler;
import com.lexor.cs.domain.ApiSaleOrder;
import com.lexor.cs.domain.CaseInformation;
import com.lexor.cs.util.APIClient;
import com.lexor.cs.util.TokenHelper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApiSOService extends BaseService<CaseInformation> {

    @Override
    public ApiSaleOrder get(Integer id) throws SQLException {
        ApiSaleOrder apiSaleOrder = new ApiSaleOrder();
        try {
            APIClient apiClient = new APIClient();
            String result = apiClient.setRoute("servicecase/getOrderServiceStatus/" + id, TokenHelper.getToken()).execute();
            JSONObject obj = new JSONObject(result);
            
            if ( null != obj.getString("orderServiceStatus") ) {
                apiSaleOrder.setCode((String)obj.getString("orderServiceStatus"));
            }
            
        } catch (Exception ex) {
            apiSaleOrder.setCode("Not Found");
        }
        
        return apiSaleOrder;
    }

    @Override
    public int persist(Object o) throws SQLException {
        CaseInformation c = (CaseInformation) o;
        QueryRunner runner = new QueryRunner();
        String query
                = "INSERT INTO \"public\".\"CaseInformation\" ( \"CaseID\", \"DocCode\", \"Status\", \"Address\", \"CreatedDate\" ) VALUES (?, ?, ?, ?, ?::timestamp);";

        return runner.update(connection, query.toLowerCase(), c.getCaseID(), c.getDocCode(), Integer.parseInt(c.getStatus()), c.getAddress(), c.getCreatedDate());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        CaseInformation c = (CaseInformation) o;
        QueryRunner runner = new QueryRunner();
        String query
                = "UPDATE \"public\".\"CaseInformation\" "
                + " SET \"CaseID\"=?, \"DocCode\"=?, \"Status\"=?, \"Address\"=?, \"CreatedDate\"=? "
                + " WHERE \"TransactionID\"=?;";

        return runner.update(connection, query.toLowerCase(), c.getCaseID(), c.getDocCode(), c.getStatus(), c.getAddress(), c.getCreatedDate(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        CaseInformation c = (CaseInformation) o;
        QueryRunner runner = new QueryRunner();
        String query = "DELETE FROM \"public\".\"CaseInformation\" WHERE ?;";
        
        try {
            return runner.execute(connection, query.toLowerCase(), c.getTransactionID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        ApiSaleOrder apiSaleOrder = null;
        try {
            Integer id = (Integer) o;
            APIClient apiClient = new APIClient();
            String result = apiClient.setRoute("reactorder/getOrderSearch/?idOrder=" + id + "&currentPage=1&pageLimit=5&orderBy=o.id&asc=desc&idOrderStatus=0&isInquireQuote=false", TokenHelper.getToken()).execute();
            JSONArray jsonData = new JSONArray(result);
            JSONObject obj = jsonData.getJSONObject(0);
            for (Object key : obj.keySet()) {
                Object keyName = key;
                Object value = obj.get((String)keyName);
                
                if ( keyName.equals("id") ) {
                    String name = value.toString();
                    apiSaleOrder = new ApiSaleOrder(name, name);
                    break;
                }
            }
            

        } catch (IOException ex) {
           throw new SQLException("Record not found");
        }
        
        return (T) apiSaleOrder;
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        List<T> list = new ArrayList<>();
        ApiSaleOrder apiSaleOrder = null;
        try {
            Integer id = (Integer) o;
            APIClient apiClient = new APIClient();
            
            String result = apiClient.setRoute("reactorder/getOrderSearch/?idOrder=" + id+ "&currentPage=1&pageLimit=5&orderBy=o.id&asc=desc&idOrderStatus=0&isInquireQuote=false", TokenHelper.getToken()).execute();
            JSONArray jsonData = new JSONArray(result);
            
            
            for (int i = 0 ; i < jsonData.length() ; i ++) {
                for (Object object : jsonData.getJSONObject(i).keySet()) {
                    Object keyName = object;
                    Object value = jsonData.getJSONObject(i).get((String)keyName);

                    if ( keyName.equals("id") ) {
                        String name = value.toString();
                        apiSaleOrder = new ApiSaleOrder(name, name);
                        list.add((T) apiSaleOrder);
                        continue;
                    }
                }
            }     

        } catch (IOException ex) {
           throw new SQLException("Record not found");
        }
        
        return list;
    }
    
    
    @Override
    public <T> List<T> findByJoinedKeyWord  (Object o) throws SQLException {        
        return new ArrayList<>();
    }


    @Override
    public long count(Integer id) throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();
        QueryRunner runner = new QueryRunner();
        String query = "SELECT COUNT(0) FROM \"CaseInformation\";";
        
        long count = runner.query(connection, query.toLowerCase(), scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseInformation>> resultHandler = new CaseInformationHandler(connection);        
        String query = "SELECT * FROM \"public\".\"CaseInformation\" WHERE \"CaseID\" >= ? AND  \"CaseID\" <= ?;";
        
        List<CaseInformation> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, from, to);
        for (CaseInformation case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
