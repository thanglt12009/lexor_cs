package com.lexor.cs.resource;

import com.lexor.cs.domain.RMA;
import com.lexor.cs.domain.RMALog;
import com.lexor.cs.service.RMAService;
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
@Path("/rma")
public class RMAFacadeREST extends AbstractFacade<RMA> {
    
    private RMAService service;

    public RMAFacadeREST() {
        super(RMA.class);
        this.service = new RMAService();
    }

    @Override
    protected Service getService() {
        return this.service;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public int create(RMA entity) throws SQLException {
        return super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public int edit(@PathParam("id") Integer id, RMA entity) throws SQLException {
        return super.edit(id, entity);
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public int remove(@PathParam("id") Integer id) throws SQLException {
        RMA entity = super.find(id);
        return super.remove(entity);
    }

    @GET
    @Path("/detail/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public RMA find(@PathParam("id") Integer id) throws SQLException {
        return super.find(id);
    }

    @GET
    @Path("/find/{status}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RMA> findByKeyWord(@PathParam("status") String status) throws SQLException {        
        return super.findByKeyWord(status);
    }
    
    @GET
    @Path("/{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RMA> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws SQLException {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() throws SQLException {
        return String.valueOf(super.count());
    }
}
