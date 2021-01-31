package com.lexor.cs.service;

import com.lexor.cs.beanhandler.ServiceDetailHandler;
import com.lexor.cs.beanhandler.ServiceMasterHandler;
import com.lexor.cs.domain.ServiceDetail;
import com.lexor.cs.domain.ServiceMaster;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class ServiceDetailService extends BaseService<ServiceDetail> {

    @Override
    public ServiceDetail get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        ServiceDetail c = (ServiceDetail) o;
        QueryRunner runner = new QueryRunner();
        String insertSQL
                = "INSERT INTO public.ServiceDetail (\"ServiceMasterID\", \"ProductID\" , \"Quantity\" , \"SoldPrice\" , \"Amount\", \"TotalWeight\"  , \"SerialNumber\", \"IsWarrantly\", \"WarrantyStartDate\", \"WarrantyEndDate\", \"PaymentType\", \"warehouse\" ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        return runner.update(connection, insertSQL.toLowerCase(), c.getServiceMasterID(), c.getProductID(), c.getQuantity(),c.getSoldPrice(),
                c.getAmount(),  c.getTotalWeight(), c.getSerialNumber(), c.getIsWarrantly(),
                c.getwarrantyStartDate(), c.getWarrantyEndDate(), c.getPaymentType(), c.getWarehouse());
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        ServiceDetail c = (ServiceDetail) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE public.ServiceDetail "
                + " SET \"ServiceMasterID\"=?, \"ProductID\"=?, \"Quantity\"=?, \"SoldPrice\"=?, \"Amount\"=?, \"TotalWeight\"=?, \"SerialNumber\"=?, \"IsWarrantly\"=?, \"WarrantyStartDate\"=?, \"WarrantyEndDate\"=?, \"PaymentType\"=?"
                + " WHERE \"ServiceMasterID\"=?;";

        return runner.update(connection, updateSQL.toLowerCase(), c.getServiceMasterID(), c.getProductID(), c.getQuantity(),c.getSoldPrice(),
                c.getAmount(),  c.getTotalWeight(), c.getSerialNumber(), c.getIsWarrantly(),
                c.getwarrantyStartDate(), c.getWarrantyEndDate(), c.getPaymentType(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        ServiceDetail c = (ServiceDetail) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM public.\"ServiceDetail\" WHERE \"ServiceDetailID\" =?";
        try {
            return runner.execute(connection, deleteSQL.toLowerCase(), c.getServiceDetailID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<ServiceDetail>> resultHandler = new ServiceDetailHandler(connection);
        
        String query = "SELECT * FROM public.ServiceDetail WHERE \"ServiceDetailID\" = ?";
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

        List<ServiceMaster> empList = queryRunner.query(connection, "SELECT * FROM \"ServiceDetail\"  WHERE CONCAT_WS(\" \", \"CaseServiceDetailID\", \"CaseServiceID\", \"CustomerSOID\") LIKE '%?%'", resultHandler, id);
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

        List<ServiceMaster> empList = queryRunner.query(connection, "SELECT ServiceDetail.* FROM \"ServiceDetail\" INNER JOIN \"ServiceMaster\" ON \"ServiceDetail.ServiceMasterID = ServiceMaster.ServiceMasterID\" WHERE \"ServiceMaster.ServiceMasterID\" = ?", resultHandler, id);
        List<T> list = new ArrayList<>();
        for (ServiceMaster case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }

    @Override
    public long count() throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();

        QueryRunner runner = new QueryRunner();
        String query = "SELECT COUNT(0) FROM \"ServiceDetail\"";
        long count = runner.query(connection, query, scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<ServiceDetail>> resultHandler = new ServiceDetailHandler(connection);
            
        String query = "SELECT * FROM public.ServiceDetail WHERE ServiceMasterID >= ? AND ServiceMasterID <= ?";
        List<ServiceDetail> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, from, to);
        for (ServiceDetail case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
