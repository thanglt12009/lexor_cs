package com.lexor.cs.resource;

import com.lexor.cs.domain.ApiSaleOrder;
import com.lexor.cs.service.ApiSOService;
import com.lexor.cs.service.Service;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET; 
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.naming.NamingException;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.NotFoundException;


@Stateless
@Path("/apiService")
public class ServiceFacadeREST extends AbstractFacade<ApiSaleOrder> {
    
    private ApiSOService service;
        
    private Session mailSession;

    public ServiceFacadeREST() {
        super(ApiSaleOrder.class);
        this.service = new ApiSOService();
    }

    @Override
    protected Service getService() {
        return this.service;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public int create(ApiSaleOrder entity) throws SQLException {
        return super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public int edit(@PathParam("id") Integer id, ApiSaleOrder entity) throws SQLException {
        throw new NotFoundException("JWT token not found");
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public int remove(@PathParam("id") Integer id) throws SQLException {
        throw new NotFoundException("JWT token not found");
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ApiSaleOrder find(@PathParam("id") Integer id) throws SQLException {
        return this.service.get(id);
    }

    @GET
    @Path("/{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ApiSaleOrder> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws SQLException {
        throw new NotFoundException("JWT token not found");
    }
    
    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ApiSaleOrder> findByKeyWord(@PathParam("id") Integer id) throws SQLException {        
        throw new NotFoundException("JWT token not found");
    }
    
    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST(@PathParam("id") Integer id) throws SQLException {
        throw new NotFoundException("JWT token not found");
    }
    
    @POST  
    @Path("/{emailTo}/{subject}/{emailBody}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void sendEmail(@PathParam("email") String emailTo, @PathParam("subject") String subject, @PathParam("emailBody") String emailBody) 
            throws NamingException, MessagingException, UnsupportedEncodingException {
        throw new NotFoundException("JWT token not found");
    }
}
