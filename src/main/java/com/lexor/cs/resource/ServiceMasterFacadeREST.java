package com.lexor.cs.resource;

import com.lexor.cs.domain.ServiceMaster;
import com.lexor.cs.service.ServiceMasterService;
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


@Stateless
@Path("/serviceMaster")
public class ServiceMasterFacadeREST extends AbstractFacade<ServiceMaster> {
    
    private ServiceMasterService service;

    public ServiceMasterFacadeREST() {
        super(ServiceMaster.class);
        this.service = new ServiceMasterService();
    }

    @Override
    protected Service getService() {
        return this.service;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public int create(ServiceMaster entity) throws SQLException {
        return super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public int edit(@PathParam("id") Integer id, ServiceMaster entity) throws SQLException {
        return super.edit(id, entity);
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public int remove(@PathParam("id") Integer id) throws SQLException {
        ServiceMaster entity = super.find(id);
        return super.remove(entity);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ServiceMaster find(@PathParam("id") Integer id) throws SQLException {
        return super.find(id);
    }
    
    @GET
    @Path("/{status}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ServiceMaster> findByKeyWord(@PathParam("status") Integer status) throws SQLException {
        ServiceMaster entity = new ServiceMaster();
        entity.setStatus(status);
        return super.findByKeyWord(entity);
    }

    @GET
    @Path("/{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ServiceMaster> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws SQLException {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() throws SQLException {
        return String.valueOf(super.count());
    }
}
