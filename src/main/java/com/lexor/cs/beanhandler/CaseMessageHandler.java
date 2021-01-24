package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.CaseMessage;
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

public class CaseMessageHandler extends BeanListHandler<CaseMessage> {
    private Connection connection;

    public CaseMessageHandler(Connection con) {
        super(CaseMessage.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("MessageID", "MessageID");
        columnsToFieldsMap.put("CaseID", "CaseID");
        columnsToFieldsMap.put("SendTo", "SendTo");
        columnsToFieldsMap.put("Subject", "Subject");
        columnsToFieldsMap.put("MessageBody", "MessageBody");
        columnsToFieldsMap.put("CreatedDate", "CreatedDate");
        columnsToFieldsMap.put("UpdatedDate", "UpdatedDate");
         
        return columnsToFieldsMap;
    }
}
