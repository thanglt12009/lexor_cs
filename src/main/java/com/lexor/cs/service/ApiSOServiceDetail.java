package com.lexor.cs.service;

import com.lexor.cs.beanhandler.CaseInformationHandler;
import com.lexor.cs.domain.ApiSaleOrderDetails;
import com.lexor.cs.domain.CaseInformation;
import com.lexor.cs.domain.Product;
import com.lexor.cs.util.APIClient;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.json.*;

public class ApiSOServiceDetail extends BaseService<CaseInformation> {

    @Override
    public CaseInformation get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        List<T> list = new ArrayList<>();
        try {
            Integer id = (Integer) o;
            APIClient apiClient = new APIClient();
            
            String result = apiClient.setRoute("qbservice/getOrderInfo/" + id).execute();
            JSONObject object = new JSONObject(result);
            JSONObject salon = object.getJSONObject("customer").getJSONObject("salonInfo");
            JSONArray products = object.getJSONArray("orderDetail");
            
            List<Product> productList = new ArrayList<>();
            for (int i = 0 ; i < products.length() ; i ++) {
                JSONObject product = products.getJSONObject(i);
                productList.add(new Product(
                    product.getString("Product"), 
                    product.getInt("Quantity"), 
                    product.getFloat("Price")
                ));
            }
            
            list.add( (T) new ApiSaleOrderDetails(
                    id,
                    salon.getInt("id"), 
                    salon.getString("address"),
                    salon.getInt("idUSZip"),
                    productList
            ));
            
        } catch (IOException ex) {
           throw new SQLException("Record not found");
        }
        
        if (list.size() > 0) {
            return (T) list.get(0);
        }
        
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        String filterStr = (String) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseInformation>> resultHandler = new CaseInformationHandler(connection);
        String query = "SELECT * FROM \"public\".\"CaseInformation\" WHERE CONCAT(\"DocCode\", \" \", \"Address\", \" \", \"Status\", \" \") LIKE ?;";
        
        List<CaseInformation> empList = queryRunner.query(connection, query.toLowerCase() , resultHandler, filterStr);
        List<T> list = new ArrayList<>();
        for (CaseInformation case1 : empList) {
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
