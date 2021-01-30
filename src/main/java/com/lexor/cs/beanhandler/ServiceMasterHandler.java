package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.ServiceMaster;
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

public class ServiceMasterHandler extends BeanListHandler<ServiceMaster> {
    private Connection connection;

    public ServiceMasterHandler(Connection con) {
        super(ServiceMaster.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("ServiceMasterID", "ServiceMasterID");
        columnsToFieldsMap.put("CaseServiceID", "CaseServiceID");
        columnsToFieldsMap.put("SubTotal", "SubTotal"); 
        columnsToFieldsMap.put("ShippingFee", "ShippingFee"); 
        columnsToFieldsMap.put("ManagerDiscount", "ManagerDiscount"); 
        columnsToFieldsMap.put("Total", "Total"); 
        columnsToFieldsMap.put("CreatedDate", "CreatedDate"); 
        columnsToFieldsMap.put("IsSubmittedProduction", "IsSubmittedProduction"); 
        columnsToFieldsMap.put("Status", "Status"); 
         
        return columnsToFieldsMap;
    }
}
