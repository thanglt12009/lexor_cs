package com.lexor.cs.resource;

import com.lexor.cs.domain.ApiSaleOrder;
import com.lexor.cs.domain.ApiUser;
import com.lexor.cs.domain.Case;
import com.lexor.cs.domain.CaseService;
import com.lexor.cs.service.ApiSOService;
import com.lexor.cs.service.ApiUserService;
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
@Path("/apiUser")
public class UserFacadeREST extends AbstractFacade<ApiUser> {
    
    private ApiUserService service;
        
    private Session mailSession;

    public UserFacadeREST() {
        super(ApiUser.class);
        this.service = new ApiUserService();
    }

    @Override
    protected Service getService() {
        return this.service;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public int create(ApiUser entity) throws SQLException {
        return super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public int edit(@PathParam("id") Integer id, ApiUser entity) throws SQLException {
        return super.edit(id, entity);
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public int remove(@PathParam("id") Integer id) throws SQLException {
        ApiUser entity = super.find(id);
        return super.remove(entity);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ApiUser find(@PathParam("id") Integer id) throws SQLException {
        return super.find(id);
    }

    @GET
    @Path("/{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ApiUser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws SQLException {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ApiUser> findByKeyWord(@PathParam("id") Integer id) throws SQLException {        
        return super.findByKeyWord(id);
    }
    
    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST(@PathParam("id") Integer id) throws SQLException {
        return String.valueOf(super.count(id));
    }
    
    @POST  
    @Path("/{emailTo}/{subject}/{emailBody}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void sendEmail(@PathParam("email") String emailTo, @PathParam("subject") String subject, @PathParam("emailBody") String emailBody) 
            throws NamingException, MessagingException, UnsupportedEncodingException {
        
        String username = "lexorsmtpmail@gmail.com";
        String password = "Lexorsmtpmail2021";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("to_username_a@gmail.com, to_username_b@yahoo.com")
            );
            message.setSubject("Testing Gmail SSL");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
