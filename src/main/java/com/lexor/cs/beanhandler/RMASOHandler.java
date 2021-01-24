package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.RMASO;
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

public class RMASOHandler extends BeanListHandler<RMASO> {
    private Connection connection;

    public RMASOHandler(Connection con) {
        super(RMASO.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("SOID", "SOID");
        columnsToFieldsMap.put("RMAID", "RMAID");
        columnsToFieldsMap.put("Fee", "Fee"); 
        columnsToFieldsMap.put("Total", "Total"); 
        columnsToFieldsMap.put("CreatedDate", "CreatedDate");
        columnsToFieldsMap.put("UpdatedDate", "UpdatedDate");
         
        return columnsToFieldsMap;
    }
}
