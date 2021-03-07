package com.lexor.cs.resource;

import com.lexor.cs.domain.RMALog;
import com.lexor.cs.domain.ServiceLog;
import com.lexor.cs.service.Service;
import com.lexor.cs.service.ServiceLogService;
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


@Stateless
@Path("/service_log")
public class ServiceLogFacadeREST extends AbstractFacade<ServiceLog> {
    
    private ServiceLogService service;

    public ServiceLogFacadeREST() {
        super(ServiceLog.class);
        this.service = new ServiceLogService();
    }

    @Override
    protected Service getService() {
        return this.service;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public int create(ServiceLog entity) throws SQLException {
        return super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public int edit(@PathParam("id") Integer id, ServiceLog entity) throws SQLException {
        return super.edit(id, entity);
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public int remove(@PathParam("id") Integer id) throws SQLException {
        ServiceLog entity = super.find(id);
        return super.remove(entity);
    }

    @GET
    @Path("/detail/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ServiceLog find(@PathParam("id") Integer id) throws SQLException {
        return super.find(id);
    }
    
    @GET
    @Path("/find/{message}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ServiceLog> findByKeyWord(@PathParam("message") String message) throws SQLException {        
        return super.findByKeyWord(message);
    }

    @GET
    @Path("/{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ServiceLog> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws SQLException {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() throws SQLException {
        return String.valueOf(super.count());
    }
}
