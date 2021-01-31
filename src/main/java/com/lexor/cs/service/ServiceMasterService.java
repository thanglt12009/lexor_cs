package com.lexor.cs.service;

import com.lexor.cs.beanhandler.ServiceMasterHandler;
import com.lexor.cs.domain.ServiceMaster;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
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
        String insertSQL
                = "INSERT INTO public.ServiceMaster (\"CaseServiceID\", \"SubTotal\" , \"ShippingFee\" , \"ManagerDiscount\" , \"Total\", \"CreatedDate\"  , \"IsSubmittedProduction\", \"Status\" ) VALUES (?,?,?,?,?,?,?,?)";

        return runner.insert(connection, insertSQL.toLowerCase(), new ScalarHandler<Integer>(), c.getCaseServiceID(), c.getSubTotal(), c.getShippingFee(),c.getManagerDiscount(), c.getTotal(), c.getCreatedDate(), c.getIsSubmittedProduction(), c.getStatus());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        ServiceMaster c = (ServiceMaster) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE public.ServiceMaster"
                + " SET  \"IsSubmittedProduction\"=?, \"Status\"=?"
                + " WHERE \"CaseServiceID\"=?;";
        //\"CaseServiceID\"=?, \"SubTotal\"=?, \"ShippingFee\"=?, \"ManagerDiscount\"=?, \"Total\"=?, \"CreatedDate\"=?,
        //return runner.update(connection, updateSQL.toLowerCase(), c.getCaseServiceID(), c.getSubTotal(), c.getShippingFee(), c.getManagerDiscount(), c.getTotal(), c.getCreatedDate(), c.getIsSubmittedProduction(), c.getStatus(), id);
        return runner.update(connection, updateSQL.toLowerCase(), c.getIsSubmittedProduction(), c.getStatus(), id);

    }

    @Override
    public int remove(Object o) throws SQLException {
        ServiceMaster c = (ServiceMaster) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM public.\"ServiceMaster\" WHERE ?;";
        try {
            return runner.execute(connection, deleteSQL, c.getServiceMasterID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<ServiceMaster>> resultHandler = new ServiceMasterHandler(connection);

        List<ServiceMaster> empList = queryRunner.query(connection, "SELECT * FROM \"ServiceMaster\" WHERE \"ServiceMasterID\" = ?", resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        Integer serviceId = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<ServiceMaster>> resultHandler = new ServiceMasterHandler(connection);
       
        
        List<ServiceMaster> empList = queryRunner.query(connection, "SELECT * FROM \"ServiceMaster\" WHERE CONCAT(\"Status\", \" \") LIKE ?", resultHandler, serviceId);
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
        String query = "SELECT COUNT(0) FROM \"ServiceMaster\"";
        long count = runner.query(connection, query, scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<ServiceMaster>> resultHandler = new ServiceMasterHandler(connection);
            
        String query = "SELECT * FROM public.ServiceMaster WHERE ServiceMasterID >= ? AND ServiceMasterID <= ?";
        List<ServiceMaster> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, from, to);
        for (ServiceMaster case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
