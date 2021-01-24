package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author THANGLT1
 */
public class CaseReturn implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer caseID;
    
    private Integer caseReturnID;
    
    private Integer customerSOID;     
    
    private String createdDate;
    
    private String updatedDate;
    
    public CaseReturn() {
    
    }
    
    public CaseReturn(int caseID) {
        this.caseID = caseID;
    }
    
    public CaseReturn(int caseID, int caseReturnID) {
        this.caseID = caseID;
        this.caseReturnID = caseReturnID;
    }     
    
    public CaseReturn(int caseID, int caseReturnID, int customerSOID) {
        this.caseID = caseID;
        this.caseReturnID = caseReturnID;
        this.customerSOID = customerSOID;
    }     
    
    public Integer getCaseID() {
        return caseID;
    }
    
    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }
    
    public Integer getCaseReturnID() {
        return caseReturnID;
    }
    
    public void setCaseReturnID(int caseReturnID) {
        this.caseReturnID = caseReturnID;
    }
    
    public Integer getCustomerSOID() {
        return customerSOID;
    }
    
    public void setCustomerSOID(int customerSOID) {
        this.customerSOID = customerSOID;
    } 
    
    public String getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    
     public String getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
