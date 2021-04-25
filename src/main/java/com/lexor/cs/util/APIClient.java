/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lexor.cs.util;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author ChiPU
 */
public class APIClient {
    protected HttpClient httpClient;
    protected HttpGet httpGet;
    protected HttpPost httpPost;
    private final String apiHost = "https://dev.chonail.com/lxerp/api/";

    
    public APIClient() {
        this.httpClient = HttpClientBuilder.create().build();
        
    }
    
    public APIClient setRoute(String route, String token) {
        this.httpGet = new HttpGet(this.apiHost + route);
        this.httpGet.setHeader("x-access-token", token);
        this.httpGet.setHeader("Access-Control-Allow-Headers", "X-Access-Token");

        return this;
    }
    
    public APIClient setPostRoute(String route, StringEntity entity, String token) {
        this.httpPost = new HttpPost(this.apiHost + route);
        this.httpPost.setEntity(entity);
        this.httpPost.setHeader("x-access-token", token);
        this.httpPost.setHeader("Access-Control-Allow-Headers", "X-Access-Token");

        return this;
    }
    
    public String execute() throws IOException {
        HttpResponse response = this.httpClient.execute(this.httpGet);
        HttpEntity entity = response.getEntity();

        return EntityUtils.toString(entity, "UTF-8");
    }
    
    public String post() throws IOException {
        HttpResponse response = this.httpClient.execute(this.httpPost);
        HttpEntity entity = response.getEntity();

        return EntityUtils.toString(entity, "UTF-8");
    }
}
