package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.RMAMessage;
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

public class RMAMessageHandler extends BeanListHandler<RMAMessage> {
    private Connection connection;

    public RMAMessageHandler(Connection con) {
        super(RMAMessage.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("RMAID", "RMAID");
        columnsToFieldsMap.put("MessageID", "MessageID");
        columnsToFieldsMap.put("SendTo", "SendTo");
        columnsToFieldsMap.put("Subject", "Subject"); 
        columnsToFieldsMap.put("MessageBody", "MessageBody"); 
        columnsToFieldsMap.put("CreatedDate", "CreatedDate");
         
        return columnsToFieldsMap;
    }
}
