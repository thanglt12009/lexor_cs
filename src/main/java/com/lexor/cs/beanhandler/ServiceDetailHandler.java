package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.ServiceDetail;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class ServiceDetailHandler extends BeanListHandler<ServiceDetail> {
    private Connection connection;

    public ServiceDetailHandler(Connection con) {
        super(ServiceDetail.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("ServiceDetailID", "ServiceDetailID");
        columnsToFieldsMap.put("ServiceMasterID", "ServiceMasterID");
        columnsToFieldsMap.put("ProductID", "ProductID"); 
        columnsToFieldsMap.put("Quantity", "Quantity"); 
        columnsToFieldsMap.put("SoldPrice", "SoldPrice"); 
        columnsToFieldsMap.put("Amount", "Amount"); 
        columnsToFieldsMap.put("TotalWeight", "TotalWeight"); 
        columnsToFieldsMap.put("SerialNumber", "SerialNumber"); 
        columnsToFieldsMap.put("IsWarrantly", "IsWarrantly");
        columnsToFieldsMap.put("WarrantyStartDate", "WarrantyStartDate");
        columnsToFieldsMap.put("WarrantyEndDate", "WarrantyEndDate");
        columnsToFieldsMap.put("PaymentType", "PaymentType");
        columnsToFieldsMap.put("LogMessage", "LogMessage");
        columnsToFieldsMap.put("CreatedDate", "CreatedDate");
         
        return columnsToFieldsMap;
    }
}
