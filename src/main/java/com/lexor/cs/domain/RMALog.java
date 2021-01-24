package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author THANGLT1
 */
public class RMALog implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer RMAID;
    
    private Integer LogID;
    
    private String logMessage; 
    
    private String createdDate; 
    
    public RMALog() {
    
    }
    
    public RMALog(int RMAID) {
        this.RMAID = RMAID;
    }
    
    public RMALog(int RMAID, int LogID) {
        this.RMAID = RMAID;
        this.LogID = LogID;
    }        
    
    public Integer getRMAID() {
        return RMAID;
    }
    
    public void setRMAID(int RMAID) {
        this.RMAID = RMAID;
    }
    
    public Integer getLogID() {
        return LogID;
    }     
    
     public void setLogID(int LogID) {
        this.LogID = LogID;
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
