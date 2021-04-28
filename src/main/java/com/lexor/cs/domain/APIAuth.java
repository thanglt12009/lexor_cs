/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lexor.cs.domain;

/**
 *
 * @author ChiPU
 */
public class APIAuth {
    private int params;
    private String token = "";

    /**
     * @return the params
     */
    public int getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(int params) {
        this.params = params;
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
