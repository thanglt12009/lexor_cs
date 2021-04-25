package com.lexor.cs.domain;

import java.io.Serializable;

/**
 *
 * @author ChiPU
 */
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String password;

    public User(String username, String password) {
        this.password = password;
    }

    public User() {
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
}
