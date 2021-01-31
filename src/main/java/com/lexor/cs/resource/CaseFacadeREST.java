package com.lexor.cs.resource;

import com.lexor.cs.domain.Case;
import com.lexor.cs.service.CaseReturnService;
import com.lexor.cs.service.CaseServices;
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
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.naming.NamingException;
import java.io.UnsupportedEncodingException;


@Stateless
@Path("/case")
public class CaseFacadeREST extends AbstractFacade<Case> {
    
    private CaseReturnService service;
        
    private Session mailSession;

    public CaseFacadeREST() {
        super(Case.class);
        this.service = new CaseReturnService();
    }

    @Override
    protected Service getService() {
        return this.service;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public int create(Case entity) throws SQLException {
        return super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public int edit(@PathParam("id") Integer id, Case entity) throws SQLException {
        return super.edit(id, entity);
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public int remove(@PathParam("id") Integer id) throws SQLException {
        Case entity = super.find(id);
        return super.remove(entity);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Case find(@PathParam("id") Integer id) throws SQLException {
        return super.find(id);
    }

    @GET
    @Path("/{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Case> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws SQLException {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() throws SQLException {
        return String.valueOf(super.count());
    }
    
    @POST  
    @Path("/{emailTo}/{subject}/{emailBody}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void sendEmail(@PathParam("email") String emailTo, @PathParam("subject") String subject, @PathParam("emailBody") String emailBody) 
            throws NamingException, MessagingException, UnsupportedEncodingException {
        Message msg = new MimeMessage(mailSession);
        msg.setSubject(subject);
        msg.setRecipient(RecipientType.TO, new InternetAddress(emailTo, emailTo)); // 2nd parameter is personal name
        msg.setFrom(new InternetAddress("emailFromHere", "password")); // set email from and password here        

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(emailBody);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        msg.setContent(multipart);

        Transport.send(msg);
    }
}
