package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author THANGLT1
 */
public class CaseType implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer caseID;
    
    private Integer caseTypeID;
    
    private String caseTypeValue; 
    
    public CaseType() {
    
    }
    
    public CaseType(int caseTypeID) {
        this.caseTypeID = caseTypeID;
    }
    
    public CaseType(int caseTypeID, int caseID) {
        this.caseID = caseID;
        this.caseTypeID = caseTypeID;
    }
    
    public Integer getCaseID() {
        return caseID;
    }
    
    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }
    
    public Integer getCaseTypeID() {
        return caseTypeID;
    }
    
    public void setCaseTypeID(int caseTypeID) {
        this.caseTypeID = caseTypeID;
    }
    
    public String getCaseTypeValue() {
        return caseTypeValue;
    }
    
    public void setCaseTypeValue(String caseTypeValue) {
        this.caseTypeValue = caseTypeValue;
    } 
    
}
