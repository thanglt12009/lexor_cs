package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.CaseType;
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

public class CaseTypeHandler extends BeanListHandler<CaseType> {
    private Connection connection;

    public CaseTypeHandler(Connection con) {
        super(CaseType.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("CaseTypeID", "CaseTypeID");
        columnsToFieldsMap.put("CaseID", "CaseID");
        columnsToFieldsMap.put("CaseTypeValue", "CaseTypeValue"); 
         
        return columnsToFieldsMap;
    }
}
