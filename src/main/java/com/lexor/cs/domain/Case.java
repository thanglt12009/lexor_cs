package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

public class Case implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer caseID;
    
    private String caseName;
    
    private int customerID;
    
    private int salonID;
    
    private Short casePriority;
    
    private Short caseType;
    
    private Short status;
    
    private Date createdDate;
    
    private Date updatedDate;

    public Case() {
    }

    public Case(Integer caseID) {
        this.caseID = caseID;
    }

    public Case(Integer caseID, int customerID, int salonID) {
        this.caseID = caseID;
        this.customerID = customerID;
        this.salonID = salonID;
    }

    public Integer getCaseID() {
        return caseID;
    }

    public void setCaseID(Integer caseID) {
        this.caseID = caseID;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getSalonID() {
        return salonID;
    }

    public void setSalonID(int salonID) {
        this.salonID = salonID;
    }

    public Short getCasePriority() {
        return casePriority;
    }

    public void setCasePriority(Short casePriority) {
        this.casePriority = casePriority;
    }

    public Short getCaseType() {
        return caseType;
    }

    public void setCaseType(Short caseType) {
        this.caseType = caseType;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (caseID != null ? caseID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Case)) {
            return false;
        }
        Case other = (Case) object;
        if ((this.caseID == null && other.caseID != null) || (this.caseID != null && !this.caseID.equals(other.caseID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lexor.cs.domain.Case[ caseID=" + caseID + " ]";
    }
    
}
