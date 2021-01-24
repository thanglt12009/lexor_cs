package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.CaseReturn;
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

public class CaseReturnHandler extends BeanListHandler<CaseReturn> {
    private Connection connection;

    public CaseReturnHandler(Connection con) {
        super(CaseReturn.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("CaseReturnID", "CaseReturnID");
        columnsToFieldsMap.put("CaseID", "CaseID");
        columnsToFieldsMap.put("CustomerSOID", "CustomerSOID"); 
        columnsToFieldsMap.put("CreatedDate", "CreatedDate");
        columnsToFieldsMap.put("UpdatedDate", "UpdatedDate");
         
        return columnsToFieldsMap;
    }
}
