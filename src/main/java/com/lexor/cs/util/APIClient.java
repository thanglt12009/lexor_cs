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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author ChiPU
 */
public class APIClient {
    protected HttpClient httpClient;
    protected HttpGet httpGet;
    private final String apiHost = "https://dev.chonail.com/lxerp/api/";

    
    public APIClient() {
        this.httpClient = HttpClientBuilder.create().build();
        
    }
    
    public APIClient setRoute(String route) {
        this.httpGet = new HttpGet(this.apiHost + route);
        this.httpGet.setHeader("x-access-token", "eyJraWQiOiJrMSIsImFsZyI6IlJTMjU2In0.eyJpc3MiOiJlcnAubGV4b3IuY29tIiwiZXhwIjoxNjE5MTU4NTU5LCJqdGkiOiJVdllhWkc3eG9Ga1BERC12ZGlmYmlRIiwiaWF0IjoxNjEzOTc0NTU5LCJuYmYiOjE2MTM5NzQ0MzksImlkIjoiNDgifQ.p0cy8M7nRG17N1lbzyGqmRvy78VM9KZqWlN57UGeYpl4sntWx2sAANV_rS_xuUmGTZ6l2b6DFKiSi55zBl8VwnaphMxQNTpHuEDUUO9fZYHvbi_ariO_JBTdcW26Cumqa70n96Hjthjntfa34b6YYivq_TRlyAgVj3XUTKcYKDrQ4ynYzN5xbN08ouFuYuNTCqzAf8RoNUUJeHHKq1uhRE0kit5YUDvkH1BC_tDv2tORF1ytWj22kr1NRnwOM4lJ_pmgK4zvmHhW1ohcFcqrCWpWzk_0035K-FcLH3GzD6wNrlaQOQjSe7ljYa72V3zUNQARtFGhPBdbfenhYyOa9g");
        this.httpGet.setHeader("Access-Control-Allow-Headers", "X-Access-Token");

        return this;
    }
    
    public String execute() throws IOException {
        HttpResponse response = this.httpClient.execute(this.httpGet);
        HttpEntity entity = response.getEntity();

        return EntityUtils.toString(entity, "UTF-8");
    }
}
