package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author THANGLT1
 */
public class CaseInformation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer caseID;
    
    private Integer transactionID;
    
    private String docCode;
    
    private String status;
    
    private String address;
    
    private String createdDate;
    
    public CaseInformation() {
    
    }
    
    public CaseInformation(int caseID) {
        this.caseID = caseID;
    }
    
    public CaseInformation(int caseID, int transactionID) {
        this.caseID = caseID;
        this.transactionID = transactionID;
    }
    
    public Integer getCaseID() {
        return caseID;
    }
    
    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }
    
    public Integer getTransactionID() {
        return transactionID;
    }
    
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }
    
    public String getDocCode() {
        return docCode;
    }
    
    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
