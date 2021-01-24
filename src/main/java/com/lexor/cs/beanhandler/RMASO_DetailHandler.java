package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.RMASO_Detail;
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

public class RMASO_DetailHandler extends BeanListHandler<RMASO_Detail> {
    private Connection connection;

    public RMASO_DetailHandler(Connection con) {
        super(RMASO_Detail.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("RMAID", "RMAID");
        columnsToFieldsMap.put("SOID", "SOID");
        columnsToFieldsMap.put("SODetail_ID", "SODetail_ID");
        columnsToFieldsMap.put("ProductID", "ProductID"); 
        columnsToFieldsMap.put("Quantity", "Quantity"); 
        columnsToFieldsMap.put("Price", "Price"); 
        columnsToFieldsMap.put("CreatedDate", "CreatedDate");
        columnsToFieldsMap.put("UpdatedDate", "UpdatedDate");
         
        return columnsToFieldsMap;
    }
}
