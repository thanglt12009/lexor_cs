package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author THANGLT1
 */
public class RMA implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer RMAID;
    
    private Integer CaseID;
    
    private Integer CustomerSOID;
    
    private String Status; 
    
    private String CreatedDate;
    
    private String UpdatedDate;
    
    public RMA() {
    
    }
    
    public RMA(int RMAID) {
        this.RMAID = RMAID;
    }
    
    public RMA(int RMAID, int CaseID) {
        this.RMAID = RMAID;
        this.CaseID = CaseID;
    }        
    
    public Integer getRMAID() {
        return RMAID;
    }
    
    public void setRMAID(int RMAID) {
        this.RMAID = RMAID;
    }
    
    public Integer getCaseID() {
        return CaseID;
    }
    
    public void setCaseID(int CaseID) {
        this.CaseID = CaseID;
    }     
    
    public int getCustomerSOID() {
        return CustomerSOID;
    }
    
    public void setCustomerSOID(int CustomerSOID) {
        this.CustomerSOID = CustomerSOID;
    } 

    public String getStatus() {
        return Status;
    }
    
    public void setStatus(String Status) {
        this.Status = Status;
    }     
    
    public String getCreatedDate() {
        return CreatedDate;
    }
    
    public void setCreatedDate(String CreatedDate) {
        this.CreatedDate = CreatedDate;
    }
    
     public String getUpdatedDate() {
        return UpdatedDate;
    }
    
    public void setUpdatedDate(String UpdatedDate) {
        this.UpdatedDate = UpdatedDate;
    }
}
