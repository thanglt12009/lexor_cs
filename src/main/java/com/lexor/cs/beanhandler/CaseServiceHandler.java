package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.CaseService;
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

public class CaseServiceHandler extends BeanListHandler<CaseService> {
    private Connection connection;

    public CaseServiceHandler(Connection con) {
        super(CaseService.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("CaseServiceID", "CaseServiceID");
        columnsToFieldsMap.put("CaseID", "CaseID");
        columnsToFieldsMap.put("CustomerSOID", "CustomerSOID");
        columnsToFieldsMap.put("LogMessage", "LogMessage");
        columnsToFieldsMap.put("CreatedDate", "CreatedDate");
        columnsToFieldsMap.put("UpdatedDate", "UpdatedDate");
         
        return columnsToFieldsMap;
    }
}
