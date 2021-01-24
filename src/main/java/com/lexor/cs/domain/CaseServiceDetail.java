package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

public class CaseServiceDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer caseServiceDetailID;
    
    private Integer caseServiceID;
    
    private Integer customerSOID;
    
    private Date createdDate;
    
    private Date updatedDate;

    public CaseServiceDetail() {
    }

    public CaseServiceDetail(Integer caseServiceDetailID) {
        this.caseServiceDetailID = caseServiceDetailID;
    }

    public CaseServiceDetail(Integer caseServiceDetailID, Integer caseServiceID, Integer customerSOID) {
        this.caseServiceDetailID = caseServiceDetailID;
        this.caseServiceID = caseServiceID;
        this.customerSOID = customerSOID;
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
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaseServiceDetail)) {
            return false;
        }
        CaseServiceDetail other = (CaseServiceDetail) object;
        if ((this.getCaseServiceDetailID() == null && other.getCaseServiceDetailID() != null) || (this.getCaseServiceDetailID() != null && !this.caseServiceDetailID.equals(other.caseServiceDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lexor.cs.domain.CaseServiceSO[ caseServiceDetailID=" + getCaseServiceDetailID() + " ]";
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @return the caseServiceDetailID
     */
    public Integer getCaseServiceDetailID() {
        return caseServiceDetailID;
    }

    /**
     * @param caseServiceDetailID the caseServiceDetailID to set
     */
    public void setCaseServiceDetailID(Integer caseServiceDetailID) {
        this.caseServiceDetailID = caseServiceDetailID;
    }

    /**
     * @return the caseServiceID
     */
    public Integer getCaseServiceID() {
        return caseServiceID;
    }

    /**
     * @param caseServiceID the caseServiceID to set
     */
    public void setCaseServiceID(Integer caseServiceID) {
        this.caseServiceID = caseServiceID;
    }

    /**
     * @return the customerSOID
     */
    public Integer getCustomerSOID() {
        return customerSOID;
    }

    /**
     * @param customerSOID the customerSOID to set
     */
    public void setCustomerSOID(Integer customerSOID) {
        this.customerSOID = customerSOID;
    }
    
}
