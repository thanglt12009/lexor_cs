package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

public class ServiceMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer serviceMasterID;
    
    private Integer caseServiceID;
    
    private Double subTotal;
    
    private Double shippingFee;
    
    private Double managerDiscount;
    
    private Double total;
    
    private Date createdDate;
    
    private Short isSubmittedProduction; 
    
    private Integer status;      
    
    private Date updatedDate;

    public ServiceMaster() {
    }

    public ServiceMaster(Integer serviceMasterID) {
        this.serviceMasterID = serviceMasterID;
    }

    public ServiceMaster(Integer serviceMasterID , Integer caseServiceID) {
        this.serviceMasterID = serviceMasterID;
        this.caseServiceID = caseServiceID; 
    }

    public Integer getServiceMasterID() {
        return serviceMasterID;
    }

    public void setServiceMasterID(Integer serviceMasterID) {
        this.serviceMasterID = serviceMasterID;
    }
    
    public Integer getCaseServiceID() {
        return caseServiceID;
    }
    
    public void setCaseServiceID(Integer caseServiceID) {
        this.caseServiceID = caseServiceID;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
    
    public Double getSubTotal() {
        return subTotal;
    }
    
    public void setTotal(Double subTotal) {
        this.total = total;
    }
    
    public Double getTotal() {
        return total;
    }
    
    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }
    
    public Double getShippingFee() {
        return shippingFee;
    }
    
    public void setManagerDiscount(Double managerDiscount) {
        this.managerDiscount = managerDiscount;
    }
    
    public Double getManagerDiscount() {
        return managerDiscount;
    }
    
    public void setIsSubmittedProduction(Short isSubmittedProduction) {
        this.isSubmittedProduction = isSubmittedProduction;
    }
    
    public Short getIsSubmittedProduction() {
        return isSubmittedProduction;
    }
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
        hash += (serviceMasterID != null ? serviceMasterID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceMaster)) {
            return false;
        }
        ServiceMaster other = (ServiceMaster) object;
        if ((this.serviceMasterID == null && other.serviceMasterID != null) || (this.serviceMasterID != null && !this.serviceMasterID.equals(other.serviceMasterID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lexor.cs.domain.Case[ serviceMasterID=" + serviceMasterID + " ]";
    }
    
}
