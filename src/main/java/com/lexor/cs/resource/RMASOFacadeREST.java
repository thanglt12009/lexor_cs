package com.lexor.cs.resource;

import com.lexor.cs.domain.RMASO;
import com.lexor.cs.service.RMASOService;
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
@Path("/rma_so")
public class RMASOFacadeREST extends AbstractFacade<RMASO> {
    
    private RMASOService service;

    public RMASOFacadeREST() {
        super(RMASO.class);
        this.service = new RMASOService();
    }

    @Override
    protected Service getService() {
        return this.service;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public int create(RMASO entity) throws SQLException {
        return super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public int edit(@PathParam("id") Integer id, RMASO entity) throws SQLException {
        return super.edit(id, entity);
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public int remove(@PathParam("id") Integer id) throws SQLException {
        RMASO entity = super.find(id);
        return super.remove(entity);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public RMASO find(@PathParam("id") Integer id) throws SQLException {
        return super.find(id);
    }
    
    @GET
    @Path("/{fee}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RMASO> findByKeyWord(@PathParam("fee") Integer fee) throws SQLException {        
        return super.findByKeyWord(fee);
    }

    @GET
    @Path("/{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RMASO> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws SQLException {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() throws SQLException {
        return String.valueOf(super.count());
    }
}
