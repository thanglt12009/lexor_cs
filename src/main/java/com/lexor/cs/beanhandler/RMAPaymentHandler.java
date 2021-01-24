package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.RMAPayment;
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

public class RMAPaymentHandler extends BeanListHandler<RMAPayment> {
    private Connection connection;

    public RMAPaymentHandler(Connection con) {
        super(RMAPayment.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("RMAID", "RMAID");
        columnsToFieldsMap.put("PaymentID", "PaymentID");
        columnsToFieldsMap.put("PaymentType", "PaymentType");
        columnsToFieldsMap.put("PaymentAmount", "PaymentAmount"); 
        columnsToFieldsMap.put("PaymentStatus", "PaymentStatus");
         
        return columnsToFieldsMap;
    }
}
