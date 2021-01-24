package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author THANGLT1
 */
public class CaseService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer caseID;
    
    private Integer caseServiceID;
    
    private Integer customerSOID;  
    
    private String logMessage;
    
    private String createdDate;
    
    private String updatedDate;
    
    public CaseService() {
    
    }
    
    public CaseService(int caseServiceID) {
        this.caseServiceID = caseServiceID;
    }
    
    public CaseService(int caseServiceID, int caseID) {
        this.caseID = caseID;
        this.caseServiceID = caseServiceID;
    }
    
    public CaseService(int caseServiceID, int caseID, int customerSOID) {
        this.caseID = caseID;
        this.caseServiceID = caseServiceID;
        this.customerSOID = customerSOID;
    }
    
    
    public Integer getCaseID() {
        return caseID;
    }
    
    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }
    
    public Integer getCaseServiceID() {
        return caseServiceID;
    }
    
    public void setCaseServiceID(int caseServiceID) {
        this.caseServiceID = caseServiceID;
    }
    
    public Integer getCustomerSOID() {
        return customerSOID;
    }
    
    public void setCustomerSOID(int customerSOID) {
        this.customerSOID = customerSOID;
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
    
    public String getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (caseServiceID != null ? caseServiceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Case)) {
            return false;
        }
        CaseService other = (CaseService) object;
        if ((this.caseServiceID == null && other.caseServiceID != null) || (this.caseServiceID != null && !this.caseServiceID.equals(other.caseServiceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lexor.cs.domain.CaseService[ caseServiceID=" + caseServiceID + " ]";
    }
}
