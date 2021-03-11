package com.lexor.cs.resource;

import com.lexor.cs.domain.CaseMessage;
import com.lexor.cs.domain.CaseReturn;
import com.lexor.cs.service.CaseMessageService;
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
@Path("/case_message")
public class CaseMessageFacadeREST extends AbstractFacade<CaseMessage> {
    
    private CaseMessageService service;

    public CaseMessageFacadeREST() {
        super(CaseMessage.class);
        this.service = new CaseMessageService();
    }

    @Override
    protected Service getService() {
        return this.service;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public int create(CaseMessage entity) throws SQLException {
        return super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public int edit(@PathParam("id") Integer id, CaseMessage entity) throws SQLException {
        return super.edit(id, entity);
    }

    @DELETE
    @Path("/detail/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public int remove(@PathParam("id") Integer id) throws SQLException {
        CaseMessage entity = super.find(id);
        return super.remove(entity);
    }

    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public CaseMessage find(@PathParam("id") Integer id) throws SQLException {
        return super.find(id);
    }
    
    @GET
    @Path("/{sendTo}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CaseMessage> findByKeyWord(@PathParam("sendTo") String sendTo) throws SQLException {        
        return super.findByKeyWord(sendTo);
    }

    @GET
    @Path("/{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CaseMessage> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws SQLException {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST(@PathParam("id") Integer id) throws SQLException {
        return String.valueOf(super.count(id));
    }
}
