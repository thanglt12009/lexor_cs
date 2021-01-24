package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.RMALog;
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

public class RMALoglHandler extends BeanListHandler<RMALog> {
    private Connection connection;

    public RMALoglHandler(Connection con) {
        super(RMALog.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("RMAID", "RMAID");
        columnsToFieldsMap.put("LogID", "LogID");
        columnsToFieldsMap.put("LogMessage", "LogMessage");
        columnsToFieldsMap.put("CreatedDate", "CreatedDate");  
        
        return columnsToFieldsMap;
    }
}
