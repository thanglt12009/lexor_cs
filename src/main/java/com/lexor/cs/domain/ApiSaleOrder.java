package com.lexor.cs.domain;

import java.io.Serializable;

/**
 *
 * @author THANGLT1
 */
public class ApiSaleOrder implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String code;
    
    public ApiSaleOrder() {
    
    }
    
    public ApiSaleOrder(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
    
}
