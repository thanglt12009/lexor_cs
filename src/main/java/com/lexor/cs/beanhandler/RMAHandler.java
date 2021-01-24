package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.RMA;
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

public class RMAHandler extends BeanListHandler<RMA> {
    private Connection connection;

    public RMAHandler(Connection con) {
        super(RMA.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("RMAID", "RMAID");
        columnsToFieldsMap.put("CaseID", "CaseID");
        columnsToFieldsMap.put("CustomerSOID", "CustomerSOID"); 
        columnsToFieldsMap.put("Status", "Status"); 
        columnsToFieldsMap.put("CreatedDate", "CreatedDate");
        columnsToFieldsMap.put("UpdatedDate", "UpdatedDate");
         
        return columnsToFieldsMap;
    }
}
