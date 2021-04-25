package com.lexor.cs.resource;

import com.lexor.cs.domain.Case;
import com.lexor.cs.domain.CaseService;
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
import java.util.Properties;
import javax.mail.PasswordAuthentication;


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
    public String countREST(@PathParam("id") Integer id) throws SQLException {
        return String.valueOf(super.count(id));
    }
    
    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Case> findByKeyWord(@PathParam("id") String id) throws SQLException {        
        return super.findByKeyWord(id);
    }
    
    @POST  
    @Path("/{emailTo}/{subject}/{emailBody}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void sendEmail(@PathParam("emailTo") String emailTo, @PathParam("subject") String subject, @PathParam("emailBody") String emailBody) 
            throws NamingException, MessagingException, UnsupportedEncodingException {
        String username = "lexorsmtpmail@gmail.com";
        String password = "Lexorsmtpmail2021";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailTo)
            );
            message.setSubject(subject);
            message.setText(emailBody);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
