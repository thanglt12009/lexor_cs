package com.lexor.cs.domain;

import java.io.Serializable;

/**
 *
 * @author ChiPU
 */
public class Token implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String token;
    
    public Token(String token) {
        this.token = token;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

}
