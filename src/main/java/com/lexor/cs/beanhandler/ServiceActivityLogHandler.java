package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.ServiceLog;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class ServiceActivityLogHandler extends BeanListHandler<ServiceLog> {
    private Connection connection;

    public ServiceActivityLogHandler(Connection con) {
        super(ServiceLog.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
         
        return columnsToFieldsMap;
    }
}
