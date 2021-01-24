package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.Case;
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

public class CaseHandler extends BeanListHandler<Case> {
    private Connection connection;

    public CaseHandler(Connection con) {
        super(Case.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("CaseID", "CaseID");
        columnsToFieldsMap.put("CustomerID", "CustomerID");
        columnsToFieldsMap.put("SalonID", "SalonID");
        columnsToFieldsMap.put("CaseName", "CaseName");
        columnsToFieldsMap.put("CasePriority", "CasePriority");
        columnsToFieldsMap.put("CaseType", "CaseType");
        columnsToFieldsMap.put("Status", "Status");
        columnsToFieldsMap.put("CreatedDate", "CreatedDate");
        columnsToFieldsMap.put("UpdatedDate", "UpdatedDate");
        return columnsToFieldsMap;
    }
}
