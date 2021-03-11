package com.lexor.cs.resource;

import com.lexor.cs.domain.RMASO_Detail;
import com.lexor.cs.service.RMASO_DetailService;
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
@Path("/rma_soDetail")
public class RMASO_DetailFacadeREST extends AbstractFacade<RMASO_Detail> {
    
    private RMASO_DetailService service;

    public RMASO_DetailFacadeREST() {
        super(RMASO_Detail.class);
        this.service = new RMASO_DetailService();
    }

    @Override
    protected Service getService() {
        return this.service;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public int create(RMASO_Detail entity) throws SQLException {
        return super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public int edit(@PathParam("id") Integer id, RMASO_Detail entity) throws SQLException {
       return super.edit(id, entity);
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public int remove(@PathParam("id") Integer id) throws SQLException {
        RMASO_Detail entity = super.find(id);
        return super.remove(entity);
    }

    @GET
    @Path("/detail/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public RMASO_Detail find(@PathParam("id") Integer id) throws SQLException {
        return super.find(id);
    }
    
    @GET
    @Path("/find/{keyword}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RMASO_Detail> findByKeyWord(@PathParam("productID") Integer productID) throws SQLException {        
        return super.findByKeyWord(productID);
    }

    @GET
    @Path("/{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RMASO_Detail> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws SQLException {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST(@PathParam("id") Integer id) throws SQLException {
        return String.valueOf(super.count(id));
    }
}
