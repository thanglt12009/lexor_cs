/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lexor.cs.factory;

import com.lexor.cs.domain.Token;

/**
 *
 * @author ChiPU
 */
public class TokenFactory {
    public static Token createToken(String tokenString) {
        Token token = new Token(tokenString);
        return token;
    }
}
