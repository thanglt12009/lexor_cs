package com.lexor.cs.resource;

import com.lexor.cs.domain.CaseService;
import com.lexor.cs.domain.RMA;
import com.lexor.cs.service.RMAService;
import com.lexor.cs.service.Service;
import com.lexor.cs.util.JWTReader;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Path("/caseRMA")
public class CaseRMAAPIFacadeREST extends AbstractFacade<CaseService> {
    
    private RMAService service;

    public CaseRMAAPIFacadeREST() {
        super(CaseService.class);
        this.service = new RMAService();
    }

    @Override
    protected Service getService() {
        return this.service;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public int create(CaseService entity) throws SQLException {
        throw new NotFoundException("Not Found");
    }

    @PUT
    @Path("/updateRMAStatus/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public int edit(@HeaderParam("x-access-token") String jwt, @PathParam("id") Integer id, RMA entity) throws SQLException {
        
        //if (JWTReader.parse(jwt)) {
            return this.service.update(id, entity);
        //}
        
        //throw new NotFoundException("JWT token not found");
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public int remove(@PathParam("id") Integer id) throws SQLException {
        throw new NotFoundException("Not Found");
    }

    @GET
    @Path("/detail/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public CaseService find(@PathParam("id") Integer id) throws SQLException {
        throw new NotFoundException("Not Found");
    }
    
    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CaseService> findByKeyWord(@PathParam("id") String id) throws SQLException {        
        throw new NotFoundException("Not Found");
    }

    @GET
    @Path("/{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CaseService> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws SQLException {
        throw new NotFoundException("Not Found");
    }

    @GET
    @Path("/count/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST(@PathParam("id") Integer id) throws SQLException {
        throw new NotFoundException("Not Found");
    }
}
