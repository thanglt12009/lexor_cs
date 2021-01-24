package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.CaseLog;
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

public class CaseLogHandler extends BeanListHandler<CaseLog> {
    private Connection connection;

    public CaseLogHandler(Connection con) {
        super(CaseLog.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("LogID", "LogID");
        columnsToFieldsMap.put("CaseID", "CaseID");
        columnsToFieldsMap.put("LogMessage", "LogMessage");
        columnsToFieldsMap.put("CreatedDate", "CreatedDate");
         
        return columnsToFieldsMap;
    }
}
