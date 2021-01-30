package com.lexor.cs.resource;

import com.lexor.cs.domain.CaseLog;
import com.lexor.cs.service.CaseLogService;
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
@Path("/case_log")
public class CaseLogFacadeREST extends AbstractFacade<CaseLog> {
    
    private CaseLogService service;

    public CaseLogFacadeREST() {
        super(CaseLog.class);
        this.service = new CaseLogService();
    }

    @Override
    protected Service getService() {
        return this.service;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public int create(CaseLog entity) throws SQLException {
        return super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public int edit(@PathParam("id") Integer id, CaseLog entity) throws SQLException {
        return super.edit(id, entity);
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public int remove(@PathParam("id") Integer id) throws SQLException {
        CaseLog entity = super.find(id);
        return super.remove(entity);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public CaseLog find(@PathParam("id") Integer id) throws SQLException {
        return super.find(id);
    }
    
    @GET
    @Path("/{message}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CaseLog> findByKeyWord(@PathParam("message") String message) throws SQLException {        
        return super.findByKeyWord(message);
    }

    @GET
    @Path("/{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CaseLog> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws SQLException {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() throws SQLException {
        return String.valueOf(super.count());
    }
}
