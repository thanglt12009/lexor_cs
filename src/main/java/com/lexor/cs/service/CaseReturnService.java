package com.lexor.cs.service;

import com.lexor.cs.beanhandler.CaseHandler;
import com.lexor.cs.beanhandler.CaseReturnHandler;
import com.lexor.cs.domain.Case;
import com.lexor.cs.domain.CaseReturn;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class CaseReturnService extends BaseService<Case> {

    @Override
    public Case get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int persist(Object o) throws SQLException {
        Case c = (Case) o;
        QueryRunner runner = new QueryRunner();
        String insertSQL
                = "INSERT INTO \"public\".\"Case\" (\"CaseName\", \"CustomerID\", \"SalonID\", \"CasePriority\", \"CaseType\", \"Status\", \"CustomerServiceRep\") VALUES (?, ?, ?, ?, ?, ?, ?);";
        
        return runner.insert(connection, insertSQL.toLowerCase(), new ScalarHandler<Integer>(), c.getCaseName(), c.getCustomerID(), c.getSalonID(), c.getCasePriority(), c.getCaseType(), c.getStatus(), c.getCustomerServiceRep()
        );
    }

    @Override
    public int update(Integer id, Object o) throws SQLException {
        Case c = (Case) o;
        QueryRunner runner = new QueryRunner();
        String updateSQL
                = "UPDATE  \"public\".\"Case\""
                + " SET \"CaseName\"=?, \"CustomerID\"=?, \"SalonID\"=?, \"CasePriority\"=?, \"Status\"=?, "
                + "\"CustomerServiceRep\"=?" 
                + " WHERE \"CaseID\"=?;";

        return runner.update(connection, updateSQL.toLowerCase(), c.getCaseName(), c.getCustomerID(), c.getSalonID(), c.getCasePriority(), c.getStatus(), c.getCustomerServiceRep(), id);
    }

    @Override
    public int remove(Object o) throws SQLException {
        Case c = (Case) o;
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM \"public\".\"Case\" WHERE ?;";
        
        try {
            return runner.execute(connection, deleteSQL.toLowerCase(), c.getCaseID());
        } catch (Exception ex) {
            throw new SQLException("Record not found");
        }
    }

    @Override
    public <T> T find(Class<T> type, Object o) throws SQLException {
        Integer id = (Integer) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<Case>> resultHandler = new CaseHandler(connection);        
        String query = "SELECT * FROM \"public\".\"Case\" WHERE \"CaseID\" = ?;";

        List<Case> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, id);
        if (empList.size() > 0) {
            return (T) empList.get(0);
        }
        throw new SQLException("Record not found");
    }
    
    @Override
    public <T> List<T> findByKeyWord(Object o) throws SQLException {
        String name = (String) o;
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseReturn>> resultHandler = new CaseReturnHandler(connection);        
        String query = "SELECT * FROM \"public\".\"Case\" WHERE CONCAT(\"CaseName\", \" \") LIKE ?;";
        
        List<CaseReturn> empList = queryRunner.query(connection, query.toLowerCase() , resultHandler, name);
        List<T> list = new ArrayList<>();
        for (CaseReturn case1 : empList) {
            list.add((T) case1);
        }
        return list; 
    }
    
    @Override
    public <T> List<T> findByJoinedKeyWord  (Object o) throws SQLException {        
        return new ArrayList<>();
    }

    @Override
    public long count() throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();

        QueryRunner runner = new QueryRunner();
        String query = "SELECT COUNT(0) FROM \"public\".\"Case\";";
        
        long count = runner.query(connection, query.toLowerCase(), scalarHandler);
        return count;
    }

    @Override
    public <T> List<T> findRange(Class<T> type, int[] range) throws SQLException {
        Integer from = range[0];
        Integer to = range[1];
        List<T> list = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<CaseReturn>> resultHandler = new CaseReturnHandler(connection);        
        String query = "SELECT * FROM \"public\".\"CaseReturn\" WHERE \"CaseID\" >= ? AND  \"CaseID\" <= ?;";
        
        List<CaseReturn> empList = queryRunner.query(connection, query.toLowerCase(), resultHandler, from, to);
        for (CaseReturn case1 : empList) {
            list.add((T) case1);
        }
        return list;
    }
}
