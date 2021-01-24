package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.CaseInformation;
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

public class CaseInformationHandler extends BeanListHandler<CaseInformation> {
    private Connection connection;

    public CaseInformationHandler(Connection con) {
        super(CaseInformation.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("TransactionID", "TransactionID");
        columnsToFieldsMap.put("CaseID", "CaseID");
        columnsToFieldsMap.put("DocCode", "DocCode");
        columnsToFieldsMap.put("Status", "Status");
        columnsToFieldsMap.put("Address", "Address");
        columnsToFieldsMap.put("CreatedDate", "CreatedDate");
        return columnsToFieldsMap;
    }
}
