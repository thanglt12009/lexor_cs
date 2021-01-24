package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author THANGLT1
 */
public class CaseLog implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer caseID;
    
    private Integer logID;
    
    private String logMessage; 
    
    private String createdDate;
    
    public CaseLog() {
    
    }
    
    public CaseLog(int caseID) {
        this.caseID = caseID;
    }
    
    public CaseLog(int caseID, int logID) {
        this.caseID = caseID;
        this.logID = logID;
    }
    
    public Integer getCaseID() {
        return caseID;
    }
    
    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }
    
    public Integer getLogID() {
        return logID;
    }
    
    public void setLogID(int logID) {
        this.logID = logID;
    }
    
    public String getLogMessage() {
        return logMessage;
    }
    
    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    } 
    
    public String getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
