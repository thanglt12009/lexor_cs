/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lexor.cs.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

/**
 *
 * @author ChiPU
 */
public class JWTReader {
    
    public static String getKey() {
        return "elZwYURwVmxKVGRWK3JNVVBaTHJ3YmZnM3Z3UDI2UEE0QXZqVnJXNHM1SVY=";
    }
    
    public static boolean parse(String jwt) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build() 
                .parseClaimsJws(jwt);
        }
        catch (JwtException ex) {
            return false;
        }
        
        return true;
    }
}
