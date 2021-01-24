package com.lexor.cs.resource;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author thanh
 */
@Path("/hello")
@RequestScoped
public class HelloResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HelloResource
     */
    public HelloResource() {
    }

    /**
     * Retrieves representation of an instance of com.lexor.cs.HelloResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        return "<html> " + "<title>" + "Hello Jersey" + "</title>"  
        + "<body><h1>" + "Hello Jersey HTML" + "</h1></body>" + "</html> "; 
    }
}
