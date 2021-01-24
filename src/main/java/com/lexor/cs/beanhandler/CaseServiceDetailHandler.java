package com.lexor.cs.beanhandler;

import com.lexor.cs.domain.Case;
import com.lexor.cs.domain.CaseServiceDetail;
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

public class CaseServiceDetailHandler extends BeanListHandler<CaseServiceDetail> {
    private Connection connection;

    public CaseServiceDetailHandler(Connection con) {
        super(CaseServiceDetail.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
        this.connection = con;
    }
    
    private Integer caseServiceDetailID;
    
    private Integer caseServiceID;
    
    private Integer customerSOID;
    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("CaseServiceDetailID", "CaseServiceDetailID");
        columnsToFieldsMap.put("CaseServiceID", "CaseServiceID");
        columnsToFieldsMap.put("CustomerSOID", "CustomerSOID");
        columnsToFieldsMap.put("CreatedDate", "CreatedDate");
        columnsToFieldsMap.put("UpdatedDate", "UpdatedDate");
        return columnsToFieldsMap;
    }
}
